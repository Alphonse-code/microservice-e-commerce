package com.dev.catalog.mapper;

import com.dev.catalog.dto.request.ReviewRequest;
import com.dev.commons.mapper.BasicMapper;

import com.dev.catalog.dto.response.ReviewResponse;
import com.dev.catalog.entity.Review;
import com.dev.catalog.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMapper {

	private BasicMapper basicMapper;
	private ReviewService reviewService;

	public ReviewResponse create(ReviewRequest review) {
		Review r = basicMapper.convertTo(review, Review.class);
		return basicMapper.convertTo(reviewService.create(review.getProductId(), r), ReviewResponse.class);
	}

	public ReviewResponse update(Long reviewId, ReviewRequest review) {
		Review r = basicMapper.convertTo(review, Review.class);
		return basicMapper.convertTo(reviewService.update(reviewId, review.getProductId(), r), ReviewResponse.class);
	}

	public ReviewResponse getById(Long reviewId) {
		return basicMapper.convertTo(reviewService.getById(reviewId), ReviewResponse.class);
	}

	public Page<ReviewResponse> getByProductId(Long productId, Pageable pageable) {
		return reviewService.getByProductId(productId, pageable)
				.map(review -> basicMapper.convertTo(review, ReviewResponse.class));
	}

	public Page<ReviewResponse> getByCustomerId(Long customerId, Pageable pageable) {
		return reviewService.getByCustomerId(customerId, pageable)
				.map(review -> basicMapper.convertTo(review, ReviewResponse.class));
	}

	public void deleteById(Long reviewId) {
		reviewService.deleteById(reviewId);
	}
}
