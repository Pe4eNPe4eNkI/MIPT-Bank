package com.example.mipt_bank_app;


public class Deposit extends IBill {
    @Override
    public String getBillKind() {
        return bill_kind_;
    }

    @Override
    public int getCashSize() {
        return cash_size_;
    }

    public int getIncomePotentialSize() {
        return income_potential_size_;
    }

    @Override
    public int getBillId() {
        return bill_id_;
    }

    @Override
    public int getPersonId() {
        return person_id_;
    }

    @Override
    public void setCashSize(int cash_size) {
        cash_size_ = cash_size;
    }

    @Override
    public void setBillId(int id) {
        bill_id_ = id;
    }

    public void setIncomePotentialSize(int income_potential_size) {
        income_potential_size_ = income_potential_size;
    }

    @Override
    public void setPersonId(int person_id) {
        person_id_ = person_id;
    }

    void update() {
        income_potential_size_ = cash_size_ / 100 * 10;
    }

    @Override
    public void assignId() {

    }

    private String bill_kind_ = SupConstants.BILL_KIND_DEPOSIT;
    private int income_potential_size_;

}
