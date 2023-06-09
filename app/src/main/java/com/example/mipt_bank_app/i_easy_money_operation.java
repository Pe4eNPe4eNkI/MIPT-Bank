package com.example.mipt_bank_app;


public abstract class i_easy_money_operation {
    public abstract void executeOperation(String receiver_bill_id, String money_size, String type);

    public abstract void cancelOperation(String receiver_bill_id, String money_size, String type);

    protected bills_db trans_;
    protected person_db pdb_;
    protected operation_db odb_;
}
