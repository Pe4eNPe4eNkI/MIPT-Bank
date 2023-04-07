#ifndef DATABASEPROJECT_IPERSON_BUILDER_H
#define DATABASEPROJECT_IPERSON_BUILDER_H

#pragma once

#include <string>
#include "iperson.h"

class iperson_builder {

  virtual iperson_builder *set_first_name(const std::string &first_name) = 0;

  virtual iperson_builder *set_second_name(const std::string &second_name) = 0;

  virtual iperson_builder *set_address(const std::string &address) = 0;

  virtual iperson_builder *set_passport_id(const std::string &passprot_id) = 0;

  virtual void set_person_first_name() = 0;

  virtual void set_person_second_name() = 0;

  virtual void set_person_passport_id() = 0;

  virtual void set_person_address() = 0;

  virtual bool check_args() const = 0;

  virtual iperson *get_person() const = 0;

  virtual void create_person() = 0;

protected:
  std::string first_name_;
  std::string second_name_;
  std::string address_;
  std::string passport_id_;
};


#endif //DATABASEPROJECT_IPERSON_BUILDER_H
