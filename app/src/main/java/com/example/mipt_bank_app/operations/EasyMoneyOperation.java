package com.example.mipt_bank_app.operations;


import com.example.mipt_bank_app.bill.CreditFactory;
import com.example.mipt_bank_app.bill.DebitFactory;
import com.example.mipt_bank_app.bill.DepositFactory;

/**Abstract class for money operations such us refill and withdrawal*/
public abstract class EasyMoneyOperation {

    public abstract void executeOperation(String money_size) throws Exception;

}
