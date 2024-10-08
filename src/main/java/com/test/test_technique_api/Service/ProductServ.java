package com.test.test_technique_api.Service;

import com.test.test_technique_api.Entity.Product;
import com.test.test_technique_api.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServ {
    @Autowired
    private ProductRepo productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setCode(productDetails.getCode());
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setImage(productDetails.getImage());
                    product.setCategory(productDetails.getCategory());
                    product.setPrice(productDetails.getPrice());
                    product.setQuantity(productDetails.getQuantity());
                    product.setInternalReference(productDetails.getInternalReference());
                    product.setShellId(productDetails.getShellId());
                    product.setInventoryStatus(productDetails.getInventoryStatus());
                    product.setRating(productDetails.getRating());
                    product.setCreatedAt(productDetails.getCreatedAt());
                    product.setUpdatedAt(productDetails.getUpdatedAt());
                    return productRepository.save(product);
                });
    }

    public Optional<Product> partialUpdateProduct(Long id, Map<String, Object> updates) {
        return productRepository.findById(id)
                .map(product -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Product.class, key);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, product, value);
                    });
                    return productRepository.save(product);
                });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
