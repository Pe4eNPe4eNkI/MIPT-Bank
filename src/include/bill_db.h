#ifndef MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_
#define MIPTBANK_MIPT_BANK_SRC_BILL_DB_H_

#pragma once

#include "sqlite-amalgamation-3410200/sqlite3.h"
#include "big_int.h"
#include "bill_factory.h"
#include <string>
#include <iostream>
#include <fstream>

class bill_db {
public:
  bill_db(char *err, sqlite3 *bill_db);

  void create_bill_query(ibill *person_bill);

  ibill *bill_find(const big_int &bill_id);

  ibill *bill_delete_and_find(const big_int &bill_id);

  void rewrite_max_id();

  ~bill_db();

private:
  char *err_;
  sqlite3 *bill_db_;

};

#endif
