#include "include/bill_db.h"
#include "include/bill.h"
#include <string>

void bill_db::create_bill_db(char* err, sqllite3* bill_db) {
  sqlite3_open("bill.db", &bill_db);

  bill = sqlite3_exec(bill_db, "CREATE TABLE IF NOT EXISTS bill("
                               "person_id INT PRIMARY KEY, "
                               "bill_id INT, "
                               "bill_kind INT;", NULL, NULL, &err);
  if (bill != SQLITE_OK) {
    std::cout << "Database error: " << err;
  }
}

void bill_db::create_bill_query() {
  std::string bill_query = "INSERT INTO bill VALUES('" + std::to_string(person_bill.get_person_id()) + "', '"
      + std::to_string(person_bill.get_bill_id()) + "', '" + person_bill.get_bill_kind() + "');";
  std::cout << query << "\n";
  bill = sqlite3_exec(bill_db, bill_query.c_str(), NULL, NULL, &err);

  if (bill != SQLITE_OK) {
    std::cout << "Database error: " << err;
  }
}

ibill* bill_db::find_bill(const big_int& person_id) {
  std::string bill_query = "SELECT * FROM bill WHERE id = ? LIMIT 1;";
  sqlite3_stmt* stmt;
  bill* bill;

  int rc = sqlite3_prepare_v2(bill_db_db_, bill_query.c_str(), -1, &stmt, nullptr);

  if (rc != SQLITE_OK) {
    sqlite3_close(bill_db_);
    throw std::string("Fuck u asshole " + std::string(err_));
  }

  rc = sqlite3_bind_text(stmt, 1, person_id.toString().c_str(), -1, SQLITE_TRANSIENT);
  std::string first_name;
  std::string second_name;
  std::string address;
  std::string passport_id;
  int money_limit;
  bool is_doubtful;

  if (sqlite3_step(stmt) == SQLITE_ROW) {
    first_name = (reinterpret_cast<const char*>(sqlite3_column_text(stmt, 1)));
    second_name = (reinterpret_cast<const char*>(sqlite3_column_text(stmt, 2)));
    address = (reinterpret_cast<const char*>(sqlite3_column_text(stmt, 3)));
    passport_id = (reinterpret_cast<const char*>(sqlite3_column_text(stmt, 4)));
    money_limit = (sqlite3_column_int(stmt, 5));
    is_doubtful = (sqlite3_column_int(stmt, 6));

    std::cout << "Name: " << first_name << ", Surname: " << second_name << ", Address: " << address << ", Passport: "
              << passport_id << ", Money_limit: " << money_limit << ", Is_doubtful: " << is_doubtful << std::endl;
  } else {
    throw std::string("Can`t find person");
  }
  sqlite3_finalize(stmt);
  return persondb_build::build_person(first_name, second_name, address, passport_id);;
}