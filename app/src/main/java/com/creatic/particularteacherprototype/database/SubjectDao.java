package com.creatic.particularteacherprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creatic.particularteacherprototype.models.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class SubjectDao {
    //region constants
    private static final String TABLE_NAME = "subject";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " VARCHAR)";
    //endregion

    SQLiteDatabase sqLiteDatabase;

    public SubjectDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public boolean delete(long id){
        int nRows = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[]{Long.toString(id)});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public boolean insert(Subject subject){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, subject.getTitle());
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        subject.setId(id);
        boolean result = (id==-1) ? false : true;
        return result;
    }

    public boolean update(Subject subject){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, subject.getTitle());
        int nRows = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Long.toString(subject.getId())});
        boolean result = (nRows>0) ? true : false;
        return result;
    }

    public List<Subject> selectAll(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorToList(cursor);
    }

    private Subject cursorToData(Cursor cursor){
        Subject subject = null;
        if(cursor.moveToNext()){
            subject = new Subject();
            subject.setId(cursor.getLong(0));
            subject.setTitle(cursor.getString(1));
        }
        return subject;
    }

    private List<Subject> cursorToList(Cursor cursor){
        List<Subject> subjectList = new ArrayList<>();
        Subject subject;
        for(int i=0; i<cursor.getCount(); i++){
            subject = cursorToData(cursor);
            subjectList.add(subject);
        }
        return subjectList;
    }
}
