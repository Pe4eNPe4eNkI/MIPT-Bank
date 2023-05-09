package com.example.mipt_bank_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class operation_db extends SQLiteOpenHelper {
    public operation_db(Context context) {
        super(context, "MyDB2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table OperationTable(" +
                "card_id TEXT primary key, " +
                "sum TEXT, " +
                "send TEXT, " +
                "receiver TEXT, " +
                "operation_type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists OperationTable");
    }

    public Boolean insertUserData(String card_id,
                                  String sum,
                                  String send,
                                  String receiver,
                                  String operation_type) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("card_id", card_id);
        contentValues.put("sum", sum);
        contentValues.put("send", send);
        contentValues.put("receiver", receiver);
        contentValues.put("operation_type", operation_type);

        long result = DB.insert("OperationTable", null, contentValues);
        return result != -1;
    }


    public Boolean deleteData(String card_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable where card_id = ?", new String[]{card_id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("OperationTable", "card_id=?", new String[]{card_id});

            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable", null);
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor getOperation(String card_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable where card_id = ?", new String[]{card_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

}
