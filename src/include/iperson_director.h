#ifndef MIPTBANK_IPERSON_DIRECTOR_H
#define MIPTBANK_IPERSON_DIRECTOR_H

#pragma once
#include "iperson_builder.h"

class iperson_director{
public:
  virtual iperson* create_person(iperson_builder& pb) = 0;
};

#endif //MIPTBANK_IPERSON_DIRECTOR_H
