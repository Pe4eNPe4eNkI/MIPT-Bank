#include "include/withdrawal.h"

void withdrawal::execute_operation(const big_int& receiver_bill_id, const big_int& money_size) {

  ibill* receiver = trans_.bill_delete_and_find(receiver_bill_id); // rename function

  if (receiver->get_cash_size() < money_size) {
    trans_.create_bill_query(receiver);
    throw std::string("fuck you, lox!");
  }
  receiver->set_cash_size(receiver->get_cash_size() - money_size);

  trans_.create_bill_query(receiver);
}

void withdrawal::cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) {

  ibill* receiver = trans_.bill_delete_and_find(receiver_bill_id); // rename function

  receiver->set_cash_size(receiver->get_cash_size() + money_size);

  trans_.create_bill_query(receiver);

}