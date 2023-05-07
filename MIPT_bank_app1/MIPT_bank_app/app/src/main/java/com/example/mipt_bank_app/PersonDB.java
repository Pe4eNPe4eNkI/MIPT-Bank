package com.example.mipt_bank_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDB extends SQLiteOpenHelper {
    public PersonDB(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table PersonTable(" +
                    "login TEXT primary key, " +
                    "password TEXT, " +
                    "first_name TEXT, " +
                    "second_name TEXT, " +
                    "address TEXT, " +
                    "passport_id TEXT, " +
                    "money_limit TEXT, " +
                    "is_doubtful TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists PersonTable");
    }

    public Boolean insertUserData(String login,
                                  String password,
                                  String first_name,
                                  String second_name,
                                  String address,
                                  String passport_id) {

        String money_limit = "10000$";
        String is_doubtful = "Norm";
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("second_name", second_name);
        contentValues.put("address", address);
        contentValues.put("passport_id", passport_id);
        contentValues.put("money_limit", money_limit);
        contentValues.put("is_doubtful", is_doubtful);
        long result = DB.insert("PersonTable", null, contentValues);
        if(result == -1) {
            return false;
        }
        return true;
    }
    public Boolean updateUserData(String login,
                                  String password,
                                  String first_name,
                                  String second_name,
                                  String address,
                                  String passport_id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("second_name", second_name);
        contentValues.put("address", address);
        contentValues.put("passport_id", passport_id);

        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = DB.update("PersonTable", contentValues, "login=?", new String[]{login});
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }
    public Boolean deleteData (String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = DB.delete("PersonTable", "login=?", new String[]{login});

            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable", null);
        return cursor;
    }
    public Cursor getPerson(String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        return cursor;
    }

    public Boolean personFind(String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        if (cursor.getCount() == 0) {
            return false;
        }
        return true;
    }

    public Boolean find(String login, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        if (cursor.getString(1).equals(password)) {
            return true;
        }
        return false;
    }
}
