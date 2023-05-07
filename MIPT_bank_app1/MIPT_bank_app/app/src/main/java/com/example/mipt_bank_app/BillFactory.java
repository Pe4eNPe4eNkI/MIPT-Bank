package com.example.mipt_bank_app;


public class BillFactory {

    public Deposit buildDeposit(int person_id) {
        Deposit deposit_ = new Deposit();
        deposit_.setPersonId(person_id);
        deposit_.setCashSize(0);
        deposit_.setIncomePotentialSize(deposit_.getCashSize() / 100 * 10);
        deposit_.assignId();
        return deposit_;
    }

    public Credit buildCredit(int person_id) {
        Credit credit_ = new Credit();
        credit_.setPersonId(person_id);
        credit_.setCashSize(100000000);
        credit_.setAccessibleCashSize(100000000);
        credit_.setDebtSize(0);
        credit_.setPaymentOnThisMonth(0);
        credit_.assignId();
        return credit_;
    }

    public Debit buildDebit(int person_id) {
        Debit debit_ = new Debit();
        debit_.setPersonId(person_id);
        debit_.setCashSize(0);
        debit_.setCashbackPotentialSize(debit_.getCashSize() / 100 * 5);
        debit_.assignId();
        return debit_;
    }
}
