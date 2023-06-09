package com.example.mipt_bank_app;


public class bill_factory {

    public deposit build_deposit(String bill_id, String person_id, String money) {
        deposit deposit_ = new deposit();
        deposit_.set_person_id(person_id);
        deposit_.set_bill_id(bill_id);
        deposit_.set_cash_size(money);
        deposit_.set_specific_bill_field(Long.toString(Long.parseLong(deposit_.get_cash_size()) / 100 * 10));
        return deposit_;
    }

    public deposit build_deposit(String person_id) {
        deposit deposit_ = new deposit();
        deposit_.set_person_id(person_id);
        deposit_.set_cash_size("0");
        deposit_.set_specific_bill_field(Long.toString(Long.parseLong(deposit_.get_cash_size()) / 100 * 10));
        return deposit_;
    }

    public credit build_credit(String bill_id, String person_id, String money, String field_for_bill) {
        credit credit_ = new credit();
        credit_.set_bill_id(bill_id);
        credit_.set_person_id(person_id);
        credit_.set_cash_size(money);
        credit_.set_specific_bill_field(field_for_bill);
        return credit_;
    }

    public credit build_credit(String person_id) {
        credit credit_ = new credit();
        credit_.set_person_id(person_id);
        credit_.set_cash_size("100000");
        credit_.set_specific_bill_field("0");
        return credit_;
    }

    public debit build_debit(String bill_id, String person_id, String money) {
        debit debit_ = new debit();
        debit_.set_person_id(person_id);
        debit_.set_bill_id(bill_id);
        debit_.set_cash_size(money);
        Long temp = (Long.parseLong(debit_.get_cash_size()) / 100 * 5);
        debit_.set_specific_bill_field(temp.toString());
        return debit_;
    }
    public debit build_debit(String person_id) {
        debit debit_ = new debit();
        debit_.set_person_id(person_id);
        debit_.set_cash_size("0");
        Long temp = (Long.parseLong(debit_.get_cash_size()) / 100 * 5);
        debit_.set_specific_bill_field(temp.toString());
        return debit_;
    }
}
