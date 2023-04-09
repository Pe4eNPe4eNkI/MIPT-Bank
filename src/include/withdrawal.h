#ifndef MIPT_BANK__WITHDRAWAL_H_
#define MIPT_BANK__WITHDRAWAL_H_

#include "imoney_operation.h"

class withdrawal : public imoney_operation {
 public:

  bool execute_operation(const big_int& receiver_bill_id,
                                  const big_int& money_size) override;

  bool cancel_operation(const big_int& receiver_bill_id,
                                 const big_int& money_size) override;
};

#endif