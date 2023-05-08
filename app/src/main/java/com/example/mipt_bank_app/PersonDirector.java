package com.example.mipt_bank_app;

public class PersonDirector {
    Person createPerson(PersonBuilder pb) {
        try {
            if (pb.check_args()) throw new ArithmeticException("Bad Input");
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
        pb.create_person();
        pb.set_person_first_name();
        pb.set_person_second_name();
        pb.set_person_address();
        pb.set_person_passport_id();
        //pb.getPerson().assignId();
        pb.get_person().update();
        return pb.get_person();
    }

}
