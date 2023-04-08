#ifndef MIPT_BANK__BILL_H_
#define MIPT_BANK__BILL_H_

#pragma once

#include <string>
#include <vector>
#include "big_int.h"
#include "ibill.h"

class bill : public ibill {
 public:

  static big_int bill_id;

  std::string get_bill_kind() const override;

  big_int get_person_id() const override;

  big_int get_bill_id() const override;

  void set_person_id(const iperson person);

  void set_bill_kind(const std::string& bill_kind);

  void assign_bill_id();
};

#endif