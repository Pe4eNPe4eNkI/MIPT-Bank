package com.example.mipt_bank_app.Person;

public class Adult extends Person {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
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

    @Override
    public void setName(String firstName) {
        this.name = firstName;
    }

    @Override
    public void setSurname(String secondName) {
        this.surname = secondName;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
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
