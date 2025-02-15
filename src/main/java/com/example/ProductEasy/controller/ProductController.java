package com.example.ProductEasy.controller;

import com.example.ProductEasy.entity.Product;
import com.example.ProductEasy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Afficher la liste des produits
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list"; // Vérifiez que le fichier product-list.html existe
    }

    // Afficher le formulaire pour créer un nouveau produit
    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form"; // Vérifiez que le fichier product-form.html existe
    }

    // Sauvegarder un produit
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products"; // Redirection vers la liste des produits
    }

    // Afficher le formulaire pour modifier un produit existant
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) { // Gérer les IDs invalides
            throw new IllegalArgumentException("Produit introuvable pour l'ID : " + id);
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    // Supprimer un produit
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) { // Gérer les IDs invalides
            throw new IllegalArgumentException("Produit introuvable pour l'ID : " + id);
        }
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
