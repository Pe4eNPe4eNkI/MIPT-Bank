#include "include/person_db.h"

person_db::person_db(sqlite3 *db, char *err) : db_(db), err_(err) {
  sqlite3_open("myDb.db", &db_);
  auto check_db = sqlite3_exec(db_,
                               "CREATE TABLE IF NOT EXISTS persons(first_name varchar(50), second_name varchar(50), "
                               "address varchar(50), passport_id varchar(50), "
                               "money_limit INT, is_doubtful INT, "
                               "id varchar(10));", NULL, NULL, &err_);
  if (check_db != SQLITE_OK) {
    std::cout << "Fuck u asshole " << err_;
  }
}

bool person_db::save_person(iperson *person) {
  std::string query =
          "insert into persons VALUES ('" + person->get_first_name() + "', '" + person->get_second_name() + "', '" +
          person->get_address() +
          "', '" + person->get_passport_id() + "', '" + std::to_string(person->get_money_limit()) + "', '" +
          std::to_string(person->is_doubtful()) + "', '" + person->get_id().toString() +
          "');";
  std::cout << query << std::endl;

  if (sqlite3_exec(db_, query.c_str(), NULL, NULL, &err_) != SQLITE_OK) {
    std::cout << "Fuck u asshole " << err_ << std::endl;
    throw std::string("Can`t save person");
  }
}

iperson *person_db::find_person(const big_int &id) {

}