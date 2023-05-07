package com.example.mipt_bank_app;

public class PersonDirector {
    Person createPerson(PersonBuilder pb) {
        try {
            if (pb.checkArgs()) throw new ArithmeticException("Bad Input");
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
        pb.createPerson();
        pb.setPersonFirstName();
        pb.setPersonSecondName();
        pb.setPersonAddress();
        pb.setPersonPassportId();
        pb.getPerson().assignId();
        pb.getPerson().update();
        return pb.getPerson();
    }

}
