package com.example.mipt_bank_app.bill;

public class DebitFactory extends BillFactory{
    @Override
    public Debit buildBill(String bill_id, String person_id, String money) {
        Debit debit_ = new Debit();
        debit_.setPersonID(person_id);
        debit_.setBillID(bill_id);
        if (money.equals("")) {
            debit_.setCashSize("0.0");
            debit_.setUniqueProperty("0.0");
        } else {
            debit_.setCashSize(money);
        }
        debit_.update();
        return debit_;
    }
}
