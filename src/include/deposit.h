#ifndef MIPT_BANK__DEPOSIT_H_
#define MIPT_BANK__DEPOSIT_H_

#pragma once
#include <string>
#include "big_int.h"
#include "ibill.h"

const std::string BILL_KIND_DEPOSIT = "deposit";

class deposit : public ibill {
 public:

  std::string get_bill_kind() const override;

  big_int get_cash_size() const override;

  big_int get_income_potential_size() const;

  big_int get_bill_id() const override;

  big_int get_person_id() const override;

  void set_cash_size(const big_int& cash_size) override;

  void set_bill_id(const big_int& id) override;

  void set_income_potential_size(const big_int& income_potential_size);

  void set_person_id(const big_int& person_id) override;

  void update();

  void assign_id() override;

 private:
  std::string bill_kind_ = BILL_KIND_DEPOSIT;
  big_int income_potential_size_;

};

#endif