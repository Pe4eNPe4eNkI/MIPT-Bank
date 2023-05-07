package com.example.mipt_bank_app;

import java.security.PublicKey;

public class Debit extends IBill {

    @Override
    public String getBillKind() {
        return bill_kind_;
    }

    @Override
    public int getCashSize() {
        return cash_size_;
    }

    public int getCashbackPotentialSize() {
        return cashback_potential_size_;
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

    public void setCashbackPotentialSize(int cashback_potential_size) {
        cashback_potential_size_ = cashback_potential_size;
    }

    @Override
    public void setPersonId(int person_id) {
        person_id_ = person_id;
    }

    public void update() {
        cashback_potential_size_ = cash_size_ / 100 * 5;
    }

    @Override
    public void assignId() {

    }

    private String bill_kind_ = SupConstants.BILL_KIND_DEBIT;
    private int cashback_potential_size_;
}
