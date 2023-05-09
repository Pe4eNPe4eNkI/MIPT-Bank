package com.example.mipt_bank_app;


import android.database.Cursor;

public class withdrawal_operation extends i_easy_money_operation {
    public withdrawal_operation(bills_db trans, person_db pdb) {
        trans_ = trans;
        pdb_ = pdb;
    }

    @Override
    public void executeOperation(String receiver_id, String width_sum, String type) {
        Cursor cursor_bill = trans_.get_bill(receiver_id, type);
        cursor_bill.moveToFirst();
        String bill_id_from_bd = cursor_bill.getString(1);
        String money_from_bd = cursor_bill.getString(3);
        String field_for_bill_from_bd = cursor_bill.getString(4);

        Cursor cursor_person = pdb_.getPerson(receiver_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        bill_factory bf = new bill_factory();

        big_int balance = new big_int(money_from_bd);
        big_int width_sum_big = new big_int(width_sum);
        big_int money_limit = new big_int(constants.money_limit);
        big_int nul = new big_int(0);

        if ((flag && width_sum_big.operator_less_or_equal(money_limit) || !flag) && width_sum_big.operator_more_or_equal(nul)) {
            balance.operator_minus_equal(width_sum_big);
            if (type == constants.BILL_KIND_CREDIT) {

                big_int updeted_field = new big_int(field_for_bill_from_bd);
                updeted_field.operator_plus_equal(width_sum_big);
                credit cr = bf.build_credit(bill_id_from_bd, receiver_id, balance.toString(), updeted_field.toString());
                trans_.updateUserData(cr);

            } else if (type == constants.BILL_KIND_DEBIT) {

                debit db = bf.build_debit(bill_id_from_bd, receiver_id, balance.toString());
                trans_.updateUserData(db);

            } else if (type == constants.BILL_KIND_DEPOSIT) {

                deposit dp = bf.build_deposit(bill_id_from_bd, receiver_id, balance.toString());
                trans_.updateUserData(dp);

            }

        }
    }

    @Override
    public void cancelOperation(String receiver_bill_id, String money_size) {
        /*Cursor receiver = trans_.getBill("" + receiver_bill_id);
        int money = Integer.parseInt(receiver.getString(3).trim()) + money_size;
        trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + money);
    */
    }

    private bills_db trans_;
    person_db pdb_;
}
