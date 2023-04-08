#include "include/bill_factory.h"

deposit bill_factory::build_deposit(const big_int& person_id) const {
  deposit deposit_;
  deposit_.set_person_id(person_id);
  deposit_.set_cash_size(0);
  deposit_.set_income_potential_size(deposit_.get_cash_size() / 100 * 10);
  return deposit_;
}

credit bill_factory::build_credit(const big_int& person_id) const {
  credit credit_;
  credit_.set_person_id(person_id);
  credit_.set_cash_size(100000000);
  credit_.set_accessible_cash_size(100000000);
  credit_.set_debt_size(0);
  credit_.set_payment_on_this_month(0);
  return credit_;
}

debit bill_factory::build_debit(const big_int& person_id) const {
  debit debit_;
  debit_.set_person_id(person_id);
  debit_.set_cash_size(0);
  debit_.set_cashback_potential_size(debit_.get_cash_size() / 100 * 5);
  return debit_;
}