package com.example.mipt_bank_app.BillDir;

import com.example.mipt_bank_app.Helper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class CreditTest {
    private Credit credit;

    @BeforeEach
    public void beforeTest() {
        credit = new Credit(Helper.CREDIT_MONEY_LIMIT);
    }

    @Test
    public void getBillKindTest() {
        Assertions.assertEquals(Helper.BILL_KIND_CREDIT, credit.getBillKind());
    }

    @Test
    public void getAndSetCashTest() {
        String cash = "10.1";
        credit.setCashSize(cash);
        Assertions.assertEquals(cash, credit.getCashSize());
    }

    @Test
    public void getAndSetBillIdTest() {
        String id = "001";
        credit.setBillID(id);
        Assertions.assertEquals(id, credit.getBillID());
    }

    @Test
    public void getAndSetPersonIdTest() {
        String id = "101";
        credit.setPersonID(id);
        Assertions.assertEquals(id, credit.getPersonID());
    }

    @Test
    public void getAndSetDebtTest() {
        String debt = "300";
        credit.setUniqueProperty(debt);
        Assertions.assertEquals(debt, credit.getUniqueProperty().getSecond());
    }

    @Test
    public void update_WithRequiresData_Test() {
        String result = "9000.0";
        credit.setCashSize("1000.0");
        credit.update();
        Assertions.assertEquals(result, credit.getUniqueProperty().getSecond());
    }

}
