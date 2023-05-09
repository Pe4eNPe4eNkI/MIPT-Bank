package com.example.mipt_bank_app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class person_db extends SQLiteOpenHelper {
    public person_db(Context context) {
        super(context, "MyDB_person.db", null, 1);
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
        big_int temp = new big_int(getMaxId());
        return temp.operator_pp_prefix().toString();
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

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists PersonTable");
    }

    public Boolean insertUserData(i_person person) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        person.set_id(this.getMaxId_pp());
        contentValues.put("login", person.get_login());
        contentValues.put("id", person.get_id());
        contentValues.put("password", person.get_password());
        contentValues.put("first_name", person.get_first_name());
        contentValues.put("second_name", person.get_second_name());
        contentValues.put("address", person.get_address());
        contentValues.put("passport_id", person.get_passport_id());
        contentValues.put("money_limit", person.get_money_limit());
        contentValues.put("is_doubtful", person.is_doubtful());
        long result = DB.insert("PersonTable", null, contentValues);
        return result != -1;
    }

    public Boolean updateUserData(i_person person) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        person.update();
        if (!person.get_password().isEmpty()) {
            contentValues.put("password", person.get_password());
        }
        if (!person.get_first_name().isEmpty()) {
            contentValues.put("first_name", person.get_first_name());
        }
        if (!person.get_second_name().isEmpty()) {
            contentValues.put("second_name", person.get_second_name());
        }
        if (!person.get_address().isEmpty()) {
            contentValues.put("address", person.get_address());
        }
        if (!person.get_passport_id().isEmpty()) {
            contentValues.put("passport_id", person.get_passport_id());
        }
        contentValues.put("money_limit", person.get_money_limit());
        contentValues.put("is_doubtful", person.is_doubtful());

        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{person.get_login()});
        if (cursor.getCount() > 0) {
            long result = DB.update("PersonTable", contentValues, "login=?", new String[]{person.get_login()});
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

    public Cursor getPerson(String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? ", new String[]{login,});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Boolean personFind(String login) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? ", new String[]{login});
        return cursor.getCount() != 0;
    }

    public Boolean personFind(String login, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? and password = ? ", new String[]{login, password});
        return cursor.getCount() != 0;
    }

}
