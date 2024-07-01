package com.dinhhieu.JavaSpringUltimate.service;

import com.dinhhieu.JavaSpringUltimate.domain.Product;
import com.dinhhieu.JavaSpringUltimate.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product handleSaveProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
