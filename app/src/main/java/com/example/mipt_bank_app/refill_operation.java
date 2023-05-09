package com.example.mipt_bank_app;


import android.database.Cursor;

public class refill_operation extends i_easy_money_operation {
    public refill_operation(bills_db trans, person_db person_db) {
        this.trans_ = trans;
        person_db_ = person_db;
    }

    @Override
    public void executeOperation(String receiver_id, String refill_sum, String type) {
        Cursor cursor_bill = trans_.get_bill(receiver_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_db = cursor_bill.getString(1);
        String money_from_db = cursor_bill.getString(3);
        String field_for_bill_from_db = cursor_bill.getString(4);

        Cursor cursor_person = person_db_.getPerson(receiver_id);
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

            if (type == constants.BILL_KIND_CREDIT) {

                big_int updeted_field = new big_int(field_for_bill_from_db);
                updeted_field.operator_minus_equal(refill_sum_big);
                credit cr = bf.build_credit(bill_id_from_db, receiver_id, money_from_db.toString(), updeted_field.toString());
                trans_.updateUserData(cr);

            } else if (type == constants.BILL_KIND_DEBIT) {

                debit db = bf.build_debit(bill_id_from_db, receiver_id, user_balance.toString());
                trans_.updateUserData(db);

            } else if (type == constants.BILL_KIND_DEPOSIT) {

                deposit dp = bf.build_deposit(bill_id_from_db, receiver_id, user_balance.toString());
                trans_.updateUserData(dp);

            }
        }
    }

    @Override
    public void cancelOperation(String receiver_bill_id, String money_size) {
        /*try {
            Cursor receiver = trans_.getBill("" + receiver_bill_id);

            if (Integer.parseInt(receiver.getString(3).trim()) < money_size) {
                throw new ArithmeticException("fuck you, lox!");
            }
            int money = Integer.parseInt(receiver.getString(3).trim()) - money_size;
            trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + money);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }*/
    }


    private bills_db trans_;
    private person_db person_db_;
}
