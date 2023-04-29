#ifndef MIPTBANK_BIG_INT_H
#define MIPTBANK_BIG_INT_H


#pragma once

#include <iostream>
#include <vector>
#include <string>


class big_int {
public:

  big_int() : big_int(0) {};

  big_int(int n);

  big_int(std::vector<int> &n, const bool &negative) : digit_arr_(std::move(n)), is_negative_(negative) {};

  big_int(const std::string &s);

  std::string toString() const;

  bool operator<(const big_int &other) const;

  bool operator>(const big_int &other) const;

  bool operator==(const big_int &other) const;

  bool operator<=(const big_int &other) const;

  bool operator>=(const big_int &other) const;

  bool operator!=(const big_int &other) const;


  friend std::istream &operator>>(std::istream &istream, big_int &number);

  friend std::ostream &operator<<(std::ostream &ostream, const big_int &number);

  big_int &operator=(const big_int &other);

  friend big_int operator+(const big_int &left, const big_int &right);

  friend big_int operator-(const big_int &left, const big_int &right);

  friend big_int operator*(const big_int &left, const big_int &right);

  friend big_int operator/(const big_int &left, const big_int &right);

  friend big_int operator%(const big_int &left, const big_int &right);

  big_int operator-() const;

  big_int &operator++();

  big_int operator++(int);

  big_int &operator--();

  big_int operator--(int);

  big_int &operator+=(const big_int &other);

  big_int &operator-=(const big_int &other);

  big_int &operator*=(const big_int &other);

  big_int &operator/=(const big_int &other);

  big_int &operator%=(const big_int &other);

  static big_int abs(const big_int &n);

  void shift_right();

  explicit operator bool() const;

  static const int k_base = 1000000000;

private:
  std::vector<int> digit_arr_;
  bool is_negative_;

  friend void remove_start_zeroes(big_int &num);
};

#endif //MIPTBANK_BIG_INT_H
