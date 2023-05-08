package com.example.mipt_bank_app;

abstract class IPersonBuilder {
    abstract IPersonBuilder set_first_name(String first_name);
    
    abstract IPersonBuilder set_second_name(String second_name);
    
    abstract IPersonBuilder set_address(String address);
    
    abstract IPersonBuilder set_passport_id(String passport_id);

    abstract IPersonBuilder set_password(String password);

    abstract IPersonBuilder set_login(String login);
    
    abstract void set_person_first_name();
    
    abstract void set_person_second_name();
    
    abstract void set_person_passport_id();
    
    abstract void set_person_address();

    abstract void set_person_password();

    abstract void set_person_login();
    
    abstract boolean check_args();
    
    abstract IPerson get_person();
    
    abstract void create_person();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
    protected String password_;
    protected String login_;

}
