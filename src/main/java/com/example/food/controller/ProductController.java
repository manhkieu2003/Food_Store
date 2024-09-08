package com.example.food.controller;

import com.example.food.entity.Product;
import com.example.food.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    private static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/Images";
    @PostMapping("/addingProduct")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile image) throws IOException {
//        if (!image.isEmpty()) {
//           product.setPImage(image.getBytes());
//        }
        if (!image.isEmpty()) {
            // Tạo tên file duy nhất
            String imageUUID = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, image.getBytes());

            product.setPImage("/Images/" + imageUUID);
        }

     productService.addProduct(product);
      return "redirect:/products";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
       this.productService.deleteProduct(id);
       return "redirect:/admin/services";
    }
    @PostMapping("/updatingProduct/{productId}")
    public String updateProduct(@ModelAttribute Product product,@PathVariable("productId") int id,@RequestParam("image") MultipartFile image) throws IOException
    {
        Product existingProduct= productService.getProductById(id);
        if(existingProduct!=null) {
            if (!image.isEmpty()) {
                // xóa ảnh cũ
                if (existingProduct.getPImage() != null) {
                    Path oldImagePath = Paths.get(uploadDir, existingProduct.getPImage());
                    Files.deleteIfExists(oldImagePath);
                }
                // tạo file mới
                String imageUUID = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
                Files.write(fileNameAndPath, image.getBytes());

                product.setPImage("/Images/" + imageUUID);
            } else {
                // ảnh cũ
                product.setPImage(existingProduct.getPImage());
            }

            this.productService.updateproduct(product, id);
        }

        return "redirect:/admin/services";
    }


}
