package com.example.mipt_bank_app.Person;
/**Abstract Class*/
public abstract class PersonBuilder {
    public abstract Person getPerson();

    public abstract void build();

    protected String type;

    protected String name;
    protected String surname;
    protected String address;
    protected String login;
    protected String password;
}
