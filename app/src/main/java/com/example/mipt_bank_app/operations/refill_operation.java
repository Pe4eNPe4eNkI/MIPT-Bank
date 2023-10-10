package com.example.mipt_bank_app.operations;


import android.database.Cursor;

import com.example.mipt_bank_app.big_int.big_int;
import com.example.mipt_bank_app.bill.bill_factory;
import com.example.mipt_bank_app.bill.bills_db;
import com.example.mipt_bank_app.bill.i_bill;
import com.example.mipt_bank_app.constants;
import com.example.mipt_bank_app.person.person_db;

public class refill_operation extends i_easy_money_operation {
    public refill_operation(bills_db trans, person_db person_db, operation_db odb) {
        this.trans_ = trans;
        person_db_ = person_db;
        odb_ = odb;
    }

    @Override
    public void executeOperation(String receiver_id, String refill_sum, String type) {
        Cursor cursor_bill = trans_.get_bill(receiver_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_db = cursor_bill.getString(1);

        String money_from_db = cursor_bill.getString(3);
        String field_for_bill_from_db = cursor_bill.getString(4);

        Cursor cursor_person = person_db_.get_person_by_id(receiver_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        big_int refill_sum_big = new big_int(refill_sum);
        big_int money_limit = new big_int(constants.money_limit);
        big_int user_balance = new big_int(money_from_db);
        big_int nul = new big_int(0);

        if ((flag && refill_sum_big.operator_less_or_equal(money_limit) || !flag) && refill_sum_big.operator_more_or_equal(nul)) {
            bill_factory bf = new bill_factory();

            user_balance.operator_plus_equal(refill_sum_big);

            i_bill bill = null;
            if (type == constants.BILL_KIND_CREDIT) {

                big_int updeted_field = new big_int(field_for_bill_from_db);
                updeted_field.operator_minus_equal(refill_sum_big);
                bill = bf.build_credit(bill_id_from_db, receiver_id, user_balance.toString(), updeted_field.toString());
            } else if (type == constants.BILL_KIND_DEBIT) {
                bill = bf.build_debit(bill_id_from_db, receiver_id, user_balance.toString());
            } else if (type == constants.BILL_KIND_DEPOSIT) {
                bill = bf.build_deposit(bill_id_from_db, receiver_id, user_balance.toString());
            }
            odb_.insertUserData(bill, refill_sum.toString(), receiver_id, constants.REFIL, bill_id_from_db);
            trans_.updateUserData(bill);
        }
    }

    @Override
    public void cancelOperation(String sender_bill_id, String width_sum, String type) {
        Cursor cursor_bill = trans_.get_bill(sender_bill_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_bd = cursor_bill.getString(1);
        String money_from_bd = cursor_bill.getString(3);
        String field_for_bill_from_bd = cursor_bill.getString(4);

        Cursor cursor_person = person_db_.get_person_by_id(sender_bill_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        bill_factory bf = new bill_factory();

        big_int balance = new big_int(money_from_bd);
        big_int width_sum_big = new big_int(width_sum);
        big_int money_limit = new big_int(constants.money_limit);
        big_int nul = new big_int(0);

        if ((flag && width_sum_big.operator_less_or_equal(money_limit) || !flag) && width_sum_big.operator_more_or_equal(nul) && balance.operator_more_or_equal(width_sum_big)) {
            balance.operator_minus_equal(width_sum_big);
            i_bill bill = null;
            if (type.equals(constants.BILL_KIND_CREDIT)) {
                big_int updeted_field = new big_int(field_for_bill_from_bd);
                updeted_field.operator_plus_equal(width_sum_big);
                bill = bf.build_credit(bill_id_from_bd, sender_bill_id, balance.toString(), updeted_field.toString());
            } else if (type.equals(constants.BILL_KIND_DEBIT)) {
                bill = bf.build_debit(bill_id_from_bd, sender_bill_id, balance.toString());
            } else if (type.equals(constants.BILL_KIND_DEPOSIT)) {
                bill = bf.build_deposit(bill_id_from_bd, sender_bill_id, balance.toString());
            }

            trans_.updateUserData(bill);
        }
    }


    private bills_db trans_;
    private person_db person_db_;
}
