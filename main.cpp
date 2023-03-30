#include <iostream>
#include "person_director.h"

int main() {

  person_director dir;
  person_builder pb;
  pb.set_first_name("a")->set_second_name("b")->set_passport_id("c")->set_address("d");
  person *person = dir.create_person(pb);

  return 0;
}
