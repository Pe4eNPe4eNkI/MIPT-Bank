#include "include/withdrawal.h"
#include "include/bill_db.h"

bool withdrawal::execute_operation(const big_int& receiver_bill_id, const big_int& money_size) {
  ibill* receiver = bill_db::bill_find(receiver_bill_id);
  receiver.cash_size_ -= money_size;
}

bool withdrawal::cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) {
  ibill* receiver = bill_db::bill_find(receiver_bill_id);
  receiver.cash_size_ += money_size;}