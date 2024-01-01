package com.example.mipt_bank_app.operations;


import android.database.Cursor;

import com.example.mipt_bank_app.bill.Bill;
import com.example.mipt_bank_app.Helper;

public class RefillOperation extends EasyMoneyOperation {

    private void doRefil(Double userBalanceDouble, Double refillSumDouble, String billIdFromDB, String personId, String billKind, String debt) {
        userBalanceDouble += refillSumDouble;

        Bill bill = null;

        switch (billKind) {
            case Helper.BILL_KIND_CREDIT:
                bill = Helper.creditFactory.buildBill(billIdFromDB, personId, Double.toString(userBalanceDouble));
                double newDebt = Double.parseDouble(debt) - refillSumDouble;
                if (newDebt > 0){
                    bill.setUniqueProperty(Double.toString(newDebt));
                } else {
                    bill.setUniqueProperty("0");
                }
                bill.update();
                break;
            case Helper.BILL_KIND_DEPOSIT:
                bill = Helper.depositFactory.buildBill(billIdFromDB, personId, Double.toString(userBalanceDouble));
                break;
            case Helper.BILL_KIND_DEBIT:
                bill = Helper.debitFactory.buildBill(billIdFromDB, personId, Double.toString(userBalanceDouble));
                break;
        }

        Helper.billsDB.updateUserData(bill);
        Helper.operationDB.insertUserData(bill, personId, Double.toString(refillSumDouble), Helper.REFIL);
    }

    @Override
    public void executeOperation(String refillSum) throws Exception {
        Cursor cursorBill = Helper.billsDB.getBill(Helper.selectedCardId);
        cursorBill.moveToFirst();

        String billIdFromDB = cursorBill.getString(Helper.billDbColumnNumber.get("bill_id"));
        String billKind = cursorBill.getString(Helper.billDbColumnNumber.get("bill_kind"));
        String balanceFromDB = cursorBill.getString(Helper.billDbColumnNumber.get("balance"));
        String personId = cursorBill.getString(Helper.billDbColumnNumber.get("person_id"));
        String debt = "";
        if (billKind.equals(Helper.BILL_KIND_CREDIT)){
            debt = cursorBill.getString(Helper.billDbColumnNumber.get("debt"));
        }

        Cursor cursorPerson = Helper.personDB.getPersonById(personId);
        cursorPerson.moveToFirst();
        String isDoubtful = cursorPerson.getString(Helper.personDbColumnNumber.get("is_doubtful"));
        boolean flag = (isDoubtful.equals("1") ? true : false);

        double refillSumDouble = Double.parseDouble(refillSum);
        double userBalanceDouble = Double.parseDouble(balanceFromDB);
        if (refillSumDouble > 0) {
            if (flag) {
                if (refillSumDouble < Helper.money_limit) {
                    doRefil(userBalanceDouble, refillSumDouble, billIdFromDB, personId, billKind, debt);
                } else {
                    throw new Exception("Too big sum");
                }
            } else {
                doRefil(userBalanceDouble, refillSumDouble, billIdFromDB, personId, billKind, debt);
            }
        }
    }
}
