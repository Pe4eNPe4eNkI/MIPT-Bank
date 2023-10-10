package com.example.mipt_bank_app.person;

import static com.example.mipt_bank_app.constants.money_limit;

public abstract class i_person {
    public abstract String get_first_name();

    public abstract String get_second_name();

    public abstract String get_address();

    public abstract String get_passport_id();

    public abstract int get_money_limit();

    public abstract String get_password();

    public abstract String get_login();
    public abstract String get_id();

    public abstract boolean is_doubtful();


    public abstract void set_first_name(String first_name);

    public abstract void set_second_name(String second_name);

    public abstract void set_address(String address);

    public abstract void set_passport_id(String passport_id);

    public abstract void set_password(String password);

    public abstract void set_login(String login);

    public abstract void set_id(String id);

    public abstract void update();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
    protected String password_;
    protected String login_;

    protected String id_;
    protected int money_limit_ = money_limit;
    protected boolean is_doubtful_ = true;

}
