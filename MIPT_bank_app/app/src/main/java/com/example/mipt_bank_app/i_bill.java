package com.example.mipt_bank_app;

abstract class i_bill {

    public static int bill_id;

    abstract String get_bill_kind();

    abstract String get_person_id();

    abstract String get_bill_id();

    abstract String get_cash_size();

    abstract void set_person_id(String id);

    abstract void set_cash_size(String money_size);

    abstract void set_bill_id(String id);

    abstract String get_specific_bill_field();

    abstract void set_specific_bill_field(String temp);


    protected String bill_id_;
    protected String person_id_;
    protected String cash_size_;
}
