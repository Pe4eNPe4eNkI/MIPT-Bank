#ifndef MIPT_BANK__REFIL_H_
#define MIPT_BANK__REFIL_H_
#pragma omce

#include "imoney_operation.h"
#include "bill_db.h"

class refill : public imoney_operation {
 private:
  bill_db* trans_;

 public:

  bool execute_operation(const big_int& receiver_bill_id, const big_int& money_size) override;

  bool cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) override;
};

#endif