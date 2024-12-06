package com.sminfinitetech.thrivesonke.features.pos.service;

import com.sminfinitetech.thrivesonke.features.pos.exception.ProductNotFoundException;
import com.sminfinitetech.thrivesonke.features.pos.model.Category;
import com.sminfinitetech.thrivesonke.features.pos.model.Product;
import com.sminfinitetech.thrivesonke.features.pos.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(productRepository::delete,
                ()-> {throw
            new ProductNotFoundException("Product Not Found");
        });
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategoryName(category.getName());
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }
}
