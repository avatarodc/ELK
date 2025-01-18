package com.example.ELK.repository;

import com.example.ELK.model.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<Category, Long> {
}
