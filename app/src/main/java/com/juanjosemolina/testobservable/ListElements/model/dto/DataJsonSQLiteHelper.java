package com.juanjosemolina.testobservable.ListElements.model.dto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan José on 04/03/2017.
 */

public class DataJsonSQLiteHelper extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE dataJson (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, idJson TEXT, name TEXT, description TEXT, language TEXT, urlImage TEXT)";

    public DataJsonSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //se elimina la version anterior de la tabla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS dataJson");

        //se vuelve a crear una nueva version de la tabla
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
