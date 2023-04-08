#ifndef MIPT_BANK__CREDIT_H_
#define MIPT_BANK__CREDIT_H_

#pragma once
#include <string>
#include "big_int.h"

class credit : public ibill{
public:

  big_int get_cash_size() const override;

  std::string get_bill_kind() const override;

  big_int get_bill_id() const override;

  big_int get_person_id() const override;

  big_int get_accessible_cash_size() const;

  big_int get_debt_size() const;

  big_int get_payment_on_this_month() const;


  void set_cash_size(const big_int &cash_size);

  void set_accessible_cash_size(const big_int &accessible_cash_size);

  void set_debt_size(const big_int &debt_size);

  void set_payment_on_this_month(const big_int &payment_on_this_month);

  void set_bill_kind(const int &bill_kind);

  void set_unique_id(const big_int &unique_id);

  void set_person_id(const big_int &person_id);

  void update();

private:
  big_int accessible_cash_size_;
  big_int payment_on_this_month_;
  big_int debt_size_;
};

#endif //MIPT_BANK__CREDIT_H_
