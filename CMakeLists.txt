cmake_minimum_required(VERSION 3.10)
project(miptbank)

set(CMAKE_CXX_STANDARD 20)

add_executable(miptbank main.cpp sqlite-amalgamation-3410200/sqlite3.c sqlite-amalgamation-3410200/sqlite3.h sqlite-amalgamation-3410200/sqlite3ext.h
        src/person.cpp
        src/person_builder.cpp
        src/person_director.cpp
        src/big_int.cpp
        src/include/iperson_builder.h
        src/include/iperson.h
        src/include/iperson_director.h)
