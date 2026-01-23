package com.anjesh.restaurant.controllers;

import com.anjesh.restaurant.domain.dtos.RestaurantDto;
import com.anjesh.restaurant.domain.entities.Restaurant;
import com.anjesh.restaurant.mappers.RestaurantMapper;
import com.anjesh.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantService restaurantService;
  private final RestaurantMapper restaurantMapper;

  @GetMapping
  public Page<RestaurantDto> searchRestaurants(
      @RequestParam(required = false) String search,
      Pageable pageable) {
    Page<Restaurant> restaurants = restaurantService.searchRestaurants(search, pageable);
    return restaurants.map(restaurantMapper::toDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable String id) {
    return restaurantService.getRestaurantById(id)
        .map(restaurantMapper::toDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
    Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);
    Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
    return new ResponseEntity<>(restaurantMapper.toDto(createdRestaurant), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RestaurantDto> updateRestaurant(
      @PathVariable String id,
      @RequestBody RestaurantDto restaurantDto) {
    Restaurant restaurantKey = restaurantMapper.toEntity(restaurantDto);
    Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurantKey);
    return ResponseEntity.ok(restaurantMapper.toDto(updatedRestaurant));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity.noContent().build();
  }
}
