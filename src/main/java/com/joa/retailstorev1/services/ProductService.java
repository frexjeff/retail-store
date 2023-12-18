package com.joa.retailstorev1.services;


import com.joa.retailstorev1.model.Product;
import com.joa.retailstorev1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product get(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
