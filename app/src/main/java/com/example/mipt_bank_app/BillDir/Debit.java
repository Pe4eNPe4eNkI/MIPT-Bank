package com.example.mipt_bank_app.BillDir;

import com.example.mipt_bank_app.Helper;

import kotlin.Triple;

public class Debit extends Bill {

    public Debit(){
        billKind = Helper.BILL_KIND_DEBIT;
    }

    @Override
    public String getBillKind() {
        return billKind;
    }

    @Override
    public String getCashSize() {
        return cashSize;
    }

    @Override
    public String getBillID() {
        return billID;
    }
    /**This property is spent money from debit card*/
    @Override
    public Triple<String,String,String> getUniqueProperty() {
        return new Triple<>(Helper.uniquePropertyDebitDBName, uniqueProperty, cashBack);
    }

    @Override
    public String getPersonID() {
        return personID;
    }

    @Override
    public void setCashSize(String cash_size) {
        cashSize = cash_size;
    }
    /**This property is spent money from debit card*/
    @Override
    public void setUniqueProperty(String spentMoney_) {
        uniqueProperty = spentMoney_;
    }

    @Override
    public void setBillID(String id) {
        billID = id;
    }
    @Override
    public void setPersonID(String person_id) {
        personID = person_id;
    }

    @Override
    public void update() {
        if (uniqueProperty != null) {
            cashBack = new Double(Double.parseDouble(uniqueProperty) / 100).toString();
        }
    }
    private String cashBack;
}
