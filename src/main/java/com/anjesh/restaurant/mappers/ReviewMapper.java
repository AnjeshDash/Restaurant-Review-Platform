package com.anjesh.restaurant.mappers;

import com.anjesh.restaurant.domain.dtos.ReviewDto;
import com.anjesh.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { PhotoMapper.class })
public interface ReviewMapper {
  ReviewDto toDto(Review review);

  Review toEntity(ReviewDto reviewDto);
}
