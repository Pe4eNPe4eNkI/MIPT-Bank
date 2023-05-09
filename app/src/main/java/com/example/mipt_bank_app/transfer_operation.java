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
        String sender_bill_id = cursor_bill1.getString(1);
        String sender_money = cursor_bill1.getString(3);
        String sender_field_for_bill = cursor_bill1.getString(4);

        Cursor cursor_bill = trans_.get_bill(receiver_bill_id);
        cursor_bill.moveToFirst();
        String receiver_bill_kind = cursor_bill.getString(0);
        String receiver_person_id = cursor_bill.getString(2);
        String receiver_money = cursor_bill.getString(3);
        String receiver_field_for_bill = cursor_bill.getString(4);

        Cursor cursor_person = pdb_.getPerson(sender_id);
        cursor_person.moveToFirst();
        String is_doubtful = cursor_person.getString(8);
        boolean flag = (is_doubtful.equals("1") ? true : false);

        big_int sender_balance_big = new big_int(sender_money);
        big_int receiver_balance_big = new big_int(receiver_money);
        big_int transfer_size = new big_int(money_size);
        big_int money_limit = new big_int(constants.money_limit);

        if (receiver_bill_id != sender_bill_id) {
            if (sender_balance_big.operator_more_or_equal(transfer_size) && (flag && transfer_size.operator_less_or_equal(money_limit) || !flag)) {

                sender_balance_big.operator_minus_equal(transfer_size);
                receiver_balance_big.operator_plus_equal(transfer_size);
                bill_factory bf = new bill_factory();

                if (type == constants.BILL_KIND_CREDIT) {
                    big_int sender_field_for_bill_big = new big_int(sender_field_for_bill);
                    big_int receiver_field_for_bill_big = new big_int(receiver_field_for_bill);
                    sender_field_for_bill_big.operator_minus_equal(transfer_size);
                    credit sender = bf.build_credit(sender_bill_id, sender_id, sender_balance_big.toString(), sender_field_for_bill_big.toString());
                    trans_.updateUserData(sender);
                    i_bill receiver = null;
                    if (receiver_bill_kind.equals("debit")) {
                        receiver = bf.build_debit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("deposit")) {
                        receiver = bf.build_deposit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("credit")) {
                        receiver_field_for_bill_big.operator_minus_equal(transfer_size);
                        receiver = bf.build_credit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString(), receiver_field_for_bill_big.toString());
                    }
                    trans_.updateUserData(receiver);
                } else if (type == constants.BILL_KIND_DEBIT) {
                    debit sender = bf.build_debit(sender_bill_id, sender_id, sender_balance_big.toString());
                    trans_.updateUserData(sender);
                    i_bill receiver = null;
                    if (receiver_bill_kind.equals("debit")) {
                        receiver = bf.build_debit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("deposit")) {
                        receiver = bf.build_deposit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("credit")) {
                        big_int receiver_field_for_bill_big = new big_int(receiver_field_for_bill);
                        receiver_field_for_bill_big.operator_minus_equal(transfer_size);
                        receiver = bf.build_credit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString(), receiver_field_for_bill_big.toString());
                    }
                    trans_.updateUserData(receiver);
                } else if (type == constants.BILL_KIND_DEPOSIT) {
                    deposit sender = bf.build_deposit(sender_bill_id, sender_id, sender_balance_big.toString());
                    trans_.updateUserData(sender);

                    i_bill receiver = null;

                    if (receiver_bill_kind.equals("debit")) {
                        receiver = bf.build_debit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("deposit")) {
                        receiver = bf.build_deposit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString());
                    } else if (receiver_bill_kind.equals("credit")) {
                        big_int receiver_field_for_bill_big = new big_int(receiver_field_for_bill);
                        receiver_field_for_bill_big.operator_minus_equal(transfer_size);
                        receiver = bf.build_credit(receiver_bill_id, receiver_person_id, receiver_balance_big.toString(), receiver_field_for_bill_big.toString());
                    }
                    trans_.updateUserData(receiver);
                }
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
