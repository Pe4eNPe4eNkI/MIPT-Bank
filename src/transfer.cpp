#include "include/transfer.h"

bool transfer::execute_transfer_operation(const big_int& send_bill_id,
                                          const big_int& receiver_bill_id,
                                          const big_int& money_size) {
  if (!trans_) {
    throw std::string("trans is null");
  }

  ibill* send = trans_->bill_find(send_bill_id); // rename function
  ibill* receiver = trans_->bill_find(receiver_bill_id); // rename function

  if (send->get_cash_size() < money_size) {
    throw std::string("fuck you, lox!");
  }
  send->set_cash_size(send->get_cash_size() - money_size);
  receiver->set_cash_size(receiver->get_cash_size() + money_size);

}

bool transfer::cancel_transfer_operation(const big_int& send_bill_id,
                                         const big_int& receiver_bill_id,
                                         const big_int& money_size) {

  if (!trans_) {
    throw std::string("trans is null");
  }

  ibill* send = trans_->bill_find(send_bill_id); // rename function
  ibill* receiver = trans_->bill_find(receiver_bill_id); // rename function

  if (receiver->get_cash_size() < money_size) {
    throw std::string("krasava, proizoshol scum :)");
  }
  send->set_cash_size(send->get_cash_size() + money_size);
  receiver->set_cash_size(receiver->get_cash_size() - money_size);

}