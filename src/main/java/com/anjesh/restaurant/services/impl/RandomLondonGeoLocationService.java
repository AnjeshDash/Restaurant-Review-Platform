package com.anjesh.restaurant.services.impl;

import com.anjesh.restaurant.domain.Geolocation;
import com.anjesh.restaurant.domain.entities.Address;
import com.anjesh.restaurant.services.GeolocationService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomLondonGeoLocationService implements GeolocationService {

    private static final float MIN_LATITUDE = 51.28f;
    private static final float MAX_LATITUDE = 51.686f;
    private static final float MIN_LONGITUDE = -0.489f;
    private static final float MAX_LONGITUDE = 0.236f;

    @Override
    public Geolocation geolocate(Address address) {  // ← Changed from "geolocation" to "geolocate"
        Random random = new Random();
        double latitude = MIN_LATITUDE + random.nextDouble() * (MAX_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);

        return Geolocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}