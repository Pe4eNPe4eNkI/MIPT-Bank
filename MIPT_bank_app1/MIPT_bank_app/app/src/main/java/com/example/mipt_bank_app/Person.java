package com.example.mipt_bank_app;


public class Person extends IPerson{
    public static int id;

    /*public Person() {
        if (id == 0) {
            std::ifstream file("max_person_id.txt");
            String s;
            file >> s;
            id = int(s);
            if (id != 0) ++id;
            file.close();
        }
    }*/

    @Override
    public String getFirstName() {
        return first_name_;
    }

    @Override
    public String getSecondName() {
        return second_name_;
    }

    @Override
    public String getAddress() {
        return address_;
    }

    @Override
    public String getPassportId() {
        return passport_id_;
    }

    @Override
    public int getMoneyLimit () {
        return money_limit_;
    }

    @Override
    public boolean isDoubtful () {
        return is_doubtful_;
    }

    @Override
    public int getId() {
        return id_;
    }


    public void setFirstName(String first_name) {
        first_name_ = first_name;
    }

    public void setSecondName(String second_name) {
        second_name_ = second_name;
    }

    public void setAddress(String address) {
        address_ = address;
    }

    public void setPassportId(String passport_id) {
        passport_id_ = passport_id;
    }

    public void assignId() {
        id = id + 1;
        id_ = id;
    }

    public void update() {
        is_doubtful_ = address_.isEmpty() || passport_id_.isEmpty();
        money_limit_ = (is_doubtful_ ? SupConstants.money_limit : 0);
    }
}
