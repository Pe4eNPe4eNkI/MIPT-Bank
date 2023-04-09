#ifndef MIPT_BANK__CREDIT_H_
#define MIPT_BANK__CREDIT_H_

#pragma once
#include <string>
#include "big_int.h"
#include "ibill.h"

const std::string BILL_KIND_CREDIT = "credit";

class credit : public ibill {
 public:

  std::string get_bill_kind() const override;

  big_int get_cash_size() const override;

  big_int get_bill_id() const override;

  big_int get_person_id() const override;

  big_int get_accessible_cash_size() const;

  big_int get_debt_size() const;

  big_int get_payment_on_this_month() const;

  void set_cash_size(const big_int& cash_size) override;

  void set_bill_id(const big_int& id) override;

  void set_accessible_cash_size(const big_int& accessible_cash_size);

  void set_debt_size(const big_int& debt_size);

  void set_payment_on_this_month(const big_int& payment_on_this_month);

  void set_person_id(const big_int& person_id) override;

  void update();

  void assign_id() override;

 private:
  std::string bill_kind_ = BILL_KIND_CREDIT;
  big_int accessible_cash_size_;
  big_int payment_on_this_month_;
  big_int debt_size_;
};

#endif
