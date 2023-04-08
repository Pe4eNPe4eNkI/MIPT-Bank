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

  deposit build_deposit(const int& bill_kind, const big_int& person_id) const;

  credit build_credit(const int& bill_kind, const big_int& person_id) const;

  debit build_debit(const int& bill_kind, const big_int& person_id) const;

};

#endif //MIPTBANK_BILL_FACTORY_H