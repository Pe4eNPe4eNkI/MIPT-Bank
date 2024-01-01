package com.example.mipt_bank_app.operations;


import android.database.Cursor;

import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.bill.Bill;

public class WithdrawalOperation extends EasyMoneyOperation {

    private void doWithdrawal(Double userBalanceDouble, Double withdrawalSumDouble, String billIdFromDB, String personId, String billKind, String debt) {
        userBalanceDouble -= withdrawalSumDouble;

        Bill bill = null;

        switch (billKind) {
            case Helper.BILL_KIND_CREDIT:
                bill = Helper.creditFactory.buildBill(billIdFromDB, personId, Double.toString(userBalanceDouble));
                double newDebt = Double.parseDouble(debt) + withdrawalSumDouble;
                bill.setUniqueProperty(Double.toString(newDebt));
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
        Helper.operationDB.insertUserData(bill, personId, Double.toString(withdrawalSumDouble), Helper.REFIL);
    }

    @Override
    public void executeOperation(String widthSum) throws Exception {
        Cursor cursorBill = Helper.billsDB.getBill(Helper.selectedCardId);
        cursorBill.moveToFirst();

        String billKind = cursorBill.getString(Helper.billDbColumnNumber.get("bill_kind"));
        String billIdFromDB = cursorBill.getString(Helper.billDbColumnNumber.get("bill_id"));
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

        double withdrawalSumDouble = Double.parseDouble(widthSum);
        double userBalanceDouble = Double.parseDouble(balanceFromDB);
        if (withdrawalSumDouble > 0 && userBalanceDouble - withdrawalSumDouble > 0) {
            if (flag) {
                if (withdrawalSumDouble < Helper.money_limit) {
                    doWithdrawal(userBalanceDouble, withdrawalSumDouble, billIdFromDB, personId, billKind, debt);
                } else {
                    throw new Exception("Too big sum");
                }
            } else {
                doWithdrawal(userBalanceDouble, withdrawalSumDouble, billIdFromDB, personId, billKind, debt);
            }
        }
    }
}
