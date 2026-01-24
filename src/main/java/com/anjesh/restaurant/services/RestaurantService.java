package com.anjesh.restaurant.services;

import com.anjesh.restaurant.domain.RestaurantCreateUpdateRequest;
import com.anjesh.restaurant.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);

}
