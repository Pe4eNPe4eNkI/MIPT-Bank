#ifndef MIPT_BANK__TRANSFER_H_
#define MIPT_BANK__TRANSFER_H_

class transfer : public imoney_operation {
 public:

  bool execute_transfer(int send_bill_id, int receiver_bill_id);

  bool cancel_transfer(int send_bill_id, int receiver_bill_id);
};

#endif