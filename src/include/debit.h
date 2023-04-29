#ifndef MIPT_BANK__DEBIT_H_
#define MIPT_BANK__DEBIT_H_

#pragma once
#include <string>
#include "big_int.h"
#include "ibill.h"

const std::string BILL_KIND_DEBIT = "debit";

class debit : public ibill {
 public:

  std::string get_bill_kind() const override;

  big_int get_cash_size() const override;

  big_int get_cashback_potential_size() const;

  big_int get_bill_id() const override;

  big_int get_person_id() const override;

  void set_cash_size(const big_int& cash_size) override;

  void set_bill_id(const big_int& id) override;

  void set_cashback_potential_size(const big_int& cashback_potential_size);

  void set_person_id(const big_int& person_id) override;

  void update();

  void assign_id() override;

 private:
  std::string bill_kind_ = BILL_KIND_DEBIT;
  big_int cashback_potential_size_;
};

#endif