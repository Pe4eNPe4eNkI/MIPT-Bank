#ifndef MIPTBANK_PERSON_DIRECTOR_H
#define MIPTBANK_PERSON_DIRECTOR_H

#pragma once

#include "person_builder.h"

class person_director {
public:
  person *create_person(person_builder& pb);
};

#endif //MIPTBANK_PERSON_DIRECTOR_H
