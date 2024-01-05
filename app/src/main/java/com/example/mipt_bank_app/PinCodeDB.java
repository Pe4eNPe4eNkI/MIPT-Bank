package com.example.mipt_bank_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

public class PinCodeDB extends SQLiteOpenHelper {
    private final SQLiteDatabase DB_;
    public PinCodeDB(Context context) {
        super(context, "PinCode.db", null, 1);
        DB_ = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table PinCode(" +
                "login TEXT, " +
                "password TEXT, " +
                "PinCode TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists bill_table");
    }

    public Pair<Boolean, String> checkPerson() {
        Cursor cursor = DB_.rawQuery("Select * from PinCode", null);
        cursor.moveToFirst();
        return cursor.getCount() != 0 ? new Pair<Boolean, String>(cursor.getCount() != 0, cursor.getString(2)) : new Pair<Boolean, String>(cursor.getCount() != 0, "-1");
    }

    public Boolean checkPinCode(String PinCode) {
        Cursor cursor = DB_.rawQuery("Select * from PinCode where PinCode = ?", new String[]{PinCode});
        cursor.moveToFirst();
        return cursor.getCount() != 0;
    }

    public Cursor getPerson(String PinCode) {
        Cursor cursor = DB_.rawQuery("Select * from PinCode where PinCode = ?", new String[]{PinCode});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public boolean addPerson(String login, String password, String pinCode) {
        Cursor cursor = DB_.rawQuery("Select * from PinCode", null);

        if (cursor.getCount() > 0) {
            long result = DB_.delete("PinCode", null, null);
        }
        ContentValues cv = new ContentValues();
        cv.put("login", login);
        cv.put("password", password);
        cv.put("PinCode", pinCode);
        long result = DB_.insert("PinCode", null, cv);

        return result != -1;
    }

    public boolean deletePinCode(){
        Cursor cursor = DB_.rawQuery("Select * from PinCode", null);
        long result = new Long(0);
        if (cursor.getCount() > 0) {
            result = DB_.delete("PinCode", null, null);
        }
        return result != -1;
    }
}