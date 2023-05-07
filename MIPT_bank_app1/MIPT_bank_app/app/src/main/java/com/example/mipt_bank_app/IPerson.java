package com.example.mipt_bank_app;


abstract class IPerson {
    abstract String getFirstName();

    abstract String getSecondName();

    abstract String getAddress();

    abstract String getPassportId();

    abstract int getMoneyLimit();

    abstract boolean isDoubtful();

    abstract int getId();

    abstract void setFirstName(String first_name);

    abstract void setSecondName(String second_name);

    abstract void setAddress(String address);

    abstract void setPassportId(String passport_id);

    abstract void assignId();

    abstract void update();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
    protected int money_limit_ = SupConstants.money_limit;
    protected boolean is_doubtful_ = true;
    protected int id_;
}
