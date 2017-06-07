package com.creatic.particularteacherprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creatic.particularteacherprototype.models.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class RatingDao {
    //region constants
    private static final String TABLE_NAME = "rating";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_COMMENT = "comment";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RATING + " FLOAT, " +
            COLUMN_COMMENT + " VARCHAR)";
    //endregion

    SQLiteDatabase sqLiteDatabase;

    public RatingDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public boolean delete(long id){
        int nRows = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{Long.toString(id)});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public boolean insert(Rating rating){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RATING, rating.getRating());
        contentValues.put(COLUMN_COMMENT, rating.getComment());
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        rating.setId(id);
        boolean result = (id==-1) ? false : true;
        return result;
    }

    public boolean update(Rating rating){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RATING, rating.getRating());
        contentValues.put(COLUMN_COMMENT, rating.getComment());
        int nRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Long.toString(rating.getId())});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public List<Rating> selectAll(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorToList(cursor);
    }

    private Rating cursorToData(Cursor cursor){
        Rating rating = null;
        if(cursor.moveToNext()){
            rating = new Rating();
            rating.setId(cursor.getLong(0));
            rating.setRating(cursor.getFloat(1));
            rating.setComment(cursor.getString(2));
        }
        return rating;
    }

    private List<Rating> cursorToList(Cursor cursor){
        List<Rating> ratingList = new ArrayList<>();
        Rating rating;
        for(int i=0; i<cursor.getCount(); i++){
            rating = cursorToData(cursor);
            ratingList.add(rating);
        }
        return ratingList;
    }
}
