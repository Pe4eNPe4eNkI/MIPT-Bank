package com.example.mipt_bank_app.bill;


import com.example.mipt_bank_app.Helper;

public class credit extends i_bill {

    @Override
    public String get_bill_kind() {
        return bill_kind_;
    }
    @Override
    public String get_cash_size() {
        return cash_size_;
    }

    @Override
    public String get_bill_id() {
        return bill_id_;
    }
    @Override
    public String get_person_id() {
        return person_id_;
    }


    @Override
    public String get_specific_bill_field() {  //debt
        return debt_size_;
    }

    @Override
    public void set_specific_bill_field(String temp) {
        debt_size_ = temp;
    }

    public Long get_payment_on_this_month() {
        return payment_on_this_month_;
    }


    @Override
    public void set_bill_id(String id) {
        bill_id_ = id;
    }

    @Override
    public void set_person_id(String person_id) {
        person_id_ = person_id;

    }

    public void set_cash_size(String cash_size) {
        cash_size_ = cash_size;
    }


    public void set_payment_on_this_month(Long payment_on_this_month) {
        payment_on_this_month_ = payment_on_this_month;
    }

   /* public void update() {
        Long.parseLong(debt_size_) = max_balance - Long.parseLong(cash_size_);
        payment_on_this_month_ = Long.parseLong(debt_size_) / 10;
    }*/

    private String bill_kind_ = Helper.BILL_KIND_CREDIT;

    private Long max_balance;
    private Long payment_on_this_month_;
    private String debt_size_;
}
