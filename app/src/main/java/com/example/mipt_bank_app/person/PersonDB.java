package com.example.mipt_bank_app.person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.big_int.big_int;

public class PersonDB extends SQLiteOpenHelper {
    public PersonDB(Context context) {
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

    public String getMaxIdPP() {
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

    public Boolean insertUserData(Adult adult) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("login", adult.getLogin());
        contentValues.put("id", this.getMaxIdPP());
        contentValues.put("password", adult.getPassword());
        contentValues.put("first_name", adult.getName());
        contentValues.put("second_name", adult.getSurName());
        contentValues.put("address", adult.getAddress());
        contentValues.put("passport_id", adult.getPassportId());
        contentValues.put("money_limit", adult.getMoneyLimit());
        contentValues.put("is_doubtful", adult.getIsDoubtful());
        long result = DB.insert("PersonTable", null, contentValues);
        return result != -1;
    }

    public Boolean updateUserData(Adult adult) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        adult.updateStatus();
        if (!adult.getPassword().isEmpty()) {
            contentValues.put("password", adult.getPassword());
        }
        if (!adult.getName().isEmpty()) {
            contentValues.put("first_name", adult.getName());
        }
        if (!adult.getSurName().isEmpty()) {
            contentValues.put("second_name", adult.getSurName());
        }
        if (!adult.getAddress().isEmpty()) {
            contentValues.put("address", adult.getAddress());
        }
        if (!adult.getPassportId().isEmpty()) {
            contentValues.put("passport_id", adult.getPassportId());
        }
        contentValues.put("money_limit", adult.getMoneyLimit());
        contentValues.put("is_doubtful", adult.getIsDoubtful());

        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ?", new String[]{adult.getLogin()});
        if (cursor.getCount() > 0) {
            long result = DB.update("PersonTable", contentValues, "login=?", new String[]{adult.getLogin()});
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
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? ", new String[]{login});
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

    public Cursor get_person_by_id(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where id = ? ", new String[]{id});
        return (cursor.getCount() == 0 ? null : cursor);
    }
    public Cursor getPersonByLoginPassword(String login, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PersonTable where login = ? and password = ?", new String[]{login,password});
        return (cursor.getCount() == 0 ? null : cursor);
    }

}
