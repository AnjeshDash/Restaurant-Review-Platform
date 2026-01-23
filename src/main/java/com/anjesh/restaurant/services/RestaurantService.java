package com.anjesh.restaurant.services;

import com.anjesh.restaurant.domain.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RestaurantService {
  Page<Restaurant> searchRestaurants(String searchQuery, Pageable pageable);

  Restaurant createRestaurant(Restaurant restaurant);

  Optional<Restaurant> getRestaurantById(String id);

  Restaurant updateRestaurant(String id, Restaurant restaurant);

  void deleteRestaurant(String id);
}
