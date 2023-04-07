#ifndef MIPT_BANK__PERSON_BUILDER_H_
#define MIPT_BANK__PERSON_BUILDER_H_

#pragma once

#include <string>
#include <cstdint>
#include <vector>
#include "person.h"
#include "iperson_builder.h"

class person_builder : public iperson_builder {
public:
  friend person;

  person_builder *set_first_name(const std::string &first_name) override;

  person_builder *set_second_name(const std::string &second_name) override;

  person_builder *set_address(const std::string &address) override;

  person_builder *set_passport_id(const std::string &passprot_id) override;

  bool check_args() const override;

  void set_person_first_name() override;

  void set_person_second_name() override;

  void set_person_passport_id() override;

  void set_person_address() override;

  person *get_person() const override { return pers; }

  void create_person() override;

private:
  person* pers;
};

#endif //MIPT_BANK__PERSON_BUILDER_H_
