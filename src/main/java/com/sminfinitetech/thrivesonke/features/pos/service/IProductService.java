package com.sminfinitetech.thrivesonke.features.pos.service;

import com.sminfinitetech.thrivesonke.features.pos.model.Category;
import com.sminfinitetech.thrivesonke.features.pos.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);

    List<Product> getAllProducts();
    List<Product> getProductByCategory(Category category);
    List<Product> getProductByBrand(String brand);
}
