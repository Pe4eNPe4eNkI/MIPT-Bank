package com.example.mipt_bank_app.Person;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class AdultTest {
    private Adult adult;

    @BeforeEach
    public void beforeTest(){
        adult = new Adult();
    }
    @Test
    public void getAndSetNameTest(){
        String name = "Dimitry";
        adult.setName(name);
        Assertions.assertEquals(name, adult.getName());
    }

    @Test
    public void getAndSetSurnameTest(){
        String surname = "Cumenshik";
        adult.setSurname(surname);
        Assertions.assertEquals(surname, adult.getSurname());
    }

    @Test
    public void getAndSetAddressTest(){
        String address = "Solicumsk";
        adult.setAddress(address);
        Assertions.assertEquals(address, adult.getAddress());
    }

    @Test
    public void getAndSetLoginTest(){
        String login = "Polymetal";
        adult.setLogin(login);
        Assertions.assertEquals(login, adult.getLogin());
    }

    @Test
    public void getAndSetPasswordTest(){
        String password = "VeryStrongPassword1";
        adult.setPassword(password);
        Assertions.assertEquals(password, adult.getPassword());
    }

    @Test
    public void getAndSetPassportIdTest(){
        String passportId = "875 794 232 098";
        adult.setPassportId(passportId);
        Assertions.assertEquals(passportId, adult.getPassportId());
    }

    @Test
    public void getAndSetPersonIdTest(){
        String personId = "001";
        adult.setID(personId);
        Assertions.assertEquals(personId, adult.getID());
    }

    @Test
    public void getIsDoubtful_defaultValueTest(){
        Assertions.assertTrue(adult.getIsDoubtful());
    }

    @Test
    public void getMoneyLimit_defaultValueTest(){
        double result = 10000.0;
        Assertions.assertEquals(result, adult.getMoneyLimit());
    }

    @Test
    public void FunctionUpdate_WithPassportData_ReturnsInfMoneyLimitAndNotDoubtful(){
        double result = 0.0;
        String passportId = "875 794 232 098";
        adult.setPassportId(passportId);
        adult.updateStatus();
        Assertions.assertEquals(result, adult.getMoneyLimit());
        Assertions.assertFalse(adult.getIsDoubtful());
    }

    @Test
    public void FunctionUpdate_WithAddressData_ReturnsInfMoneyLimitAndNotDoubtful(){
        double result = 0.0;
        String address = "Solicumsk";
        adult.setAddress(address);
        adult.updateStatus();
        Assertions.assertEquals(result, adult.getMoneyLimit());
        Assertions.assertFalse(adult.getIsDoubtful());
    }

    @Test
    public void FunctionUpdate_WithAddressDataAndPassportData_ReturnsInfMoneyLimitAndNotDoubtful(){
        double result = 0.0;
        String passportId = "875 794 232 098";
        String address = "Solicumsk";
        adult.setPassportId(passportId);
        adult.setAddress(address);
        adult.updateStatus();
        Assertions.assertEquals(result, adult.getMoneyLimit());
        Assertions.assertFalse(adult.getIsDoubtful());
    }

    @Test
    public void FunctionUpdate_WithoutRequiresData_ReturnsMoneyLimitAndIsDoubtful(){
        double result = 10000.0;
        adult.updateStatus();
        Assertions.assertEquals(result, adult.getMoneyLimit());
        Assertions.assertTrue(adult.getIsDoubtful());
    }

}
