#include <iostream>
#include "person_director.h"

int main() {

  person_director dire;
  person_builder pb;
  pb.set_first_name("a")->set_second_name("b")->set_passport_id("c")->set_address("d");
  person *person = dire.create_person(pb);


  std::cout << person->get_first_name() << std::endl;
  std::cout << person->get_second_name() << std::endl;
  std::cout << person->get_address() << std::endl;
  std::cout << person->get_passport_id() << std::endl;
  

  return 0;
}
