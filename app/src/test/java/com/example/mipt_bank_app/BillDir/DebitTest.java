package com.example.mipt_bank_app.BillDir;

import com.example.mipt_bank_app.Helper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class DebitTest {
    private Debit debit;

    @BeforeEach
    public void beforeTest() {
        debit = new Debit();
    }

    @Test
    public void getBillKindTest() {
        Assertions.assertEquals(Helper.BILL_KIND_DEBIT, debit.getBillKind());
    }

    @Test
    public void getAndSetCashTest() {
        String cash = "10.1";
        debit.setCashSize(cash);
        Assertions.assertEquals(cash, debit.getCashSize());
    }

    @Test
    public void getAndSetBillIdTest() {
        String id = "001";
        debit.setBillID(id);
        Assertions.assertEquals(id, debit.getBillID());
    }

    @Test
    public void getAndSetPersonIdTest() {
        String id = "101";
        debit.setPersonID(id);
        Assertions.assertEquals(id, debit.getPersonID());
    }

    @Test
    public void getAndSetSpentMoneyAndCashBackTest() {
        String spentMoney = "300";
        debit.setUniqueProperty(spentMoney);
        Assertions.assertEquals(spentMoney, debit.getUniqueProperty().getSecond());
    }

    @Test
    public void update_WithRequiresData_Test() {
        String cashback = "9.0";
        debit.setUniqueProperty("900.0");
        debit.update();
        Assertions.assertEquals(cashback, debit.getUniqueProperty().getThird());
    }

}
