    package com.anjesh.restaurant.services;

    import com.anjesh.restaurant.domain.Geolocation;
    import com.anjesh.restaurant.domain.entities.Address;

    public interface GeolocationService {
        Geolocation geolocate(Address address);
    }
