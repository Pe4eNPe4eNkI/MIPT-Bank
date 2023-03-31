//
// Created by Arsentiy on 29.03.2023.
//

#include "deposit.h"

big_int deposit::get_cash_size() const {
    return cash_size_;
}

big_int deposit::get_income_potential_size() const {
    return income_potential_size_;
}

int deposit::get_bill_kind() const {
    return bill_kind_;
}

big_int deposit::get_unique_id() const {
    return unique_id_;
}

big_int deposit::get_person_id() const {
    return person_id_;
}

void deposit::set_cash_size(const big_int &cash_size) {
    cash_size_ = cash_size;
}

void deposit::set_income_potential_size(const big_int &income_potential_size) {
    income_potential_size_ = income_potential_size;
}

void deposit::set_bill_kind(const int &bill_kind) {
    bill_kind_ = bill_kind;
}

void deposit::set_unique_id(const big_int &unique_id) {
    unique_id_ = unique_id;
}

void deposit::set_person_id(const big_int &person_id) {
    person_id_ = person_id;
}

void deposit::update() {
    income_potential_size_ = cash_size_ / 100 * 10;
}