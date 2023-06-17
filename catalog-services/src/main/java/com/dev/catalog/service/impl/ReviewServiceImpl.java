package com.dev.catalog.service.impl;

import com.dev.catalog.entity.Product;
import com.dev.catalog.entity.Review;
import com.dev.commons.exceptions.NotFoundException;
import com.dev.catalog.feign.CustomerClient;
import com.dev.catalog.repository.ReviewRepository;
import com.dev.catalog.service.ProductService;
import com.dev.catalog.service.ReviewService;
import com.dev.commons.utils.UpdatingUtil;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor @AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CustomerClient customerClient;
    private ProductService productService;

    @Override
    public Review create(Long productId, Review review) {
        customerClient.getById(review.getCustomerId()); // If customer doesn't exist feign will throw an exception
        Product product = productService.getById(productId);
        List<Review> reviews = product.getReviews();
        reviews.add(review);

        int sum = reviews.stream().mapToInt(Review::getRating).sum();
        float average = (float) sum / (float) reviews.size();

        product.setAverageStar(average);
        productService.update(product);
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Long reviewId, Long productId, Review review) {
        Review oldReview = getById(reviewId);
        BeanUtils.copyProperties(review, oldReview, UpdatingUtil.getNullPropertyNames(review));
        return create(productId, oldReview);
    }

    @Override
    public Review getById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    @Override
    public Page<Review> getByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByProduct_Id(productId, pageable);
    }

    @Override
    public Page<Review> getByCustomerId(Long customerId, Pageable pageable) {
        return reviewRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
