#ifndef MIPT_BANK__IMPNEY_OPERATION_H_
#define MIPT_BANK__IMPNEY_OPERATION_H_

#include "big_int.h"

class imoney_operation {
 public:

  virtual bool execute_operation(const big_int& receiver_bill_id, const big_int& money_size) = 0;

  virtual bool cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) = 0;

  virtual bool execute_transfer_operation(const big_int& send_bill_id,
                                          const big_int& receiver_bill_id,
                                          const big_int& money_size) = 0;

  virtual bool cancel_transfer_operation(const big_int& send_bill_id,
                                         const big_int& receiver_bill_id,
                                         const big_int& money_size) = 0;

};

#endif
