#include <iostream>
#include <string>
#include <cstdint>
#include <vector>
#include "big_int.h"

static const uint64_t money_limit = 1e4;  // if is_doubtful = false 0(no limits)

class person {
public:

  std::string get_first_name() const;

  std::string get_second_name() const;

  std::string get_address() const;

  std::string get_passport_id() const;


  void set_first_name(const std::string &first_name);

  void set_second_name(const std::string &second_name);

  void set_address(const std::string &address);

  void set_passport_id(const std::string &passprot_id);

  void update();

private:
  std::string first_name_;
  std::string second_name_;
  std::string address_;
  std::string passport_id_;
  uint64_t money_limit_ = 0;
  std::vector<big_int> bills_id_;
  bool is_doubtful_;
};


std::string person::get_first_name() const {
  return first_name_;
}

std::string person::get_second_name() const {
  return second_name_;
}

std::string person::get_address() const {
  return address_;
}

std::string person::get_passport_id() const {
  return passport_id_;
}

void person::set_first_name(const std::string &first_name) {
  first_name_ = first_name;
}

void person::set_second_name(const std::string &second_name) {
  second_name_ = second_name;
}

void person::set_address(const std::string &address) {
  address_ = address;
}

void person::set_passport_id(const std::string &passport_id) {
  passport_id_ = passport_id;
}

void person::update() {
  is_doubtful_ = address_.empty() || passport_id_.empty();
  if (is_doubtful_) money_limit_ = money_limit;
}


class person_builder {
public:
  friend person;

  person_builder *set_first_name(const std::string &first_name);

  person_builder *set_second_name(const std::string &second_name);

  person_builder *set_address(const std::string &address);

  person_builder *set_passport_id(const std::string &passprot_id);

  void set_person_first_name();

  void set_person_second_name();

  void set_person_passport_id();

  void set_person_address();

  person *get_person() const { return pers; }

  void create_person();

  bool check_args() const;

  std::string first_name_;

  person *pers;

private:

  std::string second_name_;
  std::string address_;
  std::string passport_id_;
};


person_builder *person_builder::set_first_name(const std::string &first_name) {
  first_name_ = first_name;
  return this;
}

person_builder *person_builder::set_second_name(const std::string &second_name) {
  second_name_ = second_name;
  return this;
}

person_builder *person_builder::set_address(const std::string &address) {
  address_ = address;
  return this;
}

person_builder *person_builder::set_passport_id(const std::string &passport_id) {
  passport_id_ = passport_id;
  return this;
}

bool person_builder::check_args() const {
  return first_name_.empty() && second_name_.empty();
}

void person_builder::create_person() {
  pers = new class person;
}

void person_builder::set_person_first_name() {
  pers->set_first_name(first_name_);
}

void person_builder::set_person_second_name() {
  pers->set_second_name(second_name_);
}

void person_builder::set_person_address() {
  pers->set_address(address_);
}

void person_builder::set_person_passport_id() {
  pers->set_passport_id(passport_id_);
}

class person_director {
public:
  person *create_person(person_builder& pb);
};

person *person_director::create_person(person_builder &pb) {
  pb.create_person();

  pb.set_person_first_name();
  pb.set_person_second_name();
  pb.set_person_address();
  pb.set_person_passport_id();
  pb.pers->update();

  return pb.pers;
}

int main() {

  person_director dire;
  person_builder pb;
  pb.set_first_name("a")->set_second_name("b")->set_passport_id("c")->set_address("d");
  person *person = dire.create_person(pb);


  std::cout << person->get_first_name() << std::endl;
  std::cout << person->get_second_name() << std::endl;
  std::cout << person->get_address() << std::endl;
  std::cout << person->get_passport_id() << std::endl;
  

  return 0;
}
