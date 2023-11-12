package com.example.mipt_bank_app.operations;


import com.example.mipt_bank_app.bill.bills_db;
import com.example.mipt_bank_app.person.PersonDB;

public abstract class i_easy_money_operation {
    public abstract void executeOperation(String receiver_bill_id, String money_size, String type);

    public abstract void cancelOperation(String receiver_bill_id, String money_size, String type);

    protected bills_db trans_;
    protected PersonDB pdb_;
    protected operation_db odb_;
}
