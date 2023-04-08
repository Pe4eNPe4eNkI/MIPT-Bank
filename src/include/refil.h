#ifndef MIPT_BANK__REFIL_H_
#define MIPT_BANK__REFIL_H_

class refill : public imoney_operation{
 public:

  bool execute(int receiver_bill_id);

  bool cancel(int receiver_bill_id);
};

#endif