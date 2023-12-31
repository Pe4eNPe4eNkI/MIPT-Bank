package com.example.mipt_bank_app.operations;


import com.example.mipt_bank_app.bill.BillDB;
import com.example.mipt_bank_app.Person.PersonDB;

public abstract class i_ne_easy_money_operation {
    public abstract void executeTransferOperation(String send_bill_id,
                                          String receiver_bill_id,
                                          String money_size,String type);

    public abstract void cancelTransferOperation(String send_bill_id,
                                         String receiver_bill_id,
                                         String money_size,String type);

    protected BillDB trans_;
    protected PersonDB pdb_;
    protected operation_db odb_;
}
