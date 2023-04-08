//
// Created by Arsentiy on 29.03.2023.
//

#include "debit.h"

big_int debit::get_cash_size() const {
    return cash_size_;
}

big_int debit::get_cashback_potential_size() const {
    return cashback_potential_size_;
}

int debit::get_bill_kind() const {
    return bill_kind_;
}

big_int debit::get_unique_id() const {
    return unique_id_;
}

big_int debit::get_person_id() const {
    return person_id_;
}

void debit::set_cash_size(const big_int &cash_size) {
    cash_size_ = cash_size;
}

void debit::set_cashback_potential_size(const big_int &cashback_potential_size) {
    cashback_potential_size_ = cashback_potential_size;
}

void debit::set_bill_kind(const int &bill_kind) {
    bill_kind_ = bill_kind;
}

void debit::set_unique_id(const big_int &unique_id) {
    unique_id_ = unique_id;
}

void debit::set_person_id(const big_int &person_id) {
    person_id_ = person_id;
}

void debit::update() {
    cashback_potential_size_ = cash_size_ / 100 * 5;
}