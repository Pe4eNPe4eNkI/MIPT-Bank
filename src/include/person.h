#ifndef MIPT_BANK__PERSON_H_
#define MIPT_BANK__PERSON_H_

#pragma once

#include <string>
#include <cstdint>
#include <vector>
#include "big_int.h"
#include "iperson.h"

class person : public iperson {
public:
  static big_int id;

  std::string get_first_name() const override;

  std::string get_second_name() const override;

  std::string get_address() const override;

  std::string get_passport_id() const override;

  big_int get_id() const override;


  void set_first_name(const std::string &first_name);

  void set_second_name(const std::string &second_name);

  void set_address(const std::string &address);

  void set_passport_id(const std::string &passprot_id);

  void assign_id();

  void update();
};

#endif //MIPT_BANK__PERSON_H_
