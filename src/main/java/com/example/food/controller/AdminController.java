package com.example.food.controller;

import com.example.food.entity.*;
import com.example.food.loginCredentials.AdminLogin;
import com.example.food.loginCredentials.UserLogin;
import com.example.food.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    ProductOrderService productOrderService;
    @GetMapping("/admin/services")
    public String services(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        List<ProductOrder> allOrder = this.productOrderService.getAllOrder();
        model.addAttribute("orders",allOrder);

      return "Admin_Page";
    }
    @GetMapping("/category")
    public String addCategory() {
        return "Category";
    }
    @PostMapping("/addingCategory")
    public String addCategory(@ModelAttribute("category") Category category) {
        this.categoryService.saveCategory(category);
        return "redirect:/category";
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model)
    {
        List<Category> allCategory=categoryService.getAllCategories();
        model.addAttribute("categorys",allCategory);
        List<Product> allProducts = productService.getAllProduct();
        model.addAttribute("products",allProducts);
        model.addAttribute("product", new Product()); // dung de them sanr pham th:oject$:product se bi loi
        return "Add_Product";
    }
    @GetMapping("/updateProduct/{productId}")
    public String updateProduct(@PathVariable("productId") int id, Model model)
    {
        Product product=this.productService.getProductById(id);
        System.out.println(product);
        model.addAttribute("product", product);
        List<Category> allCategory=categoryService.getAllCategories();
        model.addAttribute("categorys",allCategory);

        return "Update_Product";
    }




    @GetMapping("/updateUser/{idUser}")
    public String updateUser(@PathVariable("idUser") Integer id, Model model)
    {
        User userId = userService.getUserById(id).get();
        model.addAttribute("user", userId);

        return "Update_User";
    }
//    @GetMapping("/addAdmin")
//    public String addAdminPage()
//    {
//        return "Add_Admin";
//    }
//    @PostMapping("/addingAdmin")
//    public String addAdmin(@ModelAttribute("admin") Admin admin)
//    {
//        this.adminService.addAdmin(admin);
//        return "redirect:/admin/services";
//    }
//    @GetMapping("/deleteAdmin/{idAdmin}")
//    public String deleteAdmin(@PathVariable("idAdmin") Integer id)
//    {
//        this.adminService.deleteAdmin(id);
//        return "redirect:/admin/services";
//    }
//    @GetMapping("/updateAdmin/{idAdmin}")
//    public String updateAdmin(@PathVariable("idAdmin") Integer id,Model model)
//    {
//     Admin admins= this.adminService.getAdminId(id);
//     model.addAttribute("admin", admins);
//     return "Update_Admin";
//
//
//    }
//    @PostMapping("/updatingAdmin/{idAdmin}")
//    public String updatingUser(@ModelAttribute("admin") Admin admin, @PathVariable("idAdmin") Integer id )
//    {
//      this.adminService.updateAdmin(admin,id);
//      return "redirect:/admin/services";
//    }
//    @GetMapping("/adminLogin")
//    public String  getAllData(@ModelAttribute("adminLogin") AdminLogin login, Model model)
//    {
//        String email=login.getEmail();
//        String password=login.getPassword();
//        if(adminService.validateAdminCredentials(email, password))
//        {
//            return "redirect:/admin/services";
//        }else {
//            model.addAttribute("error", "Invalid email or password");
//            return "Login";
//        }
//
//    }
//    @GetMapping("/userlogin")
//    public String userLogin(@ModelAttribute("userLogin") UserLogin login, Model model)
//    {
//
//        String email=login.getUserEmail();
//        String password=login.getUserPassword();
//        if(userService.validateLoginCredentials(email, password))
//        {
//            return "redirect:/home";
////            user = this.services.getUserByEmail(email);
////            List<Orders> orders = this.orderServices.getOrdersForUser(user);
////            model.addAttribute("orders", orders);
////            model.addAttribute("name", user.getUname());
////            return "BuyProduct";
//
//        }
//        else
//        {
//            model.addAttribute("error2", "Invalid email or password");
//            return "Login";
//        }
//
//    }
//@GetMapping("/add_user")
//public String addUser()
//{
//    return "Add_User";
//}
    @GetMapping("add_admin")
    public String addAdmin()
    {
        return "Add_Admin";
    }
    @PostMapping("addingAdmin")
    public String saveAdmin(@ModelAttribute("user") User user)
    {
       this.userService.addAdmin(user);
        return "redirect:/admin/services";
    }
    @GetMapping("/updateAdmin/{idAdmin}")
    public String updateAdmin(@PathVariable("idAdmin") Integer id, Model model)
    {
        User AdminId = userService.getUserById(id).get();
        model.addAttribute("admin", AdminId);

        return "Update_Admin";
    }
    @PostMapping("/updatingAdmin/{idAdmin}")
    public String updatingUser(@ModelAttribute("user") User user , @PathVariable("idAdmin") Integer id){
        this.userService.updateAdmin(user,id);
        return "redirect:/admin/services";
    }
    @GetMapping("/deleteAdmin/{idAdmin}")
    public String deleteUser( @PathVariable("idAdmin") Integer id){
        this.userService.deleteAdmin(id);
        return "redirect:/admin/services";
    }
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Integer id)
    {
        this.productOrderService.DeleteOrder(id);
        return "redirect:/admin/services";
    }






}
