package com.example.mipt_bank_app;


abstract class i_ne_easy_money_operation {
    abstract void execute_transfer_operation(String send_bill_id,
                                          String receiver_bill_id,
                                          String money_size);

    abstract void cancel_transfer_operation(String send_bill_id,
                                         String receiver_bill_id,
                                         String money_size);
}
