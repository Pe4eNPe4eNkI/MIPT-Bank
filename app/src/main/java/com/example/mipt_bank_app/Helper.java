package com.example.mipt_bank_app;

import androidx.fragment.app.Fragment;

import com.example.mipt_bank_app.Person.Adult;
import com.example.mipt_bank_app.Person.PersonDB;
import com.example.mipt_bank_app.StringHash.StringHash;
import com.example.mipt_bank_app.bill.BillDB;
import com.example.mipt_bank_app.bill.CreditFactory;
import com.example.mipt_bank_app.bill.DebitFactory;
import com.example.mipt_bank_app.bill.DepositFactory;
import com.example.mipt_bank_app.operations.OperationDB;

import java.util.HashMap;

public class Helper extends Fragment {

    public static PersonDB personDB = null;

    public static BillDB billsDB = null;

    public static OperationDB operationDB = null;

    public static PinCodeDB pinCodeDB = null;

    public static HashMap<String, Integer> personDbColumnNumber = new HashMap<>();
    public static HashMap<String, Integer> operationDbColumnNumber = new HashMap<>();

    public static HashMap<String, Integer> billDbColumnNumber = new HashMap<>();

    public static final CreditFactory creditFactory = new CreditFactory();
    public static final DebitFactory debitFactory = new DebitFactory();
    public static final DepositFactory depositFactory = new DepositFactory();

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

        operationDbColumnNumber.put("card_id_sender", 0);
        operationDbColumnNumber.put("card_id_receiver", 1);
        operationDbColumnNumber.put("sum", 2);
        operationDbColumnNumber.put("operation_type", 3);
        operationDbColumnNumber.put("operation_id", 4);

        billDbColumnNumber.put("bill_kind", 0);
        billDbColumnNumber.put("bill_id", 1);
        billDbColumnNumber.put("person_id", 2);
        billDbColumnNumber.put("balance", 3);
        billDbColumnNumber.put("debt", 4);
        billDbColumnNumber.put("percent", 5);
        billDbColumnNumber.put("spent_money", 6);

    }

    public static StringHash stringHash = new StringHash();


    public final static String adultBuilderType = "adult";

    public static Adult adult = null;

    public static int operationsCounter = 1;
    public static int entered = 0;

    public static int haveDebit = 0;

    public static int haveCredit = 0;

    public static int haveDeposit = 0;

    public static int selected_key = 0;

    public static String selectedCardId = "";

    public static final String BILL_KIND_CREDIT = "credit";

    public static final String BILL_KIND_DEBIT = "debit";

    public static final String BILL_KIND_DEPOSIT = "deposit";

    public static final String WITHDRAWAL = "withdrawal";
    public static final String TRANSFER = "transfer";
    public static final String REFIL = "refil";
    public static final String CREDIT_MONEY_LIMIT = "10000.0";

    public static final String uniquePropertyDepositDBName = "percent";
    public static final String uniquePropertyDebitDBName = "spent_money";
    public static final String uniquePropertyCreditDBName = "debt";
    public static Double money_limit = 1000.0;

}
