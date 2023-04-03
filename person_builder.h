#ifndef MIPT_BANK__PERSON_BUILDER_H_
#define MIPT_BANK__PERSON_BUILDER_H_

#pragma once

#include <string>
#include <cstdint>
#include <vector>
#include "person.h"

class person_builder {
public:
  friend person;

  person_builder *set_first_name(const std::string &first_name);

  person_builder *set_second_name(const std::string &second_name);

  person_builder *set_address(const std::string &address);

  person_builder *set_passport_id(const std::string &passprot_id);

  person *get_person() const { return pers; }

  void create_person();

  bool check_args() const;

  void set_person_first_name();

  void set_person_second_name();

  void set_person_passport_id();

  void set_person_address();

private:
  std::string first_name_;
  std::string second_name_;
  std::string address_;
  std::string passport_id_;

  person *pers;
};

#endif //MIPT_BANK__PERSON_BUILDER_H_
