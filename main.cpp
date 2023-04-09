#include<iostream>
#include "src/include/sqlite-amalgamation-3410200/sqlite3.h"
#include "src/include/bill_db.h"

//TODO перевести директора на интерфейс

ibill* build_bill(const big_int& person_id, const std::string& bill_kind) {
  bill_factory factory;
  if (bill_kind == "credit") {
    return factory.build_credit(person_id);
  }
}
int main() {
  char* err;
  sqlite3* db;
  credit* credit1;
  credit* cre;
  try {
    bill_db pdb(err, db);
    credit1 = dynamic_cast<credit*>(build_bill(3, "credit"));  // тут ввод из fronted-а будет
    pdb.create_bill_query(credit1);
    cre = dynamic_cast<credit*>(pdb.bill_find(3));
  } catch (const std::string& s) {
    std::cout << s << std::endl;
  }

  std::cout << std::endl;
  delete err;
  delete credit1;
  delete cre;
  delete db;
  return 0;
}
