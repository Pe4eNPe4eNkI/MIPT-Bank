package com.example.mipt_bank_app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class PersonDB extends SQLiteOpenHelper {
    public PersonDB(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table PersonTable(" +
                "login TEXT primary key, " +
                "id TEXT, " +
                "password TEXT, " +
                "first_name TEXT, " +
                "second_name TEXT, " +
                "address TEXT, " +
                "passport_id TEXT, " +
                "money_limit TEXT, " +
                "is_doubtful TEXT)");
    }

    public String getMaxId_pp() {
        Cursor res = this.getData();
        if (res.getCount() == 0) {
            return "1";
        }
        String id = new String();
        while (res.moveToNext()) {
            id = res.getString(1);
        }
        big_int temp = new big_int(id);
        return temp.operator_pp_prefix().toString();
    }

    public String getMaxId() {
        Cursor res = this.getData();
        if (res.getCount() == 0) {
            return "0";
        }
        String id = new String();
        while (res.moveToNext()) {
            id = ("Id: " + res.getString(1) + "\n");

        }
        return id;
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

        String is_doubtful = address.isEmpty() && passport_id.isEmpty() ? "bad" : "good";
        String money_limit = is_doubtful.equals("bad") ? "1000" : "10000000000000000";
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("login", login);
        contentValues.put("id", this.getMaxId_pp());
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("second_name", second_name);
        contentValues.put("address", address);
        contentValues.put("passport_id", passport_id);
        contentValues.put("money_limit", money_limit);
        contentValues.put("is_doubtful", is_doubtful);
        long result = DB.insert("PersonTable", null, contentValues);
        return result != -1;
    }

    public Boolean updateUserData(String login,
                                  String password,
                                  String first_name,
                                  String second_name,
                                  String address,
                                  String passport_id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (!password.isEmpty()) {
            contentValues.put("password", password);
        }
        if (!first_name.isEmpty()) {
            contentValues.put("first_name", first_name);
        }
        if (!second_name.isEmpty()) {
            contentValues.put("second_name", second_name);
        }
        if (!address.isEmpty()) {
            contentValues.put("address", address);
        }
        if (!passport_id.isEmpty()) {
            contentValues.put("passport_id", passport_id);
        }
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

    public Boolean deleteData(String login) {
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

    public Cursor getPerson(String login, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? and password = ? ", new String[]{login, password});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Boolean personFind(String login, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? and password = ? ", new String[]{login, password});
        if (cursor.getCount() == 0) {
            return false;
        }
        return true;
    }

}
