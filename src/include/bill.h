#ifndef MIPT_BANK__IBILL_H_
#define MIPT_BANK__IBILL_H_

class bill {
 private:

  std::string bill_kind_;
  int bill_id_;
  int person_id_;

 public:

  std::string get_bill_kind() const;

  int get_bill_id() const;

  int get_person_id() const;

};

#endif