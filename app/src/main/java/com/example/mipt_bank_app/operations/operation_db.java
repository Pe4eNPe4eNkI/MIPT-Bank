package com.example.mipt_bank_app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.BigInt.BigInt;
import com.example.mipt_bank_app.bill.i_bill;

public class
operation_db extends SQLiteOpenHelper {
    public operation_db(Context context) {
        super(context, "MyDB2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table OperationTable(" +
                "card_id_sender TEXT, " +
                "card_id_receiver TEXT, " +
                "sum TEXT, " +
                "send TEXT, " +
                "receiver TEXT, " +
                "operation_type TEXT, " +
                "operation_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists OperationTable");
    }

    public String getMaxId() {
        Cursor res = this.getData();
        if (res == null || res.getCount() == 0) {
            return "0";
        }
        String id = new String();
        while (res.moveToNext()) {
            id = res.getString(6);

        }
        return id;
    }

    public String getMaxId_pp() {
        BigInt temp = new BigInt(getMaxId());
        return temp.operator_prefix_increment().toString();
    }

    public Boolean insertUserData(i_bill bill, String sum, String receiver, String type_oper, String reciver_bill_id, String sender_bill_id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!bill.get_bill_id().equals("")) {
            contentValues.put("card_id_receiver", reciver_bill_id);
        }
        if (!bill.get_bill_id().equals("")) {
            contentValues.put("card_id_sender", sender_bill_id);
        }
        if (!sum.equals("")) {
            contentValues.put("sum", sum);
        }
        if (!bill.get_person_id().equals("")) {
            contentValues.put("send", bill.get_person_id());
        }
        if (!receiver.equals("")) {
            contentValues.put("receiver", receiver);
        }
        if (!bill.get_bill_kind().equals("")) {
            contentValues.put("operation_type", type_oper);
        }
        contentValues.put("operation_id", getMaxId_pp());

        long result = DB.insert("OperationTable", null, contentValues);
        return result != -1;
    }
    public Boolean insertUserData(i_bill bill, String sum, String receiver, String type_oper, String reciver_bill_id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!bill.get_bill_id().equals("")) {
            contentValues.put("card_id_sender", "");
        }
        if (!bill.get_bill_id().equals("")) {
            contentValues.put("card_id_receiver", reciver_bill_id);
        }
        if (!sum.equals("")) {
            contentValues.put("sum", sum);
        }
        if (!bill.get_person_id().equals("")) {
            contentValues.put("send", bill.get_person_id());
        }
        if (!receiver.equals("")) {
            contentValues.put("receiver", receiver);
        }
        if (!bill.get_bill_kind().equals("")) {
            contentValues.put("operation_type", type_oper);
        }
        contentValues.put("operation_id", getMaxId_pp());

        long result = DB.insert("OperationTable", null, contentValues);
        return result != -1;
    }

    public Boolean deleteData(String operation_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("OperationTable", "operation_id=?", new String[]{operation_id});
        return result == -1 ? false : true;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable", null);
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor getOperations(String person_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable where send = ?", new String[]{person_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor find_bill(String operation_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from OperationTable where operation_id = ? ", new String[]{operation_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }


}
