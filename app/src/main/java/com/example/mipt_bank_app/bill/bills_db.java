package com.example.mipt_bank_app.bill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.big_int.big_int;
import com.example.mipt_bank_app.person.IPerson;

public class bills_db extends SQLiteOpenHelper {

    public bills_db(Context context) {
        super(context, "MyDB_bill.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table bill_table(" +
                "bill_kind TEXT, " +
                "bill_id TEXT PRIMARY KEY, " +
                "person_id TEXT, " +
                "balance TEXT, " +
                "field_for_bill TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists bill_table");
    }

    public Boolean updateUserData(i_bill bill) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (!bill.get_bill_kind().isEmpty()) {
            contentValues.put("bill_kind", bill.get_bill_kind());
        }
        if (!bill.get_person_id().isEmpty()) {
            contentValues.put("person_id", bill.get_person_id());
        }
        if (!bill.get_cash_size().isEmpty()) {
            contentValues.put("balance", bill.get_cash_size());
        }
        if (!bill.get_specific_bill_field().isEmpty()) {
            contentValues.put("field_for_bill", bill.get_specific_bill_field());
        }

        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_id = ?", new String[]{bill.get_bill_id()});
        if (cursor.getCount() > 0) {
            long result = DB.update("bill_table", contentValues, "bill_id=?", new String[]{bill.get_bill_id()});
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

    public String getMaxId_pp() {
        big_int temp = new big_int(getMaxId());
        return temp.operator_pp_prefix().toString();
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table", null);
        return cursor;
    }

    public boolean add_bill(i_bill bill) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_kind", bill.get_bill_kind());
        contentValues.put("bill_id", getMaxId_pp());
        contentValues.put("person_id", bill.get_person_id());
        contentValues.put("balance", bill.get_cash_size());
        contentValues.put("field_for_bill", bill.get_specific_bill_field());

        long result = DB.insert("bill_table", null, contentValues);
        return result != -1;
    }

    public Cursor get_bill(String person_id, String bill_kind) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ? ", new String[]{bill_kind, person_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor get_bill(String bill_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_id = ? ", new String[]{bill_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public boolean try_find_bill(IPerson person, String bill_kind) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from bill_table where bill_kind = ? and person_id = ?", new String[]{bill_kind, person.getID()});
        return cursor.getCount() != 0;
    }
}
