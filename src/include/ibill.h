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

  virtual void set_person_id(const iperson person) = 0;

  virtual void set_bill_kind(const std::string& bill_kind) = 0;

  virtual void assign_bill_id() = 0;

 protected:
  std::string bill_kind_;
  big_int bill_id_;
  big_int person_id_;
};

#endif //MIPTBANK_MIPT_BANK_SRC_INCLUDE_IBILL_H_
