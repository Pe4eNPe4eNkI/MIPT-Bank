cmake_minimum_required(VERSION 3.16)
project(mipt_bank)

set(CMAKE_CXX_STANDARD 20)

add_executable(mipt_bank main.cpp persok.cpp persok.h person_builder.cpp person_builder.h impney_operation.cpp impney_operation.h refil.cpp refil.h withdrawal.cpp withdrawal.h transfer.cpp transfer.h ibill.cpp ibill.h deposit.cpp deposit.h credit.cpp credit.h debit.cpp debit.h)