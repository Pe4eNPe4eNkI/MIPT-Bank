package com.example.mipt_bank_app.operations;


/**Abstract class for money operations such us transfer*/
public abstract class NotEasyMoneyOperation {
    public abstract void executeTransferOperation(String send_bill_id,
                                          String receiver_bill_id,
                                          String money_size,String type);

    public abstract void cancelTransferOperation(String send_bill_id,
                                         String receiver_bill_id,
                                         String money_size,String type);

}
