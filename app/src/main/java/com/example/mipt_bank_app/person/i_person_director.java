package com.example.mipt_bank_app.person;

import com.example.mipt_bank_app.person.i_person;
import com.example.mipt_bank_app.person.i_person_builder;

abstract class i_person_director {
    abstract i_person createPerson(i_person_builder pb);

}
