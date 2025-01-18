package com.example.ELK.service;

import com.example.ELK.model.Category;
import com.example.ELK.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Méthode pour enregistrer un nouveau categories* @param category Le categoriesà sauvegarder
     * @return Le categoriessauvegardé
     */
    public Category saveCategory(Category category) {
        // Journalisation de l'opération de sauvegarde
        log.info("Enregistrement d'un nouveau categorie: {}", category);
        return categoryRepository.save(category);
    }

    /**
     * Méthode pour récupérer un categoriespar son identifiant
     * @param id L'identifiant de la categproe* @return La categorie correspondant
     * @throws RuntimeException si la categorie n'est pas trouvé
     */
    public Category getCategory(long id) {
        // Journalisation de la tentative de récupération
        log.info("Récupération de la categorie avec l'identifiant : {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("categorie non trouvé"));
    }

    /**
     * Méthode pour récupérer toutes les categories
     * @return Une collection de toutes les categories
     */
    public Iterable<Category> getAllCategory() {
        // Journalisation de la récupération de toutes les categories
        log.info("Récupération de tous les categories");
        return categoryRepository.findAll();
    }
}