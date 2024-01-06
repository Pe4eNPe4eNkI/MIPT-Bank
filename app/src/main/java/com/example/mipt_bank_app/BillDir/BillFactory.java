package com.example.mipt_bank_app.BillDir;


/**Abstract class Bill factory*/
abstract class BillFactory {

    public abstract Bill buildBill(String bill_id, String person_id, String money);

}
