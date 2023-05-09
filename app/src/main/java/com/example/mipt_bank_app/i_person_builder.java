package com.example.mipt_bank_app;

abstract class i_person_builder {
    abstract i_person_builder set_first_name(String first_name);
    
    abstract i_person_builder set_second_name(String second_name);
    
    abstract i_person_builder set_address(String address);
    
    abstract i_person_builder set_passport_id(String passport_id);

    abstract i_person_builder set_password(String password);

    abstract i_person_builder set_id(String id);

    abstract i_person_builder set_login(String login);
    
   /* abstract void set_person_first_name();
    
    abstract void set_person_second_name();
    
    abstract void set_person_passport_id();
    
    abstract void set_person_address();

    abstract void set_person_password();

    abstract void set_person_login();*/
    
    abstract boolean check_args();
    
    abstract i_person get_person();
    
    abstract void create_person();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
    protected String password_;
    protected String login_;

    protected String id_;

}
