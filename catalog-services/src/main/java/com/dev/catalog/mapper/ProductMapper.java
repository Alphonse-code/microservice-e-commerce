package com.dev.catalog.mapper;

import com.dev.commons.dto.catalog.product.ProductRequest;
import com.dev.commons.dto.catalog.product.ProductResponse;
import com.dev.commons.mapper.BasicMapper;
import com.dev.catalog.entity.Product;
import com.dev.catalog.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor @NoArgsConstructor
public class ProductMapper {

    private BasicMapper basicMapper;
    private ProductService productService;

    public Page<ProductResponse> getProducts(Pageable pageable) {
        return productService.getAll(pageable).map(product -> basicMapper.convertTo(product, ProductResponse.class));
    }

    public ProductResponse getProductById(Long productId) {
        return basicMapper.convertTo(productService.getById(productId), ProductResponse.class);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productService.create(basicMapper.convertTo(productRequest, Product.class), productRequest.getCategoryId());
        return basicMapper.convertTo(product, ProductResponse.class);
    }

    public ProductResponse uploadImages(MultipartFile[] images, Long productId) {
        return basicMapper.convertTo(productService.uploadImages(images, productId), ProductResponse.class);
    }

    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productService.update(productId, basicMapper.convertTo(productRequest, Product.class), productRequest.getCategoryId());
        return basicMapper.convertTo(product, ProductResponse.class);
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}
