package com.example.mipt_bank_app.Person;

public class Adult extends IPerson {

    public String getName() {
        return name;
    }


    public String getSurName() {
        return surname;
    }


    public String getAddress() {
        return address;
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    public String getPassportId() {
        return passportId;
    }

    public Boolean getIsDoubtful() {
        return isDoubtful;
    }

    public Double getMoneyLimit() {
        return moneyLimit;
    }

    @Override
    public String getID() {
        return id;
    }


    public void setName(String firstName) {
        this.name = firstName;
    }


    public void setSurName(String secondName) {
        this.surname = secondName;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    public void updateStatus() {
        if (!address.isEmpty() || !passportId.isEmpty()) {
            isDoubtful = false;
            moneyLimit = 0.0;
        }
    }

    private String passportId;

    private Boolean isDoubtful = true;

    private Double moneyLimit = 10000.0;
}
