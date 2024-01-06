package com.example.mipt_bank_app.operations;


/**Abstract class for money operations such us refill and withdrawal*/
public abstract class EasyMoneyOperation {

    public abstract void executeOperation(String money_size) throws Exception;

}
