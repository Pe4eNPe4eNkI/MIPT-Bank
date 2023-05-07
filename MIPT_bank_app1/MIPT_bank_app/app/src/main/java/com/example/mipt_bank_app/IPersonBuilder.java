package com.example.mipt_bank_app;

abstract class IPersonBuilder {
    abstract IPersonBuilder setFirstName(String first_name);
    
    abstract IPersonBuilder setSecondName(String second_name);
    
    abstract IPersonBuilder setAddress(String address);
    
    abstract IPersonBuilder setPassportId(String passport_id);
    
    abstract void setPersonFirstName();
    
    abstract void setPersonSecondName();
    
    abstract void setPersonPassportId();
    
    abstract void setPersonAddress();
    
    abstract boolean checkArgs();
    
    abstract IPerson getPerson();
    
    abstract void createPerson();

    protected String first_name_;
    protected String second_name_;
    protected String address_;
    protected String passport_id_;
}
