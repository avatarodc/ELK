package com.example.ELK.controller;

import com.example.ELK.model.Product;
import com.example.ELK.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// Définit le chemin de base pour toutes les routes de ce contrôleur
@RequestMapping("/api/products")
// Annotation Lombok pour la journalisation
@Slf4j
public class ProductController {

    // Injection du service de produits
    @Autowired
    private ProductService productService;

    // Méthode de création de produit (requête POST)
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Journalisation de la réception de la requête de création
        log.info("Réception d'une requête de création de produit : {}", product);
        return productService.saveProduct(product);
    }

    // Méthode de récupération d'un produit par son ID (requête GET)
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        // Journalisation de la réception de la requête de récupération
        log.info("Réception d'une requête de récupération du produit avec l'identifiant : {}", id);
        return productService.getProduct(id);
    }

    // Méthode de récupération de tous les produits (requête GET)
    @GetMapping
    public Iterable<Product> getAllProducts() {
        // Journalisation de la réception de la requête de récupération de tous les produits
        log.info("Réception d'une requête de récupération de tous les produits");
        return productService.getAllProducts();
    }
}