package com.example.rupak.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseStorage extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "4th.db";
    public static final String TABLE_NAME = "info_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";


    public DatabaseStorage(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDRESS TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String name, String address) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_2, name);
        cv.put(COL_3, address);

        db.insert("info_table", null, cv);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public ArrayList<UserDto> retrieveDatas() {

        ArrayList<UserDto> userList = new ArrayList<>();
        UserDto userDto = new UserDto();

        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from info_table";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToFirst()) {
//                HashMap<String, String> map = new HashMap<>();
//
//                map.put("ID", cursor.getString(cursor.getColumnIndex(COL_1)));
//                map.put("NAME", cursor.getString(cursor.getColumnIndex(COL_2)));
//                map.put("ADDRESS", cursor.getString(cursor.getColumnIndex(COL_3)));
//                userList.add(map);
//                UserDto userDto = new UserDto(cursor.getString(cursor.getColumnIndex(COL_1)),
//                    cursor.getString(cursor.getColumnIndex(COL_2)),cursor.getString(cursor.getColumnIndex(COL_3)));
            userDto.setId(cursor.getString(cursor.getColumnIndex(COL_1)));
            userDto.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
            userDto.setAddress(cursor.getString(cursor.getColumnIndex(COL_3)));
            userList.add(userDto);
        }


        return userList;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}
