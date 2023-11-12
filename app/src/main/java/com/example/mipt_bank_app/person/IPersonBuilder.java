package com.example.mipt_bank_app.person;

public abstract class IPersonBuilder {
    public abstract IPerson getPerson();

    public abstract void build();

    protected String type;

    protected String name;
    protected String surname;
    protected String address;
    protected String login;
    protected String password;
}
