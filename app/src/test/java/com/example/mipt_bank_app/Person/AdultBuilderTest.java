package com.example.mipt_bank_app.Person;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class AdultBuilderTest {

    private AdultBuilder adultBuilder;
    private AdultParams adultParams;

    @BeforeEach
    public void beforeTest() {
        adultBuilder = null;
        adultParams = new AdultParams();
    }

    @Test
    public void getPersonTest() {
        adultBuilder = new AdultBuilder(adultParams);
        Assertions.assertTrue(adultBuilder.getPerson() instanceof Person);
        Assertions.assertTrue(adultBuilder.getPerson() instanceof Adult);
    }

    @Test
    public void buildNameTest() {
        String name = "Olesha";
        adultParams.name = name;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(name, adultBuilder.getPerson().getName());
    }

    @Test
    public void buildSurnameTest() {
        String surname = "Joe Perge";
        adultParams.surname = surname;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(surname, adultBuilder.getPerson().getSurname());
    }

    @Test
    public void buildAddressTest() {
        String address = "Belarus";
        adultParams.address = address;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(address, adultBuilder.getPerson().getAddress());
    }

    @Test
    public void buildPassportIdTest() {
        String passportId = "101";
        adultParams.passportId = passportId;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(passportId, adultBuilder.getPerson().getPassportId());
    }

    @Test
    public void buildLoginTest() {
        String login = "buuuu!";
        adultParams.login = login;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(login, adultBuilder.getPerson().getLogin());
    }
    @Test
    public void buildPasswordTest() {
        String password = "MegaSuperPuper";
        adultParams.password = password;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(password, adultBuilder.getPerson().getPassword());
    }

    @Test
    public void doUpdateTest_FalseIsDoubtful() {
        String address = "Belarus";
        adultParams.address = address;
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertFalse(adultBuilder.getPerson().getIsDoubtful());
    }

    @Test
    public void doUpdateTest_TrueIsDoubtful() {
        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertTrue(adultBuilder.getPerson().getIsDoubtful());
    }

    @Test
    public void buildTest_WithAllField() {
        String name = "Olesha";
        String surname = "Joe Perge";
        String address = "Belarus";
        String passportId = "101";
        String login = "buuuu!";
        String password = "MegaSuperPuper";
        adultParams.name = name;
        adultParams.surname = surname;
        adultParams.address = address;
        adultParams.passportId = passportId;
        adultParams.login = login;
        adultParams.password = password;

        adultBuilder = new AdultBuilder(adultParams);
        adultBuilder.build();
        Assertions.assertEquals(name, adultBuilder.getPerson().getName());
        Assertions.assertEquals(surname, adultBuilder.getPerson().getSurname());
        Assertions.assertEquals(address, adultBuilder.getPerson().getAddress());
        Assertions.assertEquals(passportId, adultBuilder.getPerson().getPassportId());
        Assertions.assertEquals(login, adultBuilder.getPerson().getLogin());
        Assertions.assertEquals(password, adultBuilder.getPerson().getPassword());
        Assertions.assertFalse(adultBuilder.getPerson().getIsDoubtful());
    }

}
