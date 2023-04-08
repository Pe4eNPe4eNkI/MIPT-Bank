#ifndef MIPT_BANK__WITHDRAWAL_H_
#define MIPT_BANK__WITHDRAWAL_H_

class withdrawal : public imoney_operation {
 public:

  bool execute_transfer(int send_bill_id, int receiver_bill_id);

  bool cancel_transfer(int send_bill_id, int receiver_bill_id);
};

#endif