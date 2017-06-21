package com.example.lenovo.app.repository;

import com.example.lenovo.app.data.model.NearbySearch;

import io.reactivex.Observable;

/**
 * Created by Lenovo on 19.06.2017.
 */

public interface NearbySearchRepository {

    Observable<NearbySearch> getNearbySearch(String location, int radius, String key);
}
