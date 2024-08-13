package com.changeside.demo_furniture_website.services.product;

import com.changeside.demo_furniture_website.models.dto.request.ProductRequest;
import com.changeside.demo_furniture_website.models.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    String deleteProduct(Long id);
}
