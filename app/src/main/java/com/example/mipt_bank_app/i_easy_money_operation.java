package com.example.mipt_bank_app;


abstract class i_easy_money_operation {
    abstract void executeOperation(String receiver_bill_id, String money_size, String type);

    abstract void cancelOperation(String receiver_bill_id, String money_size);
}
