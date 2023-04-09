#ifndef MIPT_BANK__REFIL_H_
#define MIPT_BANK__REFIL_H_
#pragma omce

#include "imoney_operation.h"
#include "bill_db.h"

class refill : public i_easy_money_operation {
 private:
  bill_db trans_;

 public:

  explicit refill(const bill_db& trans) : trans_(trans) {};

  void execute_operation(const big_int& receiver_bill_id, const big_int& money_size) override;

  void cancel_operation(const big_int& receiver_bill_id, const big_int& money_size) override;
};

#endif