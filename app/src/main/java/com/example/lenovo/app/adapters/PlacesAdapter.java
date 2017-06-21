package com.example.lenovo.app.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.app.R;
import com.example.lenovo.app.db.Place;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {

    List<Place> placesList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Place item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            icon = (SimpleDraweeView) view.findViewById(R.id.icon);
        }

        public void bind(final Place item, final OnItemClickListener listener) {
            name.setText(item.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public PlacesAdapter(List<Place> placesList, OnItemClickListener listener) {
        this.placesList = placesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(placesList.get(position), listener);
        Place place = placesList.get(position);
        holder.name.setText(place.getName());
        holder.address.setText(place.getAddress());
        Uri uri = Uri.parse(place.getIcon());
        holder.icon.setImageURI(uri);
    }

    public void swap(List<Place> datas) {
        placesList.clear();
        placesList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }
}
