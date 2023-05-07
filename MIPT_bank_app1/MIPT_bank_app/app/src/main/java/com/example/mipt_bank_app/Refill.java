package com.example.mipt_bank_app;


import android.database.Cursor;

public class Refill extends IEasyMoneyOperation {
    public Refill(BillDB trans) { this.trans_ = trans; }

    @Override
    public void executeOperation(int receiver_bill_id, int money_size) {
        Cursor receiver = trans_.getBill("" + receiver_bill_id);
        int money = Integer.parseInt(receiver.getString(3).trim()) + money_size;
        trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + money);
    }

    @Override
    public void cancelOperation(int receiver_bill_id, int money_size) {
        try {
            Cursor receiver = trans_.getBill("" + receiver_bill_id);

            if (Integer.parseInt(receiver.getString(3).trim()) < money_size) {
                throw new ArithmeticException("fuck you, lox!");
            }
            int money = Integer.parseInt(receiver.getString(3).trim()) - money_size;
            trans_.updateUserData(receiver.getString(0), receiver.getString(1), receiver.getString(2), "" + money);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }


    private BillDB trans_;
}
