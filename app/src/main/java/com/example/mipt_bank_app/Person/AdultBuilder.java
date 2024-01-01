package com.example.mipt_bank_app.Person;

import com.example.mipt_bank_app.Helper;


public class AdultBuilder extends PersonBuilder {

    public AdultBuilder(AdultParams params) {
        type = Helper.adultBuilderType;
        name = params.name;
        surname = params.surname;
        address = params.address;
        passportId = params.passportId;
        login = params.login;
        password = params.password;

        adult = new Adult();
    }

    public AdultBuilder(String name, String surname, String address, String passportId,
                 String login, String password) {
        type = Helper.adultBuilderType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportId = passportId;
        this.login = login;
        this.password = password;

        adult = new Adult();
    }


    private void buildName(String name) {
        adult.setName(name);
    }

    private void buildSurname(String surname) {
        adult.setSurname(surname);
    }

    private void buildAddress(String address) {
        adult.setAddress(address);
    }

    private void buildPassportID(String passportId) {
        adult.setPassportId(passportId);
    }

    private void buildLogin(String login) {
        adult.setLogin(login);
    }

    private void buildPassword(String password) {
        adult.setPassword(password);
    }

    private void doUpdate() {
        adult.updateStatus();
    }

    @Override
    public Adult getPerson() {
        return adult;
    }

    @Override
    public void build() {
        this.buildName(name);
        this.buildSurname(surname);
        this.buildAddress(address);
        this.buildPassportID(passportId);
        this.buildLogin(login);
        this.buildPassword(password);
        this.doUpdate();
    }

    protected Adult adult;
    protected String passportId;

}
