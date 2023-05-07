package com.example.mipt_bank_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BillDB extends SQLiteOpenHelper {
    public BillDB(Context context) {
        super(context, "MyDB1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table BillTable(login TEXT primary key, bill_id TEXT, bill_kind TEXT, cash_size TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists BillTable");
    }

    public Boolean insertUserData(String login, String bill_id, String bill_kind, String cash_size) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("bill_id", bill_id);
        contentValues.put("bill_kind", bill_kind);
        contentValues.put("cash_size", cash_size);
        long result = DB.insert("BillTable", null, contentValues);
        if(result == -1) {
            return false;
        }
        return true;
    }

    public Boolean updateUserData(String login, String bill_id, String bill_kind, String cash_size) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bill_id", bill_id);
        contentValues.put("bill_kind", bill_kind);
        contentValues.put("cash_size", cash_size);
        Cursor cursor = DB.rawQuery("Select * from BillTable where login = ?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = DB.update("BillTable", contentValues, "login=?", new String[]{login});
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }
    public Boolean deleteData(String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from BillTable where login = ?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = DB.delete("BillTable", "login=?", new String[]{login});
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Cursor getData () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from BillTable", null);
        return cursor;
    }
    public Cursor getBill (String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from BillTable where login = ?", new String[]{login});
        return cursor;
    }
}
