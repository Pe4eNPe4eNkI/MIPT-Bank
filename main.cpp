#include <iostream>
#include "src/include/sqlite-amalgamation-3410200/sqlite3.h"
#include "src/include/person_director.h"
#include "src/include/person_db.h"
#include "src/include/bill_db.h"
#include "src/include/refill.h"
#include "src/include/withdrawal.h"
#include "src/include/transfer.h"

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

}  // END PERSON_BUILD NAMESPACE

namespace bill_build {
  ibill *build_bill(const big_int &person_id, const std::string &bill_kind) {
    bill_factory factory;
    if (bill_kind == "credit") {
      return factory.build_credit(person_id);
    }
    if (bill_kind == "debit") {
      return factory.build_debit(person_id);
    }
    if (bill_kind == "deposit") {
      return factory.build_deposit(person_id);
    }
  }
}  // END BILL_BUILD NAMESPACE

int main() {  // вывод в консоль для того чтобы показать что все работает
  char *err;
  sqlite3 *db;
  person *person1;
  person *per;
  credit *credit1;
  credit *cre;
  try {

    person_db pdb(db, err);  // подключение к бд
    //pdb.rewrite_max_id();  // если бд упала узнаем макс id , бд не должна быть пустой, раскоментить если упала
    person1 = person_build::build_person("a", "b", "c", "d");  // тут ввод из fronted-а будет  // создаем персона
    pdb.save_person(person1);  //сохраняем в бд
    person1 = person_build::build_person("g", "n", "v", "s");
    pdb.save_person(person1);

    per = dynamic_cast<person *>(pdb.find_person(0));  // ищем в бд если нет то nullptr


    bill_db bdb(err, db);
    credit1 = dynamic_cast<credit *>(bill_build::build_bill(4, "credit"));  // тут ввод из fronted-а будет
    bdb.create_bill_query(credit1);
    credit1 = dynamic_cast<credit *>(bill_build::build_bill(5, "credit"));  // тут ввод из fronted-а будет
    bdb.create_bill_query(credit1);
    cre = dynamic_cast<credit *>(bdb.bill_find(0));

    refill r(bdb);
    r.execute_operation(1, 100);

    withdrawal w(bdb);
    w.execute_operation(1, 200);

    transfer t(bdb);
    t.execute_transfer_operation(0, 1, 500);


  } catch (const std::string &s) {
    std::cout << s << std::endl;
  }
  catch (const std::logic_error &le) {
    std::cout << le.what() << std::endl;
  }
  catch (...) {
    std::cout << "Unknown error" << std::endl;
  }
  std::cout << std::endl;

  return 0;
}
