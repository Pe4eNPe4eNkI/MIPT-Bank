#include "include/transfer.h"

void transfer::execute_transfer_operation(const big_int &send_bill_id,
                                          const big_int &receiver_bill_id,
                                          const big_int &money_size) {

  ibill *send = trans_.bill_delete_and_find(send_bill_id);
  ibill *receiver = trans_.bill_delete_and_find(receiver_bill_id);

  if (send->get_cash_size() < money_size) {
    throw std::string("fuck you, lox!");
  }
  send->set_cash_size(send->get_cash_size() - money_size);
  receiver->set_cash_size(receiver->get_cash_size() + money_size);

  trans_.create_bill_query(send);
  trans_.create_bill_query(receiver);

}

void transfer::cancel_transfer_operation(const big_int &send_bill_id,
                                         const big_int &receiver_bill_id,
                                         const big_int &money_size) {

  ibill *send = trans_.bill_delete_and_find(send_bill_id);
  ibill *receiver = trans_.bill_delete_and_find(receiver_bill_id);

  if (receiver->get_cash_size() < money_size) {
    throw std::string("krasava, proizoshol scum :)");
  }
  send->set_cash_size(send->get_cash_size() + money_size);
  receiver->set_cash_size(receiver->get_cash_size() - money_size);

  trans_.create_bill_query(send);
  trans_.create_bill_query(receiver);

}