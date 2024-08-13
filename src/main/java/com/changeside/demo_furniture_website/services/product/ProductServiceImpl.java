package com.changeside.demo_furniture_website.services.product;

import com.changeside.demo_furniture_website.repository.ProductRepository;
import com.changeside.demo_furniture_website.exception.ProductNotFoundException;
import com.changeside.demo_furniture_website.models.dto.request.ProductRequest;
import com.changeside.demo_furniture_website.models.dto.response.ProductResponse;
import com.changeside.demo_furniture_website.models.entity.Product;
import com.changeside.demo_furniture_website.services.image.ImageDataService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ImageDataService imageDataService;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        modelMapper.map(productRequest, product);

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductResponse.class);
    }

    @Override
    public String deleteProduct(Long id) {

        String title = getProductById(id).getName();
        productRepository.deleteById(id);
        imageDataService.removeImageByProductId(id);


        return title + " successfully deleted.";}
    }
