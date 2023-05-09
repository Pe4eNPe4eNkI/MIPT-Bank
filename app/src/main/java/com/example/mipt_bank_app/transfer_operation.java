package com.example.mipt_bank_app;


import android.database.Cursor;

public class transfer_operation extends i_ne_easy_money_operation {
    public transfer_operation(bills_db trans, person_db pdb) {
        trans_ = trans;
        pdb_ = pdb;
    }

    @Override
    public void executeTransferOperation(String sender_id, String receiver_bill_id, String money_size, String type) {

        Cursor cursor_bill1 = trans_.get_bill(sender_id, type);
        cursor_bill1.moveToFirst();
        String bill_id1 = cursor_bill1.getString(1);
        String person_id1 = cursor_bill1.getString(2);
        String money1 = cursor_bill1.getString(3);
        String field_for_bill1 = cursor_bill1.getString(4);

        Cursor cursor_bill = trans_.get_bill(receiver_bill_id);
        cursor_bill.moveToFirst();
        String bill_id = cursor_bill.getString(1);
        String person_id = cursor_bill.getString(2);
        String money = cursor_bill.getString(3);
        String field_for_bill = cursor_bill.getString(4);

        /*Cursor cursor_person = pdb_.getPerson(receiver_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = Boolean.parseBoolean(is_doubtful);*/

        big_int temp = new big_int(money1);
        big_int temp1 = new big_int(money_size);
        big_int temp2 = new big_int(money);
        if (!temp.operator_less(temp1)) {

            temp.operator_minus_equal(temp1);
            temp2.operator_plus_equal(temp1);

            bill_factory bf = new bill_factory();

            if (type == constants.BILL_KIND_CREDIT) {
                big_int temp3 = new big_int(field_for_bill);
                big_int temp4 = new big_int(money_size);
                if (temp3.operator_more(temp4)) {
                    temp3.operator_minus_equal(temp4);
                    field_for_bill = temp1.toString();
                    credit cr = bf.build_credit(bill_id, person_id, money, field_for_bill);
                    trans_.updateUserData(cr);
                }
            } else if (type == constants.BILL_KIND_DEBIT) {
                debit db1 = bf.build_debit(bill_id1, person_id1, temp.toString());
                debit db2 = bf.build_debit(bill_id, person_id, temp2.toString());
                trans_.updateUserData(db1);
                trans_.updateUserData(db2);
            } else if (type == constants.BILL_KIND_DEPOSIT) {
                deposit db1 = bf.build_deposit(bill_id, person_id1, temp.toString());
                deposit db2 = bf.build_deposit(bill_id, person_id, temp2.toString());
                trans_.updateUserData(db1);
                trans_.updateUserData(db2);
            }
        }
    }


    @Override
    public void cancelTransferOperation(String send_bill_id,
                                        String receiver_bill_id,
                                        String money_size, String type) {


        /*try {
            Cursor receiver = trans_.getBill("" + receiver_bill_id);
            Cursor send = trans_.getBill("" + send_bill_id);

            if (Integer.parseInt(receiver.getString(3).trim()) < money_size) {
                throw new ArithmeticException("krasava, proizoshol scum :)");
            }
            int send_money = Integer.parseInt(receiver.getString(3).trim()) + money_size;
            int receiver_money = Integer.parseInt(receiver.getString(3).trim()) - money_size;

            trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + receiver_money);
            trans_.updateUserData(send.getString(0), send.getString(1), send.getString(2), "" + send_money);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }*/
    }

    private bills_db trans_;
    private person_db pdb_;

}
