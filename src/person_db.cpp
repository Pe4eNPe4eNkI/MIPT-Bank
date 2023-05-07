#include "include/person_db.h"

namespace persondb_build {

  person *
  build_person(const std::string &f_n, const std::string &s_n, const std::string &ad, const std::string &pas_id) {
    person_director dire;
    person_builder pb;
    pb.set_first_name(f_n)->set_second_name(s_n)->set_passport_id(pas_id)->set_address(ad);
    person *person = dire.create_person(pb);
    person::id--;
    return person;
  }

}  // END PERSON_DB NAMESPACE

person_db::person_db(sqlite3 *db, char *err) : db_(db), err_(err) {
  sqlite3_open("myDb.db", &db_);
  auto check_db = sqlite3_exec(db_,
                               "CREATE TABLE IF NOT EXISTS persons(first_name varchar(50), second_name varchar(50), "
                               "address varchar(50), passport_id varchar(50), "
                               "money_limit INT, is_doubtful INT, "
                               "id varchar(10));", NULL, NULL, &err_);
  if (check_db != SQLITE_OK) {
    throw std::string("Fuck u asshole " + std::string(err_));
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
    throw std::string("Fuck u asshole " + std::string(err_));
  }
}

iperson *person_db::find_person(const big_int &id) {
  std::string query = "SELECT * FROM persons WHERE id = ? LIMIT 1;";
  sqlite3_stmt *stmt;
  person *person;

  int rc = sqlite3_prepare_v2(db_, query.c_str(), -1, &stmt, nullptr);

  if (rc != SQLITE_OK) {
    throw std::string("Fuck u asshole " + std::string(err_));
  }

  rc = sqlite3_bind_text(stmt, 1, id.toString().c_str(), -1, SQLITE_TRANSIENT);
  std::string first_name;
  std::string second_name;
  std::string address;
  std::string passport_id;
  int money_limit;
  bool is_doubtful;

  if (sqlite3_step(stmt) == SQLITE_ROW) {
    first_name = (reinterpret_cast<const char *>(sqlite3_column_text(stmt, 1)));
    second_name = (reinterpret_cast<const char *>(sqlite3_column_text(stmt, 2)));
    address = (reinterpret_cast<const char *>(sqlite3_column_text(stmt, 3)));
    passport_id = (reinterpret_cast<const char *>(sqlite3_column_text(stmt, 4)));
    money_limit = (sqlite3_column_int(stmt, 5));
    is_doubtful = (sqlite3_column_int(stmt, 6));

    std::cout << "Name: " << first_name << ", Surname: " << second_name << ", Address: " << address << ", Passport: "
              << passport_id << ", Money_limit: " << money_limit << ", Is_doubtful: " << is_doubtful << std::endl;
  } else {
    throw std::string("Can`t find person");
  }
  sqlite3_finalize(stmt);
  return persondb_build::build_person(first_name, second_name, address, passport_id);
}

void person_db::rewrite_max_id() const {
  std::string query = "SELECT MAX(id) FROM persons;";

  sqlite3_stmt *stmt;

  auto rc = sqlite3_prepare_v2(db_, query.c_str(), -1, &stmt, nullptr);

  if (rc != SQLITE_OK) {
    throw std::string("Fuck u asshole " + std::string(err_));
  }

  rc = sqlite3_step(stmt);

  if (rc != SQLITE_ROW) {
    sqlite3_finalize(stmt);
    throw std::string("Can`t find anything");
  }

  std::string max_id = reinterpret_cast<const char *>(sqlite3_column_text(stmt, 0));

  std::cout << "max_id: " << max_id << std::endl;
  std::ofstream file("max_person_id.txt");
  if (file.is_open()) {
    file << max_id;
  }
  file.close();
  sqlite3_finalize(stmt);
}

person_db::~person_db() {
  sqlite3_close(db_);
}