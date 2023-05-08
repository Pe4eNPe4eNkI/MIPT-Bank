package com.example.mipt_bank_app;


abstract class IPerson {
    abstract String get_first_name();

    abstract String get_second_name();

    abstract String get_address();

    abstract String get_passport_id();

    abstract int get_money_limit();

    abstract String get_password();

    abstract String get_login();

    abstract boolean is_doubtful();


    abstract void set_first_name(String first_name);

    abstract void set_second_name(String second_name);

    abstract void set_address(String address);

    abstract void set_passport_id(String passport_id);

    abstract void set_password(String password);

    abstract void set_login(String login);


    abstract void update();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
    protected String password_;
    protected String login_;
    protected int money_limit_ = SupConstants.money_limit;
    protected boolean is_doubtful_ = true;
}
