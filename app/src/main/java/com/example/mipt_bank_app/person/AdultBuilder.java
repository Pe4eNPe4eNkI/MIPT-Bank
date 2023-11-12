package com.example.mipt_bank_app.person;

import com.example.mipt_bank_app.Constants;


public class AdultBuilder extends IPersonBuilder {

    public AdultBuilder(AdultParams params) {
        type = Constants.AdultBuilderType;
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
        type = Constants.AdultBuilderType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportId = passportId;
        this.login = login;
        this.password = password;

        adult = new Adult();
    }


    public void setAdultName(String name) {
        adult.setName(name);
    }

    public void setAdultSurName(String surname) {
        adult.setSurName(surname);
    }

    public void setAdultAddress(String address) {
        adult.setAddress(address);
    }

    public void setAdultPassportId(String passportId) {
        adult.setPassportId(passportId);
    }

    public void setAdultLogin(String login) {
        adult.setLogin(login);
    }

    public void setAdultPassword(String password) {
        adult.setPassword(password);
    }

    public void doUpdate() {
        adult.updateStatus();
    }

    @Override
    public Adult getPerson() {
        return adult;
    }

    @Override
    public void build() {
        this.setAdultName(name);
        this.setAdultSurName(surname);
        this.setAdultAddress(address);
        this.setAdultPassportId(passportId);
        this.setAdultLogin(login);
        this.setAdultPassword(password);
        this.doUpdate();
    }

    protected Adult adult;
    protected String passportId;

}
