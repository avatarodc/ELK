package com.example.ELK.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "categories")
public class Category {
    private long id;
    private String name;
    private String description;
}
