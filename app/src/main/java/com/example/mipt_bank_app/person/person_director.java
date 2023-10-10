package com.example.mipt_bank_app.person;

import com.example.mipt_bank_app.person.person;
import com.example.mipt_bank_app.person.person_builder;

public class person_director {
    public person createPerson(person_builder pb) {
        pb.create_person();
        pb.get_person().set_first_name(pb.first_name_);
        pb.get_person().set_second_name(pb.second_name_);
        pb.get_person().set_address(pb.address_);
        pb.get_person().set_passport_id(pb.passport_id_);
        pb.get_person().set_login(pb.login_);
        pb.get_person().set_password(pb.password_);
        pb.get_person().update();
        return pb.get_person();
    }

}
