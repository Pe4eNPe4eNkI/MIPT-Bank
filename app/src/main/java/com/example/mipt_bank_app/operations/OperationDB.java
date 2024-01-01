package com.example.mipt_bank_app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.bill.Bill;

public class OperationDB extends SQLiteOpenHelper {
    private final SQLiteDatabase DB_;

    public OperationDB(Context context) {
        super(context, "MyDB_operations.db", null, 1);
        DB_ = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table OperationTable(" +
                "card_id_sender TEXT, " +
                "card_id_receiver TEXT, " +
                "sum TEXT, " +
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
            id = res.getString(Helper.operationDbColumnNumber.get("operation_id"));

        }
        return id;
    }

    public String getMaxId_pp() {
        long temp = Long.parseLong(getMaxId()) + 1;
        return Long.valueOf(temp).toString();
    }

    public Boolean insertUserData(Bill bill, String receiverBillId, String sum, String operationType) {

        ContentValues contentValues = new ContentValues();
        long result;
        if (!bill.getBillID().equals("") && !sum.equals("") && !bill.getPersonID().equals("") && !receiverBillId.equals("") && !operationType.equals("")) {
            contentValues.put("card_id_receiver", receiverBillId);
            contentValues.put("card_id_sender", bill.getPersonID());
            contentValues.put("sum", sum);
            contentValues.put("operation_type", operationType);
            contentValues.put("operation_id", getMaxId_pp());
            result = DB_.insert("OperationTable", null, contentValues);

        } else {
            return false;
        }
        return result != -1;
    }

    public Boolean deleteData(String operation_id) {
        long result = DB_.delete("OperationTable", "operation_id=?", new String[]{operation_id});
        return result == -1 ? false : true;
    }

    public Cursor getData() {
        Cursor cursor = DB_.rawQuery("Select * from OperationTable", null);
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor getOperations(String person_id) throws Exception{
        Cursor billCredit = Helper.billsDB.getBill(person_id, Helper.BILL_KIND_CREDIT);
        Cursor billDeposit = Helper.billsDB.getBill(person_id, Helper.BILL_KIND_DEPOSIT);
        Cursor billDebit = Helper.billsDB.getBill(person_id, Helper.BILL_KIND_DEBIT);

        String billCreditId = "";
        String billDepositId = "";
        String billDebitId = "";

        if(billCredit != null){
            billCredit.moveToFirst();
            billCreditId = billCredit.getString(Helper.billDbColumnNumber.get("bill_id"));
        }
        if(billDeposit != null){
            billDeposit.moveToFirst();
            billDepositId = billDeposit.getString(Helper.billDbColumnNumber.get("bill_id"));
        }
        if(billDebit != null){
            billDebit.moveToFirst();
            billDebitId = billDebit.getString(Helper.billDbColumnNumber.get("bill_id"));
        }
        Cursor cursor = null;
        if (!billCreditId.equals("")|| !billDebitId.equals("") || !billDepositId.equals("") ) {
            cursor = DB_.rawQuery("Select * from OperationTable where send = ?", new String[]{billCreditId, billDebitId, billDepositId});
        }
        return (cursor.getCount() == 0 ? null : cursor);
    }

    public Cursor find_bill(String operation_id) {
        Cursor cursor = DB_.rawQuery("Select * from OperationTable where operation_id = ? ", new String[]{operation_id});
        return (cursor.getCount() == 0 ? null : cursor);
    }


}
