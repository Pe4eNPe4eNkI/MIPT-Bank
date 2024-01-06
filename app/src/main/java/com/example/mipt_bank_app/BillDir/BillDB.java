package com.example.mipt_bank_app.BillDir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.Person.Person;

public class BillDB extends SQLiteOpenHelper {

    private final SQLiteDatabase DB_;

    public BillDB(Context context) {
        super(context, "MyDB_bill.db", null, 1);
        DB_ = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table bill_table(" +
                "bill_kind TEXT, " +
                "bill_id TEXT PRIMARY KEY, " +
                "person_id TEXT, " +
                "balance TEXT, " +
                "debt TEXT, " +
                "percent TEXT, " +
                "spent_money TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists bill_table");
    }

    public Boolean updateUserData(Bill bill) {
        ContentValues contentValues = new ContentValues();
        if (!bill.getCashSize().isEmpty()) {
            contentValues.put("balance", bill.getCashSize());
        }
        if(bill.getUniqueProperty().getSecond() != null && !bill.getUniqueProperty().getSecond().isEmpty()) {
            contentValues.put(bill.getUniqueProperty().getFirst(), bill.getUniqueProperty().getSecond());
        }
        Cursor cursor = DB_.rawQuery("Select * from bill_table where bill_id = ?", new String[]{bill.getBillID()});
        if (cursor.getCount() > 0) {
            long result = DB_.update("bill_table", contentValues, "bill_id=?", new String[]{bill.getBillID()});
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getMaxId() {
        Cursor res = this.getData();
        if (res.getCount() == 0) {
            return "0";
        }
        String id = new String();
        while (res.moveToNext()) {
            id = res.getString(Helper.billDbColumnNumber.get("bill_id"));

        }
        return id;
    }

    public String getMaxIdPP() {
        long temp =  Long.parseLong(getMaxId()) + 1;
        return Long.valueOf(temp).toString();
    }

    public Cursor getData() {
        Cursor cursor = DB_.rawQuery("Select * from bill_table", null);
        return cursor;
    }

    public boolean addBill(Bill bill) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_kind", bill.getBillKind());
        contentValues.put("bill_id", getMaxIdPP());
        contentValues.put("person_id", bill.getPersonID());
        contentValues.put("balance", bill.getCashSize());

        contentValues.put(bill.getUniqueProperty().getFirst(), bill.getUniqueProperty().getSecond());

        long result = DB_.insert("bill_table", null, contentValues);
        return result != -1;
    }

    public Cursor getBill(String person_id, String bill_kind) {
        Cursor cursor = DB_.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ? ", new String[]{bill_kind, person_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor getBill(String bill_id) {
        Cursor cursor = DB_.rawQuery("Select * from bill_table where bill_id = ? ", new String[]{bill_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public boolean tryFindBill(Person person, String bill_kind) {
        Cursor cursor = DB_.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ?", new String[]{bill_kind, person.getID()});
        return cursor.getCount() != 0;
    }
}
