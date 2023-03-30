cmake_minimum_required(VERSION 3.10)

project(miptbank)

add_executable(miptbank main.cpp src/person_director.cpp)

target_include_directories(miptbank PUBLIC include)

add_subdirectory(src)

target_link_libraries(miptbank lib)
