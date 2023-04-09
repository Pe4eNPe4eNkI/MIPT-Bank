#include "include/transfer.h"
#include "include/bill_db.h"

bool  transfer::execute_transfer_operation(const big_int& send_bill_id,
                                const big_int& receiver_bill_id,
                                const big_int& money_size) {

  ibill* receiver;
  receiver;
  ibill* send;
  bill_db::bill_find(send_bill_id);


  receiver.cash_size_ += money_size;
  receiver.cash_size -= money_size;

}

bool transfer::cancel_transfer_operation(const big_int& send_bill_id,
                               const big_int& receiver_bill_id,
                               const big_int& money_size) {

  ibill* receiver = bill_db::bill_find(receiver_bill_id);
  ibill* send = bill_db::bill_find(send_bill_id);

  receiver.cash_size_ -= money_size;
  receiver.cash_size += money_size;

}