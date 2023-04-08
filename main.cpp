#include <iostream>
#include "src/include/sqlite-amalgamation-3410200/sqlite3.h"
#include "src/include/person_director.h"
#include "src/include/person_db.h"

//TODO перевести директора на интерфейс

namespace person_build {

  person *
  build_person(const std::string &f_n, const std::string &s_n, const std::string &ad, const std::string &pas_id) {
    person_director dire;
    person_builder pb;
    pb.set_first_name(f_n)->set_second_name(s_n)->set_passport_id(pas_id)->set_address(ad);
    person *person = dire.create_person(pb);
    return person;
  }

}  // END PERSON_DB NAMESPACE

int main() {
  char *err;
  sqlite3 *db;
  person *person1;
  person* per;
  try {
    person_db pdb(db, err);
      person1 = person_build::build_person("a", "b", "c", "d");  // тут ввод из fronted-а будет
      pdb.save_person(person1);
      per = dynamic_cast<person*>(pdb.find_person(0));
  } catch (const std::string &s) {
    std::cout << s << std::endl;
  }


  std::cout << std::endl;
  delete person1;
  delete err;
  delete db;

  return 0;
}