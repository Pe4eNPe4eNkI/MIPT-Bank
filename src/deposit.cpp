#include "include/deposit.h"

big_int deposit::bill_id = 0;

big_int deposit::get_cash_size() const {
  return cash_size_;
}

std::string deposit::get_bill_kind() const {
  return BILL_KIND_DEPOSIT;
}

big_int deposit::get_income_potential_size() const {
  return income_potential_size_;
}

big_int deposit::get_bill_id() const {
  return bill_id_;
}

big_int deposit::get_person_id() const {
  return person_id_;
}

void deposit::set_cash_size(const big_int& cash_size) {
  cash_size_ = cash_size;
}

void deposit::set_income_potential_size(const big_int& income_potential_size) {
  income_potential_size_ = income_potential_size;
}

void deposit::set_person_id(const big_int& person_id) {
  person_id_ = person_id;
}

void deposit::update() {
  income_potential_size_ = cash_size_ / 100 * 10;
}

void deposit::assign_id() {
  bill_id_ = bill_id++;
}
