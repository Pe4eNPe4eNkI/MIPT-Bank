#include "include/person_director.h"

person *person_director::create_person(person_builder &pb) {
  if (pb.check_args()) throw std::string("Bad Input");

  pb.create_person();

  pb.set_person_first_name();
  pb.set_person_second_name();
  pb.set_person_address();
  pb.set_person_passport_id();
  pb.get_person()->assign_id();
  pb.get_person()->update();

  return pb.get_person();
}
