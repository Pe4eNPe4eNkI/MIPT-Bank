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

void bill_db::create_bill_query(sqllite3* bill_bd, bill person_bill) {
  sqlite3_open("bill.db", &bill_db);
  std::string bill_query = "INSERT INTO bill VALUES('" + std::to_string(person_bill.get_person_id()) + "', '"
      + std::to_string(person_bill.get_bill_id()) + "', '" + person_bill.get_bill_kind() + "');";
  std::cout << query << "\n";
  bill = sqlite3_exec(bill_db, bill_query.c_str(), NULL, NULL, &err);

  if (bill != SQLITE_OK) {
    std::cout << "Database error: " << err;
  }
}

/*ibill& bill_db::bill_find(sqllite3* bill_bd, std::string person_id) {
  sqlite3_open("bill.db", &bill_db);
  bill_query = """SELECT * from bill""";
  bill = sqllite3_exec(bill_db, bill_query.c_str(), NULL, NULL, &err);

}
*/
