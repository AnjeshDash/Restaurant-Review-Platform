package com.anjesh.restaurant.mappers;

import com.anjesh.restaurant.domain.ReviewCreateUpdateRequest;
import com.anjesh.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.anjesh.restaurant.domain.dtos.ReviewDto;
import com.anjesh.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toDto(Review review);

}
