package com.creatic.particularteacherprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creatic.particularteacherprototype.models.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class OfferDao {

    //region constants
    private static final String TABLE_NAME = "offer";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SUBJECT_ID = "subject_id";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_ID + " INTEGER, " +
            COLUMN_SUBJECT_ID + " INTEGER, " +
            COLUMN_TITLE + " VARCHAR, " +
            COLUMN_PRICE + " INTEGER)";
    //endregion

    SQLiteDatabase sqLiteDatabase;

    public OfferDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public boolean delete(long id){
        int nRows = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{Long.toString(id)});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public boolean insert(Offer offer){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, offer.getUserId());
        contentValues.put(COLUMN_SUBJECT_ID, offer.getSubjectId());
        contentValues.put(COLUMN_TITLE, offer.getTitle());
        contentValues.put(COLUMN_PRICE, offer.getPrice());
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        offer.setId(id);
        boolean result = (id==-1) ? false : true;
        return result;
    }

    public boolean update(Offer offer){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, offer.getUserId());
        contentValues.put(COLUMN_SUBJECT_ID, offer.getSubjectId());
        contentValues.put(COLUMN_TITLE, offer.getTitle());
        contentValues.put(COLUMN_PRICE, offer.getPrice());
        int nRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Long.toString(offer.getId())});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public List<Offer> selectAll(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorToList(cursor);
    }

    private Offer cursorToData(Cursor cursor){
        Offer offer = null;
        if(cursor.moveToNext()){
            offer = new Offer();
            offer.setId(cursor.getLong(0));
            offer.setUserId(cursor.getLong(1));
            offer.setSubjectId(cursor.getLong(2));
            offer.setTitle(cursor.getString(3));
            offer.setPrice(cursor.getLong(4));
        }
        return offer;
    }

    private List<Offer> cursorToList(Cursor cursor){
        List<Offer> offerList = new ArrayList<>();
        Offer offer;
        for(int i=0; i<cursor.getCount(); i++){
            offer = cursorToData(cursor);
            offerList.add(offer);
        }
        return offerList;
    }

}


