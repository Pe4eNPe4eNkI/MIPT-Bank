//
// Created by Arsentiy on 29.03.2023.
//

#ifndef MIPT_BANK__CREDIT_H_
#define MIPT_BANK__CREDIT_H_

#pragma once
#include <string>
#include "big_int.h"

class credit {
public:

    big_int get_cash_size() const;

    big_int get_accessible_cash_size() const;

    big_int get_debt_size() const;

    big_int get_payment_on_this_month() const;

    int get_bill_kind() const;

    big_int get_unique_id() const;

    big_int get_person_id() const;


    void set_cash_size(const big_int &cash_size);

    void set_accessible_cash_size(const big_int &accessible_cash_size);

    void set_debt_size(const big_int &debt_size);

    void set_payment_on_this_month(const big_int &payment_on_this_month);

    void set_bill_kind(const int &bill_kind);

    void set_unique_id(const big_int &unique_id);

    void set_person_id(const big_int &person_id);

    void update();

private:
    big_int cash_size_;
    big_int accessible_cash_size_;
    big_int payment_on_this_month_;
    big_int debt_size_;
    big_int unique_id_;
    big_int person_id_;
    int bill_kind_;

};

#endif //MIPT_BANK__CREDIT_H_
