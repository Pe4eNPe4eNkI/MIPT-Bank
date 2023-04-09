#ifndef MIPT_BANK__TRANSFER_H_
#define MIPT_BANK__TRANSFER_H_

#include "imoney_operation.h"

class transfer : public imoney_operation {
 public:

  bool execute_transfer_operation(const big_int& send_bill_id,
                                  const big_int& receiver_bill_id,
                                  const big_int& money_size) override;

  bool cancel_transfer_operation(const big_int& send_bill_id,
                                 const big_int& receiver_bill_id,
                                 const big_int& money_size) override;
};

#endif