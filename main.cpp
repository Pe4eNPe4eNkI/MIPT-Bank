#include <iostream>
#include "sqlite-amalgamation-3410200/sqlite3.h"
#include "src/include/person_director.h"

int main() {
  /*char * err;
  sqlite3* db;
  sqlite3_stmt* stmt;
  sqlite3_open("myDb.db", &db);

  auto rc = sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS persons(first_name TEXT, second_name TEXT, "
                                                                     "address TEXT, passport_id TEXT,"
                                                                     "money_limit INT, is_doubtful INT,"
                                                                     "id TEXT);", NULL, NULL, &err);

  if (rc != SQLITE_OK) {
    std::cout << "Fuck u asshole" << err;
  }

  for (int i = 0; i < 10; i++) {
    std::string query = "insert into algolancer VALUES ("+std::to_string(i)+", "+std::to_string(i + 5)+", 'Arsenyi have sex with Polymetal');";
    std::cout << query << "\n";
    rc = sqlite3_exec(db, query.c_str(), NULL, NULL, &err);

    if (rc != SQLITE_OK) {
      std::cout << "Fuck u asshole" << err;
    }
  }*/
    try {
      person_director dire;
      person_builder pb;
      pb.set_second_name("b")->set_passport_id("c")->set_address("d")->set_first_name("a");
      person *person = dire.create_person(pb);


      std::cout << person->get_first_name() << std::endl;
      std::cout << person->get_second_name() << std::endl;
      std::cout << person->get_address() << std::endl;
      std::cout << person->get_passport_id() << std::endl;
      std::cout << person->get_id() << std::endl;
    } catch (const std::string &s) {
      std::cout << s << std::endl;
      return -1;
    }

    std::cout << std::endl;
    return 0;
  }
