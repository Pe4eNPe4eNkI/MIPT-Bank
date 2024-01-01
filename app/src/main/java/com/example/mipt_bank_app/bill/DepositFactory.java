package com.example.mipt_bank_app.bill;

public class DepositFactory extends BillFactory{
    @Override
    public Deposit buildBill(String bill_id, String person_id, String money) {
        Deposit deposit_ = new Deposit();
        deposit_.setPersonID(person_id);
        deposit_.setBillID(bill_id);
        if (money.equals("")) {
            deposit_.setCashSize("0.0");
            deposit_.setUniqueProperty("0.0");
        } else {
            deposit_.setCashSize(money);
        }
        deposit_.update();
        return deposit_;
    }
}
