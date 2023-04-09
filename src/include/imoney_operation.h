#ifndef MIPT_BANK__IMPNEY_OPERATION_H_
#define MIPT_BANK__IMPNEY_OPERATION_H_

#include "big_int.h"

class i_easy_money_operation {
 public:

  virtual void execute_operation(const big_int& receiver_bill_id, const big_int& money_size) = 0;

  virtual void cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) = 0;

};

class i_neasy_money_operation{
public:
  virtual void execute_transfer_operation(const big_int& send_bill_id,
                                          const big_int& receiver_bill_id,
                                          const big_int& money_size) = 0;

  virtual void cancel_transfer_operation(const big_int& send_bill_id,
                                         const big_int& receiver_bill_id,
                                         const big_int& money_size) = 0;
};

#endif
