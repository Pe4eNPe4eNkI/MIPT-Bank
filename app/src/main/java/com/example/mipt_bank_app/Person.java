package com.example.mipt_bank_app;


public class Person extends IPerson {

    @Override
    public String get_first_name() {
        return first_name_;
    }

    @Override
    public String get_second_name() {
        return second_name_;
    }

    @Override
    public String get_address() {
        return address_;
    }

    @Override
    public String get_passport_id() {
        return passport_id_;
    }

    @Override
    public int get_money_limit() {
        return money_limit_;
    }

    @Override
    public boolean is_doubtful() {
        return is_doubtful_;
    }

    @Override
    public String get_password() {
        return password_;
    }

    @Override
    public String get_login() {
        return login_;
    }


    @Override
    public void set_first_name(String first_name) {
        first_name_ = first_name;
    }

    @Override
    public void set_second_name(String second_name) {
        second_name_ = second_name;
    }

    @Override
    public void set_address(String address) {
        address_ = address;
    }

    @Override
    public void set_passport_id(String passport_id) {
        passport_id_ = passport_id;
    }

    @Override
    public void set_password(String password) {
        password_ = password;
    }

    @Override
    public void set_login(String login) {
        login_ = login;
    }

    public void update() {
        is_doubtful_ = address_.isEmpty() || passport_id_.isEmpty();
        money_limit_ = (is_doubtful_ ? SupConstants.money_limit : 0);
    }
}
