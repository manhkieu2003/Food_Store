package com.example.food.controller;

import com.example.food.entity.*;
import com.example.food.service.*;
import com.example.food.util.OrderStatus;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller

public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductOrderService productOrderService;
    @ModelAttribute
    public void getUserDetails(Principal principal, Model model)
    {
        if(principal!=null)
        {
            String email = principal.getName();
            User user = userService.getUserByUemail(email);
            model.addAttribute("user",user);
            Integer countCart = cartService.getCountCart(user.getId());
            model.addAttribute("countCart",countCart);
        }
    }
    @GetMapping("/home")
    public String home()
    {
        return "Home";
    }
    @GetMapping("/products")
    public String products(Model model)
    {



        List<Product> allProducts = productService.getAllProduct();
        model.addAttribute("products",allProducts);
        List<Category> allCategory=categoryService.getAllCategories();
        model.addAttribute("categorys",allCategory);

//        if(principal != null)
//        {
//            String email = principal.getName();
//            User user = userService.getUserByUemail(email);
//            model.addAttribute("user",user);
//        }
//        List<User> allUsers = userService.getAllUsers();
//        model.addAttribute("user",allUsers);



        return "Products";
    }
    @GetMapping("/addCart")
    public String addToCart(@RequestParam("pid") Integer pid, @RequestParam("uid") Integer uid)
    {
        this.cartService.saveCart(pid,uid);
        return "redirect:/cart";
    }

    @GetMapping("/about")
    public String about()
    {
        return "About";
    }
    @GetMapping("/signin")

    public String login(Model model)
    {
//        model.addAttribute("adminLogin",new AdminLogin());
        return "Login";
    }
    @GetMapping("/add_user")
    public String addUser()
    {
        return "Add_User";
    }
    @GetMapping("/cart")
    public String loadCart(Principal principal,Model model)
    {
        String email=principal.getName();
        User user = userService.getUserByUemail(email);
     List<Cart> carts=  cartService.getCartsByUser(user.getId());
       model.addAttribute("carts",carts);
       if(carts.size()>0) {
           Double totalAllPrice = carts.get(carts.size() - 1).getTotalAllPrice();
           model.addAttribute("totalAllPrice", totalAllPrice);
       }
        return "Cart";
    }
    @GetMapping("/deleteCart/{id}")
            public String deleteCart(@PathVariable Integer id)
    {
        this.cartService.DeleteCart(id);
        return "redirect:/cart";
    }
    @GetMapping("/cartQuantityUpdate")
    public String UpdateQuantity(@RequestParam("sy") String sy,@RequestParam("cid") Integer cid)
    {
      this.cartService.updateQuantity(sy,cid);

        return "redirect:/cart";
    }
    @GetMapping("/orders")
    public String Order(Principal principal,Model model) {
        String email = principal.getName();
        User user = userService.getUserByUemail(email);
        List<Cart> carts = cartService.getCartsByUser(user.getId());
        model.addAttribute("carts", carts);
        // laays ra toong tiền
        if (carts.size() > 0) {
            double totalOderPrice = carts.get(carts.size() - 1).getTotalAllPrice();
            double AlltotalOrderPrice = carts.get(carts.size() - 1).getTotalAllPrice() + 250 + 100;
            System.out.println(totalOderPrice);
            System.out.println(AlltotalOrderPrice);

            model.addAttribute("totalAllPrice", totalOderPrice);
            model.addAttribute("AlltotalAllPrice", AlltotalOrderPrice);

        }
        return "Order";
    }
    @PostMapping("/save-order")
    public String SaveOrder(@ModelAttribute OrderRequest orderRequest, Principal principal)
    {
        String email=principal.getName();
        User user = userService.getUserByUemail(email);
        this.productOrderService.saveOrder(user.getId(),orderRequest);
        System.out.println(orderRequest);
        return "OrderSuccess";
    }
    @GetMapping("/user_order")
    public String MyOder(Model model,Principal principal)
    {
        String email=principal.getName();
        User user = userService.getUserByUemail(email);
        List<ProductOrder> orders = productOrderService.getOrdersByUser(user.getId());
        model.addAttribute("orders",orders);

        return "My_Order";
    }
    @GetMapping("update_status")
    public String updateStatus(@RequestParam Integer id,@RequestParam Integer st)
    {
        String status=null;
        OrderStatus[] values = OrderStatus.values();
        for(OrderStatus value : values) {
            if(Objects.equals(value.getId(),st))
            {
              status = value.getName();


            }
        }
        this.productOrderService.updateOrderStatus(id,status);
//        System.out.println(values);
        return "redirect:/user_order";
    }


}
