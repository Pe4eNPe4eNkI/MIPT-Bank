cmake_minimum_required(VERSION 3.10)

project(lib)

set(SOURCE_LIB credit.cpp debit.cpp
        deposit.cpp ibill.cpp
        imoney_operation.cpp person.cpp
        person_builder.cpp refil.cpp
        transfer.cpp withdrawal.cpp big_int.cpp person_director.cpp)

add_library(lib SHARED ${SOURCE_LIB})

target_include_directories(lib PRIVATE ${miptbank_SOURCE_DIR}/include)

file(GLOB_RECURSE LIBRARY_HEADERS "${miptbank_SOURCE_DIR}/include/*.h")

set_target_properties(lib PROPERTIES PUBLIC_HEADER "${LIBRARY_HEADERS}")