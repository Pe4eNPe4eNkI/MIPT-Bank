#include "person_director.h"

person *person_director::create_person(person_builder &pb) {
  pb.create_person();

  pb.set_person_first_name();
  pb.set_person_second_name();
  pb.set_person_address();
  pb.set_person_passport_id();
  pb.person->update();

  pb.get_person();
}
