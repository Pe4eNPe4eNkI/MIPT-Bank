package com.example.mipt_bank_app;


public class Credit extends IBill {

    @Override
    public String getBillKind() {
        return bill_kind_;
    }

    @Override
    public int getCashSize() {
        return cash_size_;
    }

    @Override
    public int getBillId() {
        return bill_id_;
    }

    @Override
    public int getPersonId() {
        return person_id_;
    }

    public int getAccessibleCashSize() {
        return accessible_cash_size_;
    }

    public int getDebtSize() {
        return debt_size_;

    }

    public int getPaymentOnThisMonth() {
        return payment_on_this_month_;
    }

    @Override
    public void setCashSize(int cash_size) {
        cash_size_ = cash_size;
    }

    @Override
    public void setBillId(int id) {
        bill_id_ = id;
    }

    public void setAccessibleCashSize(int accessible_cash_size) {
        accessible_cash_size_ = accessible_cash_size;
    }

    public void setDebtSize(int debt_size) {
        debt_size_ = debt_size;
    }

    public void setPaymentOnThisMonth(int payment_on_this_month) {
        payment_on_this_month_ = payment_on_this_month;
    }

    @Override
    public void setPersonId(int person_id) {
        person_id_ = person_id;

    }

    public void update() {
        debt_size_ = cash_size_ - accessible_cash_size_;
        payment_on_this_month_ = debt_size_ / 10;
    }

    @Override
    public void assignId() {

    }

    private String bill_kind_ = SupConstants.BILL_KIND_CREDIT;
    private int accessible_cash_size_;
    private int payment_on_this_month_;
    private int debt_size_;
}
