//
// Created by belka on 30.03.2023.
//

#include "bill_factory.h"

bill_factory::bill_factory(const int &bill_kind, const big_int &unique_id, const big_int &person_id) {
    bill_kind_ = bill_kind;
    unique_id_ = unique_id;
    person_id_ = person_id;
}

deposit bill_factory::build_deposit() const {
    deposit deposit_ = deposit();
    deposit_.set_bill_kind(bill_kind_);
    deposit_.set_person_id(person_id_);
    deposit_.set_unique_id(unique_id_);
    deposit_.set_cash_size(0);
    deposit_.set_income_potential_size(deposit_.get_cash_size() / 100 * 10);
    return deposit_;
}

credit bill_factory::build_credit() const {
    credit credit_ = credit();
    credit_.set_bill_kind(bill_kind_);
    credit_.set_person_id(person_id_);
    credit_.set_unique_id(unique_id_);
    credit_.set_cash_size(100000000);
    credit_.set_accessible_cash_size(100000000);
    credit_.set_debt_size(0);
    credit_.set_payment_on_this_month(0);
    return credit_;
}

debit bill_factory::build_debit() const {
    debit debit_ = debit();
    debit_.set_bill_kind(bill_kind_);
    debit_.set_person_id(person_id_);
    debit_.set_unique_id(unique_id_);
    debit_.set_cash_size(0);
    debit_.set_cashback_potential_size(debit_.get_cash_size() / 100 * 5);
    return debit_;
}
