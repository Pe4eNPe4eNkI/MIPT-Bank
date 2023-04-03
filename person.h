#ifndef MIPT_BANK__PERSON_H_
#define MIPT_BANK__PERSON_H_

#pragma once

#include <string>
#include <cstdint>
#include <vector>
#include "big_int.h"

static const uint64_t money_limit = 1e4;  // if is_doubtful = false 0(no limits)

class person {
public:

  static big_int id;

  std::string get_first_name() const;

  std::string get_second_name() const;

  std::string get_address() const;

  std::string get_passport_id() const;

  big_int get_id() const;


  void set_first_name(const std::string &first_name);

  void set_second_name(const std::string &second_name);

  void set_address(const std::string &address);

  void set_passport_id(const std::string &passprot_id);

  void assign_id();

  void update();

private:
  std::string first_name_;
  std::string second_name_;
  std::string address_;
  std::string passport_id_;
  uint64_t money_limit_ = money_limit;
  std::vector<big_int> bills_id_;
  bool is_doubtful_ = true;
  big_int id_;
};

#endif //MIPT_BANK__PERSON_H_
