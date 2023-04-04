//
// Created by belka on 30.03.2023.
//

#ifndef MIPTBANK_BILL_FACTORY_H
#define MIPTBANK_BILL_FACTORY_H

#pragma once
#include "bill_factory.h"
#include "big_int.h"
#include "deposit.h"
#include "debit.h"
#include "credit.h"

class bill_factory {
public:
    bill_factory(const int &bill_kind, const big_int &unique_id, const big_int &person_id);

    deposit build_deposit() const;

    credit build_credit() const;

    debit build_debit() const;

private:
    int bill_kind_;
    big_int unique_id_;
    big_int person_id_;
};

#endif //MIPTBANK_BILL_FACTORY_H
