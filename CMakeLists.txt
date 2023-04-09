cmake_minimum_required(VERSION 3.10)
project(miptbank)

set(CMAKE_CXX_STANDARD 20)

add_executable(miptbank main.cpp
        src/include/sqlite-amalgamation-3410200/sqlite3.c
        src/include/sqlite-amalgamation-3410200/sqlite3.h
        src/include/sqlite-amalgamation-3410200/sqlite3ext.h
        src/person.cpp
        src/person_builder.cpp
        src/person_director.cpp
        src/big_int.cpp
        src/person_db.cpp
        src/bill_factory.cpp

        src/include/iperson_builder.h
        src/include/iperson.h
        src/include/iperson_director.h
        src/include/person_db.h
        src/include/bill_factory.h)
