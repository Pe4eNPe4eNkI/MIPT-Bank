package com.example.mipt_bank_app.bill;


import com.example.mipt_bank_app.Helper;

public class BillFactory {

    public Deposit buildDeposit(String bill_id, String person_id, String money) {
        Deposit deposit_ = new Deposit();
        deposit_.setPersonID(person_id);
        deposit_.setBillID(bill_id);
        deposit_.setCashSize(money);
        return deposit_;
    }

    public Credit buildCredit(String bill_id, String person_id, String money) {
        Credit credit_ = new Credit(Helper.CREDIT_MONEY_LIMIT);
        credit_.setBillID(bill_id);
        credit_.setPersonID(person_id);
        credit_.setCashSize(money);
        return credit_;
    }

    public Debit buildDebit(String bill_id, String person_id, String money) {
        Debit debit_ = new Debit();
        debit_.setPersonID(person_id);
        debit_.setBillID(bill_id);
        debit_.setCashSize(money);
        return debit_;
    }
}
