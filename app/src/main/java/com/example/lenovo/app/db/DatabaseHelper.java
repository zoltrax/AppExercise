package com.example.lenovo.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lenovo.app.data.model.NearbySearchDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    Context ctx;
    private static DatabaseHelper mInstance = null;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getDb(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx);
        }
        return mInstance;
    }

    public static final String TABLE_NAME_PLACES = "places";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_ICON = "icon";
    public static final String FIELD_NAME_ADDRESS = "address";
    public static final String FIELD_NAME_LAT = "lat";
    public static final String FIELD_NAME_LNG = "lng";

    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_NAME_PLACES + "( " + FIELD_NAME_ID
            + " text, " + FIELD_NAME_NAME
            + " text, " + FIELD_NAME_ICON + " text, " + FIELD_NAME_ADDRESS + " text," + FIELD_NAME_LAT + " text," + FIELD_NAME_LNG + " text);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dropPlaceTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME_PLACES);
    }

    public void insertPlaces(List<NearbySearchDto> placeList) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < placeList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(FIELD_NAME_ID, placeList.get(i).getId());
            values.put(FIELD_NAME_NAME, placeList.get(i).getName());
            values.put(FIELD_NAME_ICON, placeList.get(i).getIcon());
            values.put(FIELD_NAME_ADDRESS, placeList.get(i).getVicinity());
            values.put(FIELD_NAME_LAT, placeList.get(i).getGeometry().getLocation().getLat());
            values.put(FIELD_NAME_LNG, placeList.get(i).getGeometry().getLocation().getLng());

            db.insert(TABLE_NAME_PLACES, null, values);
        }

    }

    public List<Place> getPlaces() {
        SQLiteDatabase db = this.getReadableDatabase();
        String checkPlaces = "SELECT * FROM " + TABLE_NAME_PLACES;
        List<Place> places = new ArrayList<Place>();
        Cursor c1 = db.rawQuery(checkPlaces, null);

        if (c1 != null)
            c1.moveToFirst();

        while (c1.isAfterLast() == false) {
            Place place = new Place();
            place.setId(c1.getString(c1.getColumnIndex(FIELD_NAME_ID)));
            place.setName(c1.getString(c1.getColumnIndex(FIELD_NAME_NAME)));
            place.setIcon(c1.getString(c1.getColumnIndex(FIELD_NAME_ICON)));
            place.setAddress(c1.getString(c1.getColumnIndex(FIELD_NAME_ADDRESS)));
            place.setLat(c1.getString(c1.getColumnIndex(FIELD_NAME_LAT)));
            place.setLng(c1.getString(c1.getColumnIndex(FIELD_NAME_LNG)));
            places.add(place);

            c1.moveToNext();
        }

        return places;
    }
}
