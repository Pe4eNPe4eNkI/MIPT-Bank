#include "include/debit.h"


big_int debit::get_cash_size() const {
  return cash_size_;
}

void debit::set_bill_id(const big_int &id) {
  bill_id_ = id;
}

big_int debit::get_cashback_potential_size() const {
  return cashback_potential_size_;
}

std::string debit::get_bill_kind() const {
  return BILL_KIND_DEBIT;
}

big_int debit::get_bill_id() const {
  return bill_id_;
}

big_int debit::get_person_id() const {
  return person_id_;
}

void debit::set_cash_size(const big_int &cash_size) {
  cash_size_ = cash_size;
}

void debit::set_cashback_potential_size(const big_int &cashback_potential_size) {
  cashback_potential_size_ = cashback_potential_size;
}

void debit::set_person_id(const big_int &person_id) {
  person_id_ = person_id;
}

void debit::update() {
  cashback_potential_size_ = cash_size_ / 100 * 5;
}

void debit::assign_id() {
  std::ifstream file("max_bill_id.txt");
  std::string s;
  file >> s;
  bill_id = big_int(s);
  if (bill_id == 0) {
    bill_id++;
    bill_id_ = 0;
  } else bill_id_ = ++bill_id;

  file.close();
  std::ofstream file1("max_bill_id.txt");
  file1 << bill_id.toString();
  file1.close();
}