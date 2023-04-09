#ifndef MIPT_BANK__TRANSFER_H_
#define MIPT_BANK__TRANSFER_H_

#include "imoney_operation.h"
#include "bill_db.h"

class transfer : public imoney_operation {
 private:
  bill_db* trans_;

 public:

  transfer(bill_db* trans) : trans_(trans) {};

  bool execute_transfer_operation(const big_int& send_bill_id,
                                  const big_int& receiver_bill_id,
                                  const big_int& money_size) override;

  bool cancel_transfer_operation(const big_int& send_bill_id,
                                 const big_int& receiver_bill_id,
                                 const big_int& money_size) override;
};

#endif