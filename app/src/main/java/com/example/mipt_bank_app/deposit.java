package com.example.mipt_bank_app;


public class deposit extends i_bill {
    @Override
    public String get_bill_kind() {
        return bill_kind_;
    }

    @Override
    public String get_cash_size() {
        return cash_size_;
    }

    public String get_income_potential_size() {
        return income_potential_size_;
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
    public void set_cash_size(String cash_size) {
        cash_size_ = cash_size;
    }

    @Override
    public void set_bill_id(String id) {
        bill_id_ = id;
    }

    @Override
    String get_specific_bill_field() {
        return income_potential_size_;
    }

    @Override
    void set_specific_bill_field(String income_potential_size) {
        income_potential_size_ = income_potential_size;
    }

    @Override
    public void set_person_id(String person_id) {
        person_id_ = person_id;
    }

    void update() {
        Long temp = Long.parseLong(cash_size_) / 100 * 10;
        income_potential_size_ = Long.toString(temp);
    }

    private String bill_kind_ = constants.BILL_KIND_DEPOSIT;
    private String income_potential_size_;

}
