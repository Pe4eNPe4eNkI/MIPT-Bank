#ifndef MIPT_BANK__TRANSFER_H_
#define MIPT_BANK__TRANSFER_H_

#include "imoney_operation.h"
#include "bill_db.h"

class transfer : public i_neasy_money_operation {
public:

  explicit transfer(const bill_db &trans) : trans_(trans) {};

  void execute_transfer_operation(const big_int &send_bill_id,
                                  const big_int &receiver_bill_id,
                                  const big_int &money_size) override;

  void cancel_transfer_operation(const big_int &send_bill_id,
                                 const big_int &receiver_bill_id,
                                 const big_int &money_size) override;

private:
  bill_db trans_;

};

#endif
