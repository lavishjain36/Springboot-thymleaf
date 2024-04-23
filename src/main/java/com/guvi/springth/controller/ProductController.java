package com.guvi.springth.controller;


import com.guvi.springth.entity.Product;
import com.guvi.springth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Controller annotation indicates that this class serves as a Spring MVC controller

@Controller
public class ProductController {
    // Autowired annotation injects ProductService bean into this controller
    @Autowired
    private ProductService productService;

    // Handler method for GET request to "/"
    @GetMapping("/")
    public String index() {
        // Returns the name of the Thymeleaf template to be rendered
        return "index";
    }

    // Handler method for GET request to "/addProduct"
    @GetMapping("/addProduct")
    public String showAddProductForm() {
        // Returns the name of the Thymeleaf template to be rendered
        return "addProduct";
    }

    // Handler method for POST request to "/addProduct"
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product) {
        // Calls the saveProduct method from ProductService to save the product
        productService.saveProduct(product);
        // Redirects to the root ("/") URL after adding the product
        return "redirect:/";
    }

    // Handler method for GET request to "/displayProduct"
    @GetMapping("/displayProduct")
    public String showDisplayProductForm(Model model) {
        // Adds a new Product object to the model with the key "product"
        model.addAttribute("product", new Product());
        // Returns the name of the Thymeleaf template to be rendered
        return "displayProduct";
    }

    // Handler method for POST request to "/displayProduct"
    @PostMapping("/displayProduct")
    public String displayProduct(@RequestParam("category") String category, Model model) {
        // Calls the getAllProductsByCategory method from ProductService to get products by category
        List<Product> products = productService.getAllProductsByCategory(category);
        // Adds the products list to the model with the key "products"
        model.addAttribute("products", products);
        // Returns the name of the Thymeleaf template to be rendered
        return "productList";
    }


    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products"; // This will return products.html Thymeleaf template
    }

}
