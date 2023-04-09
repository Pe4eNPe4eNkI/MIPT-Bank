#ifndef MIPTBANK_PERSON_DB_H
#define MIPTBANK_PERSON_DB_H

#pragma once
#include <fstream>
#include "iperson.h"
#include "sqlite-amalgamation-3410200/sqlite3.h"
#include "person_director.h"

class person_db{
public:
  person_db(sqlite3* db, char* err);
  bool save_person(iperson* person);
  iperson* find_person(const big_int& id);
  void rewrite_max_id () const;

private:
  sqlite3* db_;
  char* err_;
};

#endif //MIPTBANK_PERSON_DB_H
