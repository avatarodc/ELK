package com.example.ELK.controller;

import com.example.ELK.model.Category;
import com.example.ELK.model.Product;
import com.example.ELK.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// Définit le chemin de base pour toutes les routes de ce contrôleur
@RequestMapping("/api/categories")
// Annotation Lombok pour la journalisation
@Slf4j
public class CategoryController {

    // Injection du service de produits
    @Autowired
    private CategoryService categoryService;

    // Méthode de création de produit (requête POST)
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        // Journalisation de la réception de la requête de création
        log.info("Réception d'une requête de création de produit : {}", category);
        return categoryService.saveCategory(category);
    }

    // Méthode de récupération d'une categorie par son ID (requête GET)
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id) {
        // Journalisation de la réception de la requête de récupération
        log.info("Réception d'une requête de récupération du produit avec l'identifiant : {}", id);
        return categoryService.getCategory(id);
    }

    // Méthode de récupération de tous les produits (requête GET)
    @GetMapping
    public Iterable<Category> getAllProducts() {
        // Journalisation de la réception de la requête de récupération de toutes les categories
        log.info("Réception d'une requête de récupération de tous les produits");
        return categoryService.getAllCategory();
    }
}