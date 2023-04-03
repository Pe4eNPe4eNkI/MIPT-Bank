#include "big_int.h"

void big_int::shift_right() {
  if (digit_arr_.empty()) {
    digit_arr_.push_back(0);
    return;
  }
  digit_arr_.push_back(digit_arr_[digit_arr_.size() - 1]);
  for (size_t i = digit_arr_.size() - 2; i > 0; --i)
    digit_arr_[i] = digit_arr_[i - 1];
  digit_arr_[0] = 0;
}

big_int big_int::abs(const big_int &n) {
  big_int result = n;
  result.is_negative_ = false;
  return result;
}

void remove_start_zeroes(big_int &num) {
  while (num.digit_arr_.size() > 1 && num.digit_arr_.back() == 0) {
    num.digit_arr_.pop_back();
  }

  if (num.digit_arr_.size() == 1 && num.digit_arr_[0] == 0) num.is_negative_ = false;
}


big_int::big_int(int n) {
  digit_arr_.clear();
  if (n < 0) {
    is_negative_ = true;
    n = -n;
  } else {
    is_negative_ = false;
  }

  do {
    digit_arr_.push_back(static_cast<int>(n % base));
    n /= base;
  } while (n);
}

big_int::big_int(const std::string &s) {
  is_negative_ = false;
  if (s.empty()) {
    digit_arr_.clear();
    digit_arr_.emplace_back(0);
    return;
  }
  digit_arr_.clear();
  is_negative_ = s[0] == '-';
  for (int i = static_cast<int>(s.length()); i > is_negative_; i -= 9)
    if (i < 9)
      digit_arr_.push_back(std::stoi(s.substr(is_negative_, i - is_negative_)));
    else
      digit_arr_.push_back(std::stoi(s.substr(i - 9, 9)));
  remove_start_zeroes(*this);
}


big_int &big_int::operator=(const big_int &other) {
  digit_arr_.resize(other.digit_arr_.size());
  std::copy(other.digit_arr_.begin(), other.digit_arr_.end(), digit_arr_.begin());
  is_negative_ = other.is_negative_;
  return *this;
}

std::string big_int::toString() const {
  std::string result = is_negative_ ? "-" : "";

  result += std::to_string(digit_arr_.empty() ? 0 : digit_arr_.back());
  for (int i = static_cast<int>(digit_arr_.size()) - 2; i >= 0; --i) {
    std::string tmp = std::to_string(digit_arr_[i]);
    result += std::string(9 - tmp.size(), '0') + tmp;
  }
  return result;
}

std::ostream &operator<<(std::ostream &o, const big_int &num) {

  return o << num.toString();
}

std::istream &operator>>(std::istream &in, big_int &num) {
  std::string str;
  in >> str;
  num.digit_arr_.clear();
  if (str.length() == 0) {
    num.is_negative_ = false;
  } else {
    if (str[0] == '-') {
      str = str.substr(1);
      num.is_negative_ = true;
    } else {
      num.is_negative_ = false;
    }

    for (long long i = str.length(); i > 0; i -= 9) {
      if (i < 9)
        num.digit_arr_.push_back(atoi(str.substr(0, i).c_str()));
      else
        num.digit_arr_.push_back(atoi(str.substr(i - 9, 9).c_str()));
    }
    remove_start_zeroes(num);
  }
  return in;
}

bool big_int::operator<(const big_int &other) const {
  if ((*this).is_negative_ != other.is_negative_)
    return (*this).is_negative_;

  if ((*this).digit_arr_.size() != other.digit_arr_.size())
    return ((*this).digit_arr_.size() < other.digit_arr_.size()) ^ (*this).is_negative_;

  for (size_t i = (*this).digit_arr_.size(); i > 0; --i)
    if ((*this).digit_arr_[i - 1] != other.digit_arr_[i - 1])
      return ((*this).digit_arr_[i - 1] < other.digit_arr_[i - 1]) ^ (*this).is_negative_;

  return false;
}

bool big_int::operator>(const big_int &other) const {
  return other < *this;
}

bool big_int::operator!=(const big_int &other) const {
  return !(*this == other);
}

bool big_int::operator<=(const big_int &other) const {
  return !(*this > other);
}

bool big_int::operator>=(const big_int &other) const {
  return !(*this < other);
}

bool big_int::operator==(const big_int &other) const {
  return !(*this < other || *this > other);
}

big_int big_int::operator-() const {
  big_int copy(*this);
  if ((*this).digit_arr_.empty() || ((*this).digit_arr_.size() == 1 && (*this).digit_arr_[0] == 0)) {
    copy.is_negative_ = false;
    return copy;
  }
  copy.is_negative_ = !copy.is_negative_;
  return copy;
}


big_int operator+(const big_int &left, const big_int &right) {
  if (!left.is_negative_ && right.is_negative_) {
    return left - -right;
  }

  if (left.is_negative_ && !right.is_negative_) {
    return right - -left;
  }

  std::vector<int> answer_subseq(left.digit_arr_.size());
  std::copy(left.digit_arr_.begin(), left.digit_arr_.end(), answer_subseq.begin());

  int carry = 0;
  for (size_t i = 0; i < std::max(left.digit_arr_.size(), right.digit_arr_.size()) || carry; ++i) {
    if (i == answer_subseq.size()) answer_subseq.push_back(0);

    answer_subseq[i] += carry + (i < right.digit_arr_.size() ? right.digit_arr_[i] : 0);
    carry = answer_subseq[i] >= big_int::base;
    if (carry) answer_subseq[i] -= big_int::base;
  }

  big_int temp(answer_subseq, left.is_negative_);
  remove_start_zeroes(temp);
  return temp;
}

big_int operator-(const big_int &left, const big_int &right) {
  if (!left.is_negative_ && right.is_negative_) {
    return left + -right;
  }
  if (left.is_negative_ && !right.is_negative_) {
    big_int tmp = -left + right;
    tmp.is_negative_ = true;
    return tmp;
  }

  if (big_int::abs(left) < big_int::abs(right)) {
    big_int result = (big_int::abs(right) - big_int::abs(left));
    result.is_negative_ = !left.is_negative_;
    return result;
  }

  std::vector<int> answer_subseq(left.digit_arr_.size());
  std::copy(left.digit_arr_.begin(), left.digit_arr_.end(), answer_subseq.begin());

  int carry = 0;
  for (size_t i = 0; i < right.digit_arr_.size() || carry; ++i) {
    answer_subseq[i] -= carry + (i < right.digit_arr_.size() ? right.digit_arr_[i] : 0);
    carry = answer_subseq[i] < 0;
    if (carry) answer_subseq[i] += big_int::base;
  }

  big_int temp(answer_subseq, left.is_negative_);
  remove_start_zeroes(temp);
  return temp;
}

big_int operator*(const big_int &left, const big_int &right) {
  std::vector<int> answer_subseq(left.digit_arr_.size() + right.digit_arr_.size());
  for (size_t i = 0; i < left.digit_arr_.size(); ++i)
    for (int j = 0, carry = 0; j < static_cast<int>( right.digit_arr_.size()) || carry; ++j) {
      long long cur =
              answer_subseq[i + j] +
              left.digit_arr_[i] * 1ll * (j < static_cast<int>( right.digit_arr_.size()) ? right.digit_arr_[j] : 0) +
              carry;
      answer_subseq[i + j] = int(cur % big_int::base);
      carry = int(cur / big_int::base);
    }

  while (answer_subseq.size() > 1 && answer_subseq.back() == 0)
    answer_subseq.pop_back();
  big_int temp(answer_subseq,
                  !(answer_subseq.size() == 1 && answer_subseq[0] == 0) && (left.is_negative_ ^ right.is_negative_));
  remove_start_zeroes(temp);
  return temp;
}

big_int operator/(const big_int &left, const big_int &right) {
  if (right == 0) {
    throw std::string("Divide by 0");
  }

  if (big_int::abs(left) < big_int::abs(right)) {
    return 0;
  }

  big_int abs_other = big_int::abs(right);
  big_int div_result, remainder;
  div_result.digit_arr_.clear();
  remainder.digit_arr_.clear();
  div_result.digit_arr_.resize(left.digit_arr_.size());
  for (long long i = static_cast<long long>((left.digit_arr_.size())) - 1; i >= 0; --i) {
    remainder.shift_right();
    remainder.digit_arr_[0] = left.digit_arr_[i];
    remove_start_zeroes(remainder);
    int x = 0, l = 0, r = big_int::base;
    while (l <= r) {
      int m = (l + r) / 2;
      big_int t = abs_other * m;
      if (t <= remainder) {
        x = m;
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    div_result.digit_arr_[i] = x;
    remainder -= abs_other * x;

  }
  div_result.is_negative_ = left.is_negative_ ^ right.is_negative_;
  remove_start_zeroes(div_result);
  return div_result;
}


big_int operator%(const big_int &left, const big_int &right) {
  return left - (left / right) * right;
}

big_int &big_int::operator++() {
  return *this += 1;
}

big_int big_int::operator++(int) {
  *this += 1;
  return *this - 1;
}

big_int &big_int::operator--() {
  return *this -= 1;
}

big_int big_int::operator--(int) {
  *this -= 1;
  return *this + 1;
}

big_int &big_int::operator+=(const big_int &other) {
  return *this = *this + other;
}

big_int &big_int::operator-=(const big_int &other) {
  return *this = *this - other;
}

big_int &big_int::operator*=(const big_int &other) {
  return *this = *this * other;
}

big_int &big_int::operator/=(const big_int &other) {
  return *this = *this / other;
}

big_int &big_int::operator%=(const big_int &other) {
  return *this = *this % other;
}

big_int::operator bool() const {
  if (this->digit_arr_.empty() || (this->digit_arr_.size() == 1 && this->digit_arr_[0] == 0)) {
    return false;
  } else {
    return true;
  }
}
