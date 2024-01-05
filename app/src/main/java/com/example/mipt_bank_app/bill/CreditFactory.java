package com.example.mipt_bank_app.bill;

import com.example.mipt_bank_app.Helper;

public class CreditFactory extends BillFactory{
    @Override
    public Credit buildBill(String bill_id, String person_id, String money) {
        Credit credit_ = new Credit(Helper.CREDIT_MONEY_LIMIT);
        credit_.setBillID(bill_id);
        credit_.setPersonID(person_id);
        if (money.equals("")) {
            credit_.setCashSize(Helper.CREDIT_MONEY_LIMIT);
            credit_.setUniqueProperty("0.0");
        } else {
            credit_.setCashSize(money);
        }
        credit_.update();
        return credit_;
    }
}
