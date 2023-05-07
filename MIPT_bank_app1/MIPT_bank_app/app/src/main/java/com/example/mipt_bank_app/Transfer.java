package com.example.mipt_bank_app;


import android.database.Cursor;

public class Transfer extends INeEasyMoneyOperation {
    public Transfer(BillDB trans) {
        this.trans_ = trans;
    }

    @Override
    public void executeTransferOperation(int send_bill_id,
                                         int receiver_bill_id,
                                         int money_size) {

        try {
            Cursor receiver = trans_.getBill("" + receiver_bill_id);
            Cursor send = trans_.getBill("" + send_bill_id);

            if (Integer.parseInt(send.getString(3).trim()) < money_size) {
                throw new ArithmeticException("fuck you, lox!");
            }
            int send_money = Integer.parseInt(receiver.getString(3).trim()) - money_size;
            int receiver_money = Integer.parseInt(receiver.getString(3).trim()) + money_size;

            trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + receiver_money);
            trans_.updateUserData(send.getString(0), send.getString(1), send.getString(2), "" + send_money);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    @Override
    public void cancelTransferOperation(int send_bill_id,
                                        int receiver_bill_id,
                                        int money_size) {


        try {
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
        }
    }

    private BillDB trans_;
}
