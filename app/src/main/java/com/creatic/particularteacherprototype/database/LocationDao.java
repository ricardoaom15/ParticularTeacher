package com.creatic.particularteacherprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creatic.particularteacherprototype.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class LocationDao {

    //region constants
    private static final String TABLE_NAME = "location";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OFFER_ID = "offer_id";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_OFFER_ID + " INTEGER, " +
            COLUMN_LATITUDE + " FLOAT, " +
            COLUMN_LONGITUDE + " FLOAT)";
    //endregion

    SQLiteDatabase sqLiteDatabase;

    public LocationDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public boolean delete(long id){
        int nRows = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{Long.toString(id)});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public boolean insert(Location location){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_OFFER_ID, location.getOfferId());
        contentValues.put(COLUMN_LATITUDE, location.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, location.getLongitude());
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        location.setId(id);
        boolean result = (id==-1) ? false : true;
        return result;
    }

    public boolean update(Location location){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_OFFER_ID, location.getOfferId());
        contentValues.put(COLUMN_LATITUDE, location.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, location.getLongitude());
        int nRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Long.toString(location.getId())});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public List<Location> selectAll(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorToList(cursor);
    }

    private Location cursorToData(Cursor cursor){
        Location location = null;
        if(cursor.moveToNext()){
            location = new Location();
            location.setId(cursor.getLong(0));
            location.setOfferId(cursor.getLong(1));
            location.setLatitude(cursor.getFloat(2));
            location.setLongitude(cursor.getFloat(3));
        }
        return location;
    }

    private List<Location> cursorToList(Cursor cursor){
        List<Location> locationList = new ArrayList<>();
        Location location;
        for(int i=0; i<cursor.getCount(); i++){
            location = cursorToData(cursor);
            locationList.add(location);
        }
        return locationList;
    }

}
