package com.example.mipt_bank_app;



abstract class IBill {

    public static int bill_id;

    abstract String getBillKind();

    abstract int getPersonId();

    abstract int getBillId();

    abstract int getCashSize();

    abstract void setPersonId(int id);

    abstract void setCashSize(int money_size);

    abstract void setBillId(int id);

    abstract void assignId();

    protected int bill_id_;
    protected int person_id_;
    protected int cash_size_;
}
