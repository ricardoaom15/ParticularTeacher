package com.creatic.particularteacherprototype.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.creatic.particularteacherprototype.models.Subject;
import com.creatic.particularteacherprototype.util.C;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, C.DATABASE_NAME, null, C.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SubjectDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(RatingDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(OfferDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(LocationDao.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
