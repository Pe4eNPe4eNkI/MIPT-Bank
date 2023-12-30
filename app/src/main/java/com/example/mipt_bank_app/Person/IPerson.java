package com.example.mipt_bank_app.Person;

 public abstract class IPerson {

      public abstract String getID();
      public abstract void setID(String id);

      protected String name;
      protected String surname;
      protected String address;
      protected String login;
      protected String password;
      protected String id;
}
