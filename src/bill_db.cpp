#include "include/bill_db.h"

bill_db::bill_db(char *err, sqlite3 *bill_db) : err_(err), bill_db_(bill_db) {
  sqlite3_open("myDb.db", &bill_db_);

  std::string query = "CREATE TABLE IF NOT EXISTS bill("
                      "person_id varchar(10), "
                      "bill_id varchar(10), "
                      "bill_kind varchar(10), "
                      "cash_size varchar(10));";

  auto bill = sqlite3_exec(bill_db_, query.c_str(), NULL, NULL, &err_);
  if (bill != SQLITE_OK) {
    throw std::string("Database error: " + std::string(err_));
  }
}

void bill_db::create_bill_query(ibill *person_bill) {
  std::string bill_query = "INSERT INTO bill VALUES('" + person_bill->get_person_id().toString() + "', '"
                           + person_bill->get_bill_id().toString() + "', '" + person_bill->get_bill_kind() + "', '"
                           + person_bill->get_cash_size().toString() + "');";
  std::cout << bill_query << "\n\n";
  auto bill = sqlite3_exec(bill_db_, bill_query.c_str(), NULL, NULL, &err_);

  if (bill != SQLITE_OK) {
    throw std::string("Database error: " + std::string(err_));
  }
}

ibill *bill_db::bill_find(const big_int &bill_id) {
  std::string bill_query = "SELECT * FROM bill WHERE bill_id = ? LIMIT 1;";
  sqlite3_stmt *stmt;

  int rc = sqlite3_prepare_v2(bill_db_, bill_query.c_str(), -1, &stmt, nullptr);

  if (rc != SQLITE_OK) {
    sqlite3_close(bill_db_);
    throw std::string("Fuck u asshole ");
  }

  rc = sqlite3_bind_text(stmt, 1, bill_id.toString().c_str(), -1, SQLITE_TRANSIENT);

  if (sqlite3_step(stmt) == SQLITE_ROW) {
    big_int person_id(reinterpret_cast<const char *>(sqlite3_column_text(stmt, 0)));
    std::string bill_kind = (reinterpret_cast<const char *>(sqlite3_column_text(stmt, 2)));
    const char *a = reinterpret_cast<const char *>(sqlite3_column_text(stmt, 3));
    big_int cash_size(a);
    std::cout << "Person id: " << person_id << " Bill id: " << bill_id << " Bill kind: " << bill_kind << " Cash size: "
              << cash_size << std::endl << std::endl;

    sqlite3_finalize(stmt);
    bill_factory factory;
    rewrite_max_id();
    if (bill_kind == BILL_KIND_CREDIT) {
      credit* cr = factory.build_credit(person_id);
      cr->set_cash_size(cash_size);
      cr->set_bill_id(bill_id);
      return cr;
    } else if (bill_kind == BILL_KIND_DEBIT) {
      debit* db = factory.build_debit(person_id);
      db->set_cash_size(cash_size);
      db->set_bill_id(bill_id);
      return db;
    } else if (bill_kind == BILL_KIND_DEPOSIT) {
      deposit* de = factory.build_deposit(person_id);
      de->set_cash_size(cash_size);
      de->set_bill_id(bill_id);
      return de;
    }

  } else {
    throw std::string("Fuck u asshole can`t find bill");
  }
  return nullptr;
}

void bill_db::rewrite_max_id() {
  std::string query = "SELECT MAX(bill_id) FROM bill;";

  sqlite3_stmt *stmt;

  auto rc = sqlite3_prepare_v2(bill_db_, query.c_str(), -1, &stmt, nullptr);

  if (rc != SQLITE_OK) {
    throw std::string("Fuck u asshole " + std::string(err_));
  }

  rc = sqlite3_step(stmt);

  if (rc != SQLITE_ROW) {
    sqlite3_finalize(stmt);
    throw std::string("Can`t find anything");
  }

  std::string max_id = reinterpret_cast<const char *>(sqlite3_column_text(stmt, 0));

  std::ofstream file("max_bill_id.txt");
  if (file.is_open()) {
    file << max_id;
  }
  file.close();
  sqlite3_finalize(stmt);
}

ibill *bill_db::bill_delete_and_find(const big_int &bill_id) {
  ibill *p = bill_find(bill_id);

  std::string query = "DELETE FROM bill WHERE bill_id = '" + bill_id.toString() + "';";

  auto rc = sqlite3_exec(bill_db_, query.c_str(), nullptr, nullptr, &err_);

  if (rc != SQLITE_OK) {
    throw std::string("aaa u asshole " + std::string(err_));
  }
  return p;
}

bill_db::~bill_db() {
  sqlite3_close(bill_db_);
}