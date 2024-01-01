package com.example.mipt_bank_app.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.Helper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PersonDB extends SQLiteOpenHelper {

    private final SQLiteDatabase DB_;
    public PersonDB(Context context) {
        super(context, "MyDB_person.db", null, 1);
        DB_ = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table PersonTable(" +
                "login TEXT primary key, " +
                "id TEXT, " +
                "password TEXT, " +
                "salt TEXT, " +
                "first_name TEXT, " +
                "second_name TEXT, " +
                "address TEXT, " +
                "passport_id TEXT, " +
                "money_limit TEXT, " +
                "is_doubtful TEXT)");
    }

    public String getMaxIdPP() {
        long temp =  Long.parseLong(getMaxId()) + 1;
        return Long.valueOf(temp).toString();
    }

    public String getMaxId() {
        Cursor res = this.getData();
        if (res.getCount() == 0) {
            return "0";
        }
        String id = new String();
        while (res.moveToNext()) {
            id = res.getString(Helper.personDbColumnNumber.get("id"));

        }
        return id;
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists PersonTable");
    }

    public Boolean insertUserData(Adult adult) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

        ContentValues contentValues = new ContentValues();

        contentValues.put("login", adult.getLogin());
        contentValues.put("id", this.getMaxIdPP());
        contentValues.put("first_name", adult.getName());
        contentValues.put("second_name", adult.getSurname());
        contentValues.put("address", adult.getAddress());
        contentValues.put("passport_id", adult.getPassportId());
        contentValues.put("money_limit", adult.getMoneyLimit());
        contentValues.put("is_doubtful", adult.getIsDoubtful());

        Helper.stringHash.generateSalt();
        String hash = Helper.stringHash.generateHash(adult.getPassword());
        contentValues.put("password", hash);

        contentValues.put("salt", Helper.stringHash.getSalt());

        long result = DB_.insert("PersonTable", null, contentValues);
        return result != -1;
    }

    public Boolean updateUserData(Adult adult) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

        ContentValues contentValues = new ContentValues();
        adult.updateStatus();
        if (!adult.getPassword().isEmpty()) {
            Helper.stringHash.generateSalt();
            String hash = Helper.stringHash.generateHash(adult.getPassword());
            contentValues.put("password", hash);
            contentValues.put("salt", Helper.stringHash.getSalt());
        }
        if (!adult.getName().isEmpty()) {
            contentValues.put("first_name", adult.getName());
        }
        if (!adult.getSurname().isEmpty()) {
            contentValues.put("second_name", adult.getSurname());
        }
        if (!adult.getAddress().isEmpty()) {
            contentValues.put("address", adult.getAddress());
        }
        if (!adult.getPassportId().isEmpty()) {
            contentValues.put("passport_id", adult.getPassportId());
        }
        contentValues.put("money_limit", adult.getMoneyLimit());
        contentValues.put("is_doubtful", adult.getIsDoubtful());

        Cursor cursor = DB_.rawQuery("Select * from PersonTable where login = ?", new String[]{adult.getLogin()});
        if (cursor.getCount() > 0) {
            long result = DB_.update("PersonTable", contentValues, "login=?", new String[]{adult.getLogin()});
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Boolean deleteData(String login) {
        Cursor cursor = DB_.rawQuery("Select * from PersonTable where login = ?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = DB_.delete("PersonTable", "login=?", new String[]{login});

            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Cursor getData() {
        return DB_.rawQuery("Select * from PersonTable", null);
    }

    public Cursor getPerson(String login) {
        Cursor cursor = DB_.rawQuery("Select * from PersonTable where login = ? ", new String[]{login});
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Boolean personFind(String login) {
        Cursor cursor = DB_.rawQuery("Select * from PersonTable where login = ? ", new String[]{login});
        return cursor.getCount() != 0;
    }

    public Cursor getPersonById(String id) {
        Cursor cursor = DB_.rawQuery("Select * from PersonTable where id = ? ", new String[]{id});
        return (cursor.getCount() == 0 ? null : cursor);
    }

}
