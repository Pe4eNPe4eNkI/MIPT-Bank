#ifndef MIPTBANK_PERSON_DB_H
#define MIPTBANK_PERSON_DB_H

#pragma once
#include "iperson.h"
#include "sqlite-amalgamation-3410200/sqlite3.h"

class person_db{
public:
  person_db(sqlite3* db, char* err);
  bool save_person(iperson* person);
  iperson* find_person(const big_int& id);

private:
  sqlite3* db_;
  char* err_;
};

#endif //MIPTBANK_PERSON_DB_H
