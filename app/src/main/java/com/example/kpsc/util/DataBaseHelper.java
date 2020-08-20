package com.example.kpsc.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kpsc.MainActivity;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String ManagerHelper ="Helper" ;
    public static final String DESCR_NOTE ="note" ;
    public static final String Operation_ID ="id_operation" ;

    public DataBaseHelper(@Nullable Context context) {
        super(context, ManagerHelper, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableOperation ="CREATE TABLE operation ( id INTEGER PRIMARY KEY AUTOINCREMENT, operation_name VARCHAR(50), \n" +
                "                    id_type_work integer ,wip_before integer ,wip_after Integer ,\n" +
                "                    total_time Integer,id_list_pause integer ,image_path String,\n" +
                "                   FOREIGN KEY (id_type_work) REFERENCES typework(id),\n" +
                "                   FOREIGN KEY (id_list_pause)REFERENCES pause(id))";
        String createTablePause ="CREATE TABLE pause ( id INTEGER PRIMARY KEY AUTOINCREMENT, time_pause Integer,image_path String)";
        String createTableTapeOfWork ="CREATE TABLE type_work( id INTEGER PRIMARY KEY AUTOINCREMENT, time_pause Integer,image_path String)";
        String createTableRoute ="CREATE TABLE route ( id INTEGER PRIMARY KEY AUTOINCREMENT,route_name VARCHAR(50) NOT NULL UNIQUE,id_operation INTEGER,image_path String," +
                "FOREIGN KEY (id_operation) REFERENCES operation(id))";
        String createTableDescription ="CREATE TABLE description ( id INTEGER PRIMARY KEY AUTOINCREMENT,"+DESCR_NOTE+" TEXT," +Operation_ID+" INTEGER," +
                "FOREIGN KEY (id_operation) REFERENCES operation(id))";
        db.execSQL(createTableOperation);
        db.execSQL(createTablePause);
        db.execSQL(createTableTapeOfWork);
        db.execSQL(createTableRoute);
        db.execSQL(createTableDescription);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
