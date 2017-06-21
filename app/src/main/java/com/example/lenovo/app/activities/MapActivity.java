package com.example.lenovo.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.app.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    TextView nameTextView;
    TextView addressTextView;
    SimpleDraweeView iconSimpleDrawerView;
    String latPos;
    String lngPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameTextView = (TextView) findViewById(R.id.name);
        addressTextView = (TextView) findViewById(R.id.address);
        iconSimpleDrawerView = (SimpleDraweeView) findViewById(R.id.icon);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String icon = intent.getStringExtra("icon");
        latPos = intent.getStringExtra("lat");
        lngPos = intent.getStringExtra("lng");

        Uri uri = Uri.parse(icon);

        nameTextView.setText(name);
        addressTextView.setText(address);
        iconSimpleDrawerView.setImageURI(uri);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lat = Double.parseDouble(latPos);
        double lng = Double.parseDouble(lngPos);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("LinkedIn")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 10));

    }
}
