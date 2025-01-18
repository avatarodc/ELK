package com.example.ELK.service;

import com.example.ELK.model.Product;
import com.example.ELK.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Méthode pour enregistrer un nouveau produit
     * @param product Le produit à sauvegarder
     * @return Le produit sauvegardé
     */
    public Product saveProduct(Product product) {
        // Journalisation de l'opération de sauvegarde
        log.info("Enregistrement d'un nouveau produit : {}", product);
        return productRepository.save(product);
    }

    /**
     * Méthode pour récupérer un produit par son identifiant
     * @param id L'identifiant du produit
     * @return Le produit correspondant
     * @throws RuntimeException si le produit n'est pas trouvé
     */
    public Product getProduct(Long id) {
        // Journalisation de la tentative de récupération
        log.info("Récupération du produit avec l'identifiant : {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    /**
     * Méthode pour récupérer tous les produits
     * @return Une collection de tous les produits
     */
    public Iterable<Product> getAllProducts() {
        // Journalisation de la récupération de tous les produits
        log.info("Récupération de tous les produits");
        return productRepository.findAll();
    }
}