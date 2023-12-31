package com.example.mipt_bank_app.operations;


import android.database.Cursor;

import com.example.mipt_bank_app.BigInt.BigInt;
import com.example.mipt_bank_app.bill.BillFactory;
import com.example.mipt_bank_app.bill.BillDB;
import com.example.mipt_bank_app.bill.i_bill;
import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.Person.PersonDB;

public class withdrawal_operation extends i_easy_money_operation {
    public withdrawal_operation(BillDB trans, PersonDB pdb, operation_db odb) {
        trans_ = trans;
        pdb_ = pdb;
        odb_ = odb;
    }

    @Override
    public void executeOperation(String receiver_id, String width_sum, String type) {
        Cursor cursor_bill = trans_.getBill(receiver_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_bd = cursor_bill.getString(1);
        String money_from_bd = cursor_bill.getString(3);
        String field_for_bill_from_bd = cursor_bill.getString(4);

        Cursor cursor_person = pdb_.get_person_by_id(receiver_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        BillFactory bf = new BillFactory();

        BigInt balance = new BigInt(money_from_bd);
        BigInt width_sum_big = new BigInt(width_sum);
        BigInt money_limit = new BigInt(Helper.money_limit);
        BigInt nul = new BigInt(0);

        if ((flag && width_sum_big.operatorLessOrEqual(money_limit) || !flag) && width_sum_big.operatorMoreOrEqual(nul) && balance.operatorMoreOrEqual(width_sum_big)) {
            balance.operatorMinusEqual(width_sum_big);
            i_bill bill = null;
            if (type == Helper.BILL_KIND_CREDIT) {

                BigInt updeted_field = new BigInt(field_for_bill_from_bd);
                updeted_field.operatorPlusEqual(width_sum_big);
                bill = bf.buildCredit(bill_id_from_bd, receiver_id, balance.toString(), updeted_field.toString());
            } else if (type == Helper.BILL_KIND_DEBIT) {
                bill = bf.buildDebit(bill_id_from_bd, receiver_id, balance.toString());
            } else if (type == Helper.BILL_KIND_DEPOSIT) {
                bill = bf.buildDeposit(bill_id_from_bd, receiver_id, balance.toString());
            }
            odb_.insertUserData(bill, width_sum, receiver_id, Helper.WITHDRAWAL, bill_id_from_bd);
            trans_.updateUserData(bill);
        }
    }

    @Override
    public void cancelOperation(String sender_bill_id, String refill_sum, String type) {
        Cursor cursor_bill = trans_.getBill(sender_bill_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_db = cursor_bill.getString(1);
        String money_from_db = cursor_bill.getString(3);
        String field_for_bill_from_db = cursor_bill.getString(4);

        Cursor cursor_person = pdb_.get_person_by_id(sender_bill_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        BigInt refill_sum_big = new BigInt(refill_sum);
        BigInt money_limit = new BigInt(Helper.money_limit);
        BigInt user_balance = new BigInt(money_from_db);
        BigInt nul = new BigInt(0);

        if ((flag && refill_sum_big.operatorLessOrEqual(money_limit) || !flag) && refill_sum_big.operatorMoreOrEqual(nul)) {
            BillFactory bf = new BillFactory();

            user_balance.operatorPlusEqual(refill_sum_big);

            i_bill bill = null;
            if (type.equals(Helper.BILL_KIND_CREDIT)) {

                BigInt updeted_field = new BigInt(field_for_bill_from_db);
                updeted_field.operatorMinusEqual(refill_sum_big);
                bill = bf.buildCredit(bill_id_from_db, sender_bill_id, user_balance.toString(), updeted_field.toString());
            } else if (type.equals(Helper.BILL_KIND_DEBIT)) {
                bill = bf.buildDebit(bill_id_from_db, sender_bill_id, user_balance.toString());
            } else if (type.equals(Helper.BILL_KIND_DEPOSIT)) {
                bill = bf.buildDeposit(bill_id_from_db, sender_bill_id, user_balance.toString());
            }
            trans_.updateUserData(bill);
        }
    }

    private BillDB trans_;
    PersonDB pdb_;
}
