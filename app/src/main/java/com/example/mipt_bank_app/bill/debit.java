package com.example.mipt_bank_app.bill;

import com.example.mipt_bank_app.Constants;

public class debit extends i_bill {

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
    public String get_specific_bill_field() {
        Long temp = new Long(cashback_potential_size_);
        return temp.toString();
    }

    @Override
    public void set_cash_size(String cash_size) {
        cash_size_ = cash_size;
    }

    @Override
    public void set_bill_id(String id) {
        bill_id_ = id;
    }
    @Override
    public void set_person_id(String person_id) {
        person_id_ = person_id;
    }

    @Override
    public void set_specific_bill_field(String cashback_potential_size) {
        cashback_potential_size_ = Long.parseLong(cashback_potential_size);
    }

    public void update() {
        cashback_potential_size_ = Long.parseLong(cash_size_) / 100 * 5;
    }

    private String bill_kind_ = Constants.BILL_KIND_DEBIT;
    private long cashback_potential_size_;
}
