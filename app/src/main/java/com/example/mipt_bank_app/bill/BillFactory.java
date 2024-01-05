package com.example.mipt_bank_app.bill;


import com.example.mipt_bank_app.Helper;
/**Abstract class Bill factory*/
abstract class BillFactory {

    public abstract Bill buildBill(String bill_id, String person_id, String money);

}
