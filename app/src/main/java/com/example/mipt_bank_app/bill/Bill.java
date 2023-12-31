package com.example.mipt_bank_app.bill;

import android.util.Pair;

/** Abstract class for bills */
public abstract class Bill {

    public abstract String getBillKind();

    public abstract String getPersonID();

    public abstract String getBillID();

    public abstract Pair<String,String> getUniqueProperty();

    public abstract String getCashSize();


    public abstract void setPersonID(String id);

    public abstract void setBillID(String id);

    public abstract void setCashSize(String moneySize);

    public abstract void setUniqueProperty(String spentMoney);



    protected String billID;
    protected String personID;
    protected String cashSize;
    protected String billKind;
    protected String uniqueProperty;

}
