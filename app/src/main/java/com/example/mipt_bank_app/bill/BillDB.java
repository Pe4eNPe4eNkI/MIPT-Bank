package com.example.mipt_bank_app.bill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.Person.Person;

public class BillDB extends SQLiteOpenHelper {

    public BillDB(Context context) {
        super(context, "MyDB_bill.db", null, 1);
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

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (!bill.getBillKind().isEmpty()) {
            contentValues.put("bill_kind", bill.getBillKind());
        }
        if (!bill.getPersonID().isEmpty()) {
            contentValues.put("person_id", bill.getPersonID());
        }
        if (!bill.getCashSize().isEmpty()) {
            contentValues.put("balance", bill.getCashSize());
        }
        contentValues.put(bill.getUniqueProperty().first, bill.getUniqueProperty().second);

        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_id = ?", new String[]{bill.getBillID()});
        if (cursor.getCount() > 0) {
            long result = DB.update("bill_table", contentValues, "bill_id=?", new String[]{bill.getBillID()});
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
            id = res.getString(1);

        }
        return id;
    }

    public String getMaxIdPP() {
        long temp = new Long(getMaxId()) + 1;
        return new Long(temp).toString();
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table", null);
        return cursor;
    }

    public boolean addBill(Bill bill) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_kind", bill.getBillKind());
        contentValues.put("bill_id", getMaxIdPP());
        contentValues.put("person_id", bill.getPersonID());
        contentValues.put("balance", bill.getCashSize());

        contentValues.put(bill.getUniqueProperty().first, bill.getUniqueProperty().second);

        long result = DB.insert("bill_table", null, contentValues);
        return result != -1;
    }

    public Cursor getBill(String person_id, String bill_kind) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ? ", new String[]{bill_kind, person_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor getBill(String bill_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_id = ? ", new String[]{bill_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public boolean tryFindBill(Person person, String bill_kind) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ?", new String[]{bill_kind, person.getID()});
        return cursor.getCount() != 0;
    }
}
