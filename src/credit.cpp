//
// Created by Arsentiy on 29.03.2023.
//

#include "credit.h"

big_int credit::get_accessible_cash_size() const {
    return accessible_cash_size_;
}

big_int credit::get_cash_size() const {
    return cash_size_;
}

big_int credit::get_debt_size() const {
    return debt_size_;
}

big_int credit::get_payment_on_this_month() const {
    return payment_on_this_month_;
}

int credit::get_bill_kind() const {
    return bill_kind_;
}

big_int credit::get_unique_id() const {
    return unique_id_;
}

big_int credit::get_person_id() const {
    return person_id_;
}

void credit::set_cash_size(const big_int &cash_size) {
    cash_size_ = cash_size;
}

void credit::set_accessible_cash_size(const big_int &accessible_cash_size) {
    accessible_cash_size_ = accessible_cash_size;
}

void credit::set_debt_size(const big_int &debt_size) {
    debt_size_ = debt_size;
}

void credit::set_payment_on_this_month(const big_int &payment_on_this_month) {
    payment_on_this_month_ = payment_on_this_month;
}

void credit::set_bill_kind(const int &bill_kind) {
    bill_kind_ = bill_kind;
}

void credit::set_unique_id(const big_int &unique_id) {
    unique_id_ = unique_id;
}

void credit::set_person_id(const big_int &person_id) {
    person_id_ = person_id;
}

void credit::update() {
    debt_size_ = cash_size_ - accessible_cash_size_;
    payment_on_this_month_ = debt_size_ / 10;
}