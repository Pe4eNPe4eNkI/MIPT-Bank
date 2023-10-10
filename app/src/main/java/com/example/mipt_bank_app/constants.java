package com.example.mipt_bank_app;

import com.example.mipt_bank_app.person.person;

public class constants {

    public static int oper_counter = 1;
    public static int entered = 0;

    public static int have_debit = 0;

    public static int have_credit = 0;

    public static int have_deposit = 0;

    public static int selected_key = 0;

    public static person person = null;

    public static String operation = "";

    public static final String BILL_KIND_CREDIT = "credit";

    public static final String BILL_KIND_DEBIT = "debit";

    public static final String BILL_KIND_DEPOSIT = "deposit";

    public static final String WITHDRAWAL = "WITHDRAWAL";
    public static final String TRANSFER = "TRANSFER";
    public static final String REFIL = "REFIL";

    public static int money_limit = 1000;

}
