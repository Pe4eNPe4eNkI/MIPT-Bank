package com.example.mipt_bank_app.Person;
/**Abstract class*/
 public abstract class Person {

      public abstract String getID();
      public abstract void setID(String id);

     public abstract String getName();

     public abstract void setName(String name);

     public abstract String getSurname();

     public abstract void setSurname(String surname);

     public abstract String getAddress();
     public abstract void setAddress(String address);

     public abstract String getLogin();

     public abstract void setLogin(String login);
     public abstract String getPassword();

     public abstract void setPassword(String password);


      protected String name;
      protected String surname;
      protected String address;
      protected String login;
      protected String password;
      protected String id;
}
