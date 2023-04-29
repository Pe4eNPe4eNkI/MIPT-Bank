#ifndef MIPTBANK_IPERSON_H
#define MIPTBANK_IPERSON_H
#pragma once

#include <string>
#include <vector>
#include "big_int.h"

static const int money_limit = 1e4;  // if is_doubtful = false 0(no limits)

class iperson {
public:
  virtual std::string get_first_name() const = 0;

  virtual std::string get_second_name() const = 0;

  virtual std::string get_address() const = 0;

  virtual std::string get_passport_id() const = 0;

  virtual int get_money_limit() const = 0;

  virtual bool is_doubtful() const = 0;

  virtual big_int get_id() const = 0;

  virtual void set_first_name(const std::string &first_name) = 0;

  virtual void set_second_name(const std::string &second_name) = 0;

  virtual void set_address(const std::string &address) = 0;

  virtual void set_passport_id(const std::string &passprot_id) = 0;

  virtual void assign_id() = 0;

  virtual void update() = 0;

protected:
  std::string first_name_;
  std::string second_name_;
  std::string address_;
  std::string passport_id_;
  int money_limit_ = money_limit;
  bool is_doubtful_ = true;
  big_int id_;
};

#endif //MIPTBANK_IPERSON_H
