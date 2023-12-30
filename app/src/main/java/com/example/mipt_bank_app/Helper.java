package com.example.mipt_bank_app;

import androidx.fragment.app.Fragment;

import com.example.mipt_bank_app.Person.Adult;
import com.example.mipt_bank_app.Person.PersonDB;
import com.example.mipt_bank_app.StringHash.StringHash;
import com.example.mipt_bank_app.bill.bills_db;
import com.example.mipt_bank_app.operations.operation_db;

import java.util.HashMap;
import java.util.Map;

public class Helper extends Fragment {
    public Helper() {
        personDbColumnNumber.put("login", 0);
        personDbColumnNumber.put("id", 1);
        personDbColumnNumber.put("password", 2);
        personDbColumnNumber.put("salt", 3);
        personDbColumnNumber.put("first_name", 4);
        personDbColumnNumber.put("second_name", 5);
        personDbColumnNumber.put("address", 6);
        personDbColumnNumber.put("passport_id", 7);
        personDbColumnNumber.put("money_limit", 8);
        personDbColumnNumber.put("is_doubtful", 9);
    }

    public static StringHash stringHash = new StringHash();

    public static HashMap<String, Integer> personDbColumnNumber = new HashMap<>();
    public static PersonDB personDB = null;

    public static bills_db billsDB = null;

    public static operation_db operationDB = null;

    public static PinCodeDB pinCodeDB = null;

    public final static String AdultBuilderType = "adult";

    public static Adult adult = null;

    public static int oper_counter = 1;
    public static int entered = 0;

    public static int have_debit = 0;

    public static int have_credit = 0;

    public static int have_deposit = 0;

    public static int selected_key = 0;

    public static String operation = "";

    public static final String BILL_KIND_CREDIT = "credit";

    public static final String BILL_KIND_DEBIT = "debit";

    public static final String BILL_KIND_DEPOSIT = "deposit";

    public static final String WITHDRAWAL = "WITHDRAWAL";
    public static final String TRANSFER = "TRANSFER";
    public static final String REFIL = "REFIL";

    public static int money_limit = 1000;

}
