#ifndef MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_
#define MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_

#include "sqlite-amalgamation-3410200/sqllite3.h"
#include "iostream"

class bill_db {
 private:
  char* err_;
  sqlite3* bill_db_;

 public:
  void create_bill_db(char* err, sqlite3* bill_db);

  void create_bill_query(sqllite3* bill_bd, std::string person_id, std::string bill_id, std::string bill_kind);

  //ibill& bill_find(sqllite3* bill_bd, std::string person_id);
};

#endif
