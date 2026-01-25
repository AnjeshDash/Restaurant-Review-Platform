package com.anjesh.restaurant.services;

import com.anjesh.restaurant.domain.ReviewCreateUpdateRequest;
import com.anjesh.restaurant.domain.entities.Review;
import com.anjesh.restaurant.domain.entities.User;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);
}
