#include "bill.h"

std::string bill::get_bill_kind() const {
  return bill_kind_;
}

int bill::get_bill_id() const {
  return bill_id_;
}

int bill::get_person_id() const {
  return person_id_;
}