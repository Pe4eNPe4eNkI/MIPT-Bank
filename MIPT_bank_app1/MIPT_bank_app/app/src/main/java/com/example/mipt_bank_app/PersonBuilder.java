package com.example.mipt_bank_app;

public class PersonBuilder extends IPersonBuilder{

    @Override
    public PersonBuilder setFirstName(String first_name) {
        first_name_ = first_name;
        return this;
    }

    @Override
    public PersonBuilder setSecondName(String second_name) {
        second_name_ = second_name;
        return this;
    }

    @Override
    public PersonBuilder setAddress(String address) {
        address_ = address;
        return this;
    }

    @Override
    public PersonBuilder setPassportId(String passport_id) {
        passport_id_ = passport_id;
        return this;
    }
    @Override
    public boolean checkArgs() {
        return first_name_.isEmpty() && second_name_.isEmpty();

    }

    @Override
    public void setPersonFirstName() {
        pers.setFirstName(first_name_);
    }

    @Override
    public void setPersonSecondName() {
        pers.setSecondName(second_name_);
    }

    @Override
    public void setPersonPassportId() {
        pers.setPassportId(passport_id_);
    }

    @Override
    public void setPersonAddress() {
        pers.setAddress(address_);
    }
    @Override
    public Person getPerson()   {
        return pers;
    }

    @Override
    public void createPerson() {
        pers = new Person();
    }

    private Person pers;
}
