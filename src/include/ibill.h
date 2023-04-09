#ifndef MIPTBANK_MIPT_BANK_SRC_INCLUDE_IBILL_H_
#define MIPTBANK_MIPT_BANK_SRC_INCLUDE_IBILL_H_

#pragma once

#include <string>
#include <vector>
#include "big_int.h"

class ibill {
 public:

  virtual std::string get_bill_kind() const = 0;

  virtual big_int get_person_id() const = 0;

  virtual big_int get_bill_id() const = 0;

  virtual big_int get_cash_size() const = 0;

  virtual void set_person_id(const big_int& id) = 0;

  virtual void set_cash_size(const big_int& money_size) = 0;

  virtual void assign_id() = 0;

 protected:
  big_int bill_id_;
  big_int person_id_;
  big_int cash_size_;
};

#endif