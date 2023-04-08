//
// Created by belka on 30.03.2023.
//

#include "bill_factory.h"

/*bill_factory::bill_factory() {
}*/

deposit bill_factory::build_deposit(const int &bill_kind, const big_int &unique_id, const big_int &person_id) const {
    deposit deposit_ = deposit();
    deposit_.set_bill_kind(bill_kind);
    deposit_.set_person_id(person_id);
    deposit_.set_unique_id(unique_id);
    deposit_.set_cash_size(0);
    deposit_.set_income_potential_size(deposit_.get_cash_size() / 100 * 10);
    return deposit_;
}

credit bill_factory::build_credit(const int &bill_kind, const big_int &unique_id, const big_int &person_id) const {
    credit credit_ = credit();
    credit_.set_bill_kind(bill_kind);
    credit_.set_person_id(person_id);
    credit_.set_unique_id(unique_id);
    credit_.set_cash_size(100000000);
    credit_.set_accessible_cash_size(100000000);
    credit_.set_debt_size(0);
    credit_.set_payment_on_this_month(0);
    return credit_;
}

debit bill_factory::build_debit(const int &bill_kind, const big_int &unique_id, const big_int &person_id) const {
    debit debit_ = debit();
    debit_.set_bill_kind(bill_kind);
    debit_.set_person_id(person_id);
    debit_.set_unique_id(unique_id);
    debit_.set_cash_size(0);
    debit_.set_cashback_potential_size(debit_.get_cash_size() / 100 * 5);
    return debit_;
}
