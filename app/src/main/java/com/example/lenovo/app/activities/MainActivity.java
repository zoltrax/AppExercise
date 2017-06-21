package com.example.lenovo.app.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.app.R;
import com.example.lenovo.app.adapters.PlacesAdapter;
import com.example.lenovo.app.data.model.NearbySearch;
import com.example.lenovo.app.data.model.NearbySearchDto;
import com.example.lenovo.app.db.DatabaseHelper;
import com.example.lenovo.app.db.Place;
import com.example.lenovo.app.services.NearbySearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://maps.googleapis.com/maps/";
    private List<Place> placeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlacesAdapter mAdapter;
    Button refresh;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        db = DatabaseHelper.getDb(getApplicationContext());
        refresh = (Button) findViewById(R.id.refresh);

        mAdapter = new PlacesAdapter(db.getPlaces(), new PlacesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Place item) {
                Intent mapActivity = new Intent(MainActivity.this, MapActivity.class);
                mapActivity.putExtra("name", item.getName());
                mapActivity.putExtra("icon", item.getIcon());
                mapActivity.putExtra("address", item.getAddress());
                mapActivity.putExtra("lat", item.getLat());
                mapActivity.putExtra("lng", item.getLng());
                startActivity(mapActivity);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void refresh(View v) throws ExecutionException, InterruptedException {

        refresh.setEnabled(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NearbySearchService nearbySearchService = retrofit.create(NearbySearchService.class);
        Observable<NearbySearch> nearbySearchService2 = nearbySearchService.getNearbySearch("52,21", 1500);

        nearbySearchService2
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<NearbySearch>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull NearbySearch nearbySearch) {

                        try {
                            new ReloadData(nearbySearch.getResults()).execute().get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.v("error", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private class ReloadData extends AsyncTask<Object, Object, List<Place>> {

        List<NearbySearchDto> places;

        public ReloadData(List<NearbySearchDto> places) {
            this.places = places;
        }

        @Override
        protected List<Place> doInBackground(Object... params) {
            db.dropPlaceTable();
            db.insertPlaces(places);
            return db.getPlaces();
        }

        protected void onPostExecute(List<Place> places) {
            refresh.setEnabled(true);
            mAdapter.swap(places);
        }

    }
}
