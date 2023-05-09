package com.example.mipt_bank_app;


import android.database.Cursor;

public class withdrawal_operation extends i_easy_money_operation {
    public withdrawal_operation(bills_db trans, person_db pdb) {
        trans_ = trans;
        pdb_ = pdb;
    }

    @Override
    public void executeOperation(String receiver_id, String money_size, String type) {
        Cursor cursor_bill = trans_.get_bill(receiver_id, type);
        cursor_bill.moveToFirst();
        String bill_id = cursor_bill.getString(1);
        String money = cursor_bill.getString(3);
        String field_for_bill = cursor_bill.getString(4);

       /* Cursor cursor_person = pdb_.getPerson(receiver_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = Boolean.parseBoolean(is_doubtful);*/

        bill_factory bf = new bill_factory();

        big_int temp1 = new big_int(money);
        big_int temp2 = new big_int(money_size);

        temp1.operator_minus_equal(temp2);

        String itogo_deneg = temp1.toString();
        big_int nul = new big_int(0);
        if (temp1.operator_more_or_equal(nul)) {
            if (type == constants.BILL_KIND_CREDIT) {
                big_int temp3 = new big_int(field_for_bill);
                big_int temp4 = new big_int(money_size);
                if (temp3.operator_more(temp4)) {
                    temp3.operator_plus_equal(temp4);
                    field_for_bill = temp1.toString();
                    credit cr = bf.build_credit(bill_id, receiver_id, money, field_for_bill);
                    trans_.updateUserData(cr);
                }
            } else if (type == constants.BILL_KIND_DEBIT) {
                debit db = bf.build_debit(bill_id, receiver_id, itogo_deneg);
                trans_.updateUserData(db);
            } else if (type == constants.BILL_KIND_DEPOSIT) {
                deposit dp = bf.build_deposit(bill_id, receiver_id, itogo_deneg);
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
