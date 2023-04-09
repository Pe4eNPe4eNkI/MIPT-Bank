#include "include/refill.h"

bool refill::execute_operation(const big_int& receiver_bill_id, const big_int& money_size) {
  if (!trans_) {
    throw std::string("trans is null");
  }

  ibill* receiver = trans_->bill_find(receiver_bill_id); // rename function

  receiver->set_cash_size(receiver->get_cash_size() + money_size);

}

bool refill::cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) {

  if (!trans_) {
    throw std::string("trans is null");
  }

  ibill* receiver = trans_->bill_find(receiver_bill_id); // rename function

  if (receiver->get_cash_size() < money_size) {
    throw std::string("fuck you, lox!");
  }
  receiver->set_cash_size(receiver->get_cash_size() - money_size);

}