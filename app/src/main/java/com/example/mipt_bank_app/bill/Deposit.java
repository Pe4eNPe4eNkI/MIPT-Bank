package com.example.mipt_bank_app.bill;


import android.util.Pair;

import com.example.mipt_bank_app.Helper;

public class Deposit extends Bill {

    public Deposit() {
        billKind = Helper.BILL_KIND_DEPOSIT;
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

    @Override
    public String getPersonID() {
        return personID;
    }

    /**
     * This unique property is percent of your deposit
     */
    @Override
    public Pair<String, String> getUniqueProperty() {
        return new Pair<>(Helper.uniquePropertyDepositDBName, percent);
    }

    @Override
    public void setCashSize(String cash_size) {
        cashSize = cash_size;
    }

    @Override
    public void setBillID(String id) {
        billID = id;
    }

    @Override
    public void setPersonID(String person_id) {
        personID = person_id;
    }

    /**
     * This unique property is percent of your deposit
     */
    @Override
    public void setUniqueProperty(String percent_) {
        percent = percent_;
    }

    void update() {
        percent = new Double(Double.parseDouble(cashSize) / 100 * 10).toString();
    }

    private String percent;

}
