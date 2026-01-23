package com.anjesh.restaurant.mappers;

import com.anjesh.restaurant.domain.dtos.RestaurantDto;
import com.anjesh.restaurant.domain.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { PhotoMapper.class,
    ReviewMapper.class })
public interface RestaurantMapper {
  RestaurantDto toDto(Restaurant restaurant);

  Restaurant toEntity(RestaurantDto restaurantDto);
}
