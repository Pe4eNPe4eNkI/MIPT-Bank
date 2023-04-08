#ifndef MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_
#define MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_

#include "sqlite-amalgamation-3410200/sqlite3.h"
#include "big_int.h"
#include "bill_factory.h"
#include <string>
#include <iostream>

class bill_db {
 private:
  char* err_;
  sqlite3* bill_db_;

 public:
  bill_db(char* err, sqlite3* bill_db);

  void create_bill_query(credit person_bill);

  credit bill_find(const big_int& bill_id);
};

#endif
