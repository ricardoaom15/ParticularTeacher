package com.creatic.particularteacherprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creatic.particularteacherprototype.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class UserDao {

    //region constants
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_AVATAR = "avatar";
    private static final String COLUMN_BANNER = "banner";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_USERNAME + " VARCHAR, " +
            COLUMN_EMAIL + " VARCHAR, " +
            COLUMN_PASSWORD + " VARCHAR, " +
            COLUMN_PHONE + " VARCHAR, " +
            COLUMN_AVATAR + " VARCHAR, " +
            COLUMN_BANNER + " VARCHAR)";
    //endregion

    SQLiteDatabase sqLiteDatabase;

    public UserDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public boolean delete(long id){
        int nRows = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{Long.toString(id)});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public boolean insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_PHONE, user.getPhone());
        contentValues.put(COLUMN_AVATAR, user.getAvatar());
        contentValues.put(COLUMN_BANNER, user.getBanner());
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        user.setId(id);
        boolean result = (id==-1) ? false : true;
        return result;
    }

    public boolean update(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_PHONE, user.getPhone());
        contentValues.put(COLUMN_AVATAR, user.getAvatar());
        contentValues.put(COLUMN_BANNER, user.getBanner());
        int nRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Long.toString(user.getId())});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public List<User> selectAll(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorToList(cursor);
    }

    private User cursorToData(Cursor cursor){
        User user = null;
        if(cursor.moveToNext()){
            user = new User();
            user.setId(cursor.getLong(0));
            user.setName(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setPhone(cursor.getString(5));
            user.setAvatar(cursor.getString(6));
            user.setBanner(cursor.getString(7));
        }
        return user;
    }

    private List<User> cursorToList(Cursor cursor){
        List<User> userList = new ArrayList<>();
        User user;
        for(int i=0; i<cursor.getCount(); i++){
            user = cursorToData(cursor);
            userList.add(user);
        }
        return userList;
    }
}
