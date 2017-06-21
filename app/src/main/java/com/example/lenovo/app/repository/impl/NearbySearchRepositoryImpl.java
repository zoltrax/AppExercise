package com.example.lenovo.app.repository.impl;

import android.content.Context;

import com.example.lenovo.app.data.model.NearbySearch;
import com.example.lenovo.app.repository.NearbySearchRepository;
import com.example.lenovo.app.services.NearbySearchService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 19.06.2017.
 */
@Singleton
public class NearbySearchRepositoryImpl implements NearbySearchRepository {

    private NearbySearchService service;
    private Context context;

    @Inject
    public NearbySearchRepositoryImpl(NearbySearchService service, Context context) {
        this.service = service;
        this.context = context;
    }

    @Override
    public Observable<NearbySearch> getNearbySearch(String location, int radius, String key) {
        Observable<NearbySearch> nearbySearch = service
                .getNearbySearch(location,
                        radius);

        return nearbySearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
