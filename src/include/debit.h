//
// Created by Arsentiy on 29.03.2023.
//

#ifndef MIPT_BANK__DEBIT_H_
#define MIPT_BANK__DEBIT_H_

#pragma once
#include <string>
#include "big_int.h"

class debit {
public:

    big_int get_cash_size() const;

    big_int get_cashback_potential_size() const;

    int get_bill_kind() const;

    big_int get_unique_id() const;

    big_int get_person_id() const;


    void set_cash_size(const big_int &cash_size);

    void set_cashback_potential_size(const big_int &cashback_potential_size);

    void set_bill_kind(const int &bill_kind);

    void set_unique_id(const big_int &unique_id);

    void set_person_id(const big_int &person_id);

    void update();

private:
    big_int cash_size_;
    big_int cashback_potential_size_;
    big_int unique_id_;
    big_int person_id_;
    int bill_kind_;
};

#endif //MIPT_BANK__DEBIT_H_
