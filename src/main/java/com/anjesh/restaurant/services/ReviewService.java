package com.anjesh.restaurant.services;

import com.anjesh.restaurant.domain.ReviewCreateUpdateRequest;
import com.anjesh.restaurant.domain.entities.Review;
import com.anjesh.restaurant.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);

    Page<Review> listReviews(String restaurantId, Pageable pageable);
}
