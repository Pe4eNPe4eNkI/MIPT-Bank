package com.example.mipt_bank_app.bill;

public abstract class i_bill {

    public static int bill_id;

    public abstract String get_bill_kind();

    public abstract String get_person_id();

    public abstract String get_bill_id();

    public abstract String get_cash_size();

    public abstract void set_person_id(String id);

    public abstract void set_cash_size(String money_size);

    public abstract void set_bill_id(String id);

    public abstract String get_specific_bill_field();

    public abstract void set_specific_bill_field(String temp);


    protected String bill_id_;
    protected String person_id_;
    protected String cash_size_;

}
