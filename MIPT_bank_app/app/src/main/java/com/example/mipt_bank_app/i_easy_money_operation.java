package com.example.mipt_bank_app;


abstract class i_easy_money_operation {
    abstract void execute_operation(String receiver_bill_id, String money_size, String type);

    abstract void cancel_operation(String receiver_bill_id, String money_size);
}
