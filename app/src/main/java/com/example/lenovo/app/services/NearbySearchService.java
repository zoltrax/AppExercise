package com.example.lenovo.app.services;

import com.example.lenovo.app.data.model.NearbySearch;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 19.06.2017.
 */

public interface NearbySearchService {

    @GET("api/place/nearbysearch/json?key=AIzaSyATupoW49ysV-uT4wrjGEeMISARWesm5kI")
    Observable<NearbySearch> getNearbySearch(@Query("location") String location, @Query("radius") int radius);

}
