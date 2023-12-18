package com.joa.retailstorev1.services;

import com.joa.retailstorev1.model.Category;
import com.joa.retailstorev1.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
