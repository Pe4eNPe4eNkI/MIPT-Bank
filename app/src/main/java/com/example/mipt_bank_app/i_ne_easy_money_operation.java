package com.example.mipt_bank_app;


abstract class i_ne_easy_money_operation {
    abstract void executeTransferOperation(String send_bill_id,
                                          String receiver_bill_id,
                                          String money_size,String type);

    abstract void cancelTransferOperation(String send_bill_id,
                                         String receiver_bill_id,
                                         String money_size,String type);
}
