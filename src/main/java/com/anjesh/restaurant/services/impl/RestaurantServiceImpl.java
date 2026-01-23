package com.anjesh.restaurant.services.impl;

import com.anjesh.restaurant.domain.entities.Restaurant;
import com.anjesh.restaurant.exceptions.BaseException;
import com.anjesh.restaurant.repositories.RestaurantRepository;
import com.anjesh.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Override
  public Page<Restaurant> searchRestaurants(String searchQuery, Pageable pageable) {
    // Simple findAll for now, or could implement robust search later
    return restaurantRepository.findAll(pageable);
  }

  @Override
  public Restaurant createRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Override
  public Optional<Restaurant> getRestaurantById(String id) {
    return restaurantRepository.findById(id);
  }

  @Override
  public Restaurant updateRestaurant(String id, Restaurant restaurantDetails) {
    return restaurantRepository.findById(id)
        .map(existing -> {
          // Update fields as needed. For simplicity, we might just copy properties or
          // assume mapper handles it
          // Ideally, do granular updates
          existing.setName(restaurantDetails.getName());
          existing.setCuisineType(restaurantDetails.getCuisineType());
          existing.setContactInformation(restaurantDetails.getContactInformation());
          existing.setAddress(restaurantDetails.getAddress());
          existing.setOperatingHours(restaurantDetails.getOperatingHours());
          return restaurantRepository.save(existing);
        })
        .orElseThrow(() -> new BaseException("Restaurant not found with id: " + id));
  }

  @Override
  public void deleteRestaurant(String id) {
    restaurantRepository.deleteById(id);
  }
}
