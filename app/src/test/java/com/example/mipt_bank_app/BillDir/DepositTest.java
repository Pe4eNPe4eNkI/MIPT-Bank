package com.example.mipt_bank_app.BillDir;

import com.example.mipt_bank_app.Helper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class DepositTest {
    private Deposit deposit;

    @BeforeEach
    public void beforeTest() {
        deposit = new Deposit();
    }

    @Test
    public void getBillKindTest() {
        Assertions.assertEquals(Helper.BILL_KIND_DEPOSIT, deposit.getBillKind());
    }

    @Test
    public void getAndSetCashTest() {
        String cash = "10.1";
        deposit.setCashSize(cash);
        Assertions.assertEquals(cash, deposit.getCashSize());
    }

    @Test
    public void getAndSetBillIdTest() {
        String id = "001";
        deposit.setBillID(id);
        Assertions.assertEquals(id, deposit.getBillID());
    }

    @Test
    public void getAndSetPersonIdTest() {
        String id = "101";
        deposit.setPersonID(id);
        Assertions.assertEquals(id, deposit.getPersonID());
    }

    @Test
    public void getAndSetPercentTest() {
        String percent = "10.0";
        deposit.setUniqueProperty(percent);
        Assertions.assertEquals(percent, deposit.getUniqueProperty().getSecond());
    }

    @Test
    public void update_WithRequiresData_Test() {
        String percent = "90.0";
        String cash = "900.0";
        deposit.setCashSize(cash);
        deposit.update();
        Assertions.assertEquals(percent, deposit.getUniqueProperty().getSecond());
    }

}
