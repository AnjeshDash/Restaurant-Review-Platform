package com.anjesh.restaurant.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import com.anjesh.restaurant.domain.entities.Address;
import com.anjesh.restaurant.domain.entities.OperatingHours;
import com.anjesh.restaurant.domain.entities.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {

  private String id;
  private String name;
  private String cuisineType;
  private String contactInformation;
  private Float averageRating;
  private GeoPoint geoLocation;
  private Address address;
  private OperatingHours operatingHours;
  private List<PhotoDto> photos;
  private List<ReviewDto> reviews;
  private User createdBy;
}
