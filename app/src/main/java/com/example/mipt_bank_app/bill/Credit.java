package com.example.mipt_bank_app.bill;


import android.util.Pair;
import com.example.mipt_bank_app.Helper;

import kotlin.Triple;

public class Credit extends Bill {

    public Credit(String maxBalance_) {
        billKind = Helper.BILL_KIND_CREDIT;
        maxBalance = maxBalance_;
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
    /**This property is debt
     * @return Pair 1st is db field, 2nd is value*/
    @Override
    public Triple<String,String,String> getUniqueProperty() {
        return new Triple<>(Helper.uniquePropertyCreditDBName, debt, "");
    }

    @Override
    public String getPersonID() {
        return personID;
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
    public void setCashSize(String cashSize_) {
        cashSize = cashSize_;
    }
    /**This property is debt*/
    @Override
    public void setUniqueProperty(String debt_) {
        debt = debt_;
    }

    @Override
    public void update() {
        if (uniqueProperty != null) {
            debt = new Double(Double.parseDouble(maxBalance) - Double.parseDouble(cashSize)).toString();
        }
    }
    private String debt;
    private final String maxBalance;
}
