package com.example.mipt_bank_app.big_int;

import java.util.ArrayList;

public class big_int {

    public big_int(int n) {
        digits_arr_.clear();
        if (n < 0) {
            is_negative_ = true;
            n = -n;
        } else {
            is_negative_ = false;
        }

        do {
            digits_arr_.add(n % k_base);
            n /= k_base;
        } while (n != 0);
    }

    public big_int(ArrayList<Integer> al, boolean is_negative) {
        is_negative_ = is_negative;
        for (int i = 0; i < al.size(); ++i) {
            digits_arr_.add(al.get(i));
        }
    }

    public big_int(String s) {
        is_negative_ = false;
        if (s.isEmpty()) {
            digits_arr_.clear();
            digits_arr_.add(0);
            return;
        }
        digits_arr_.clear();
        is_negative_ = s.substring(0, 1).equals("-");
        for (int i = s.length(); i > (is_negative_ ? 1 : 0); i -= 9) {
            if (i < 9) {
                digits_arr_.add(Integer.parseInt(s.substring((is_negative_ ? 1 : 0), i)));
            } else {
                digits_arr_.add(Integer.parseInt(s.substring(i - 9, i)));
            }
        }
        remove_start_zeroes(this);
    }

    public void set_big_int(big_int other) {  // equal opertaor =
        digits_arr_.clear();
        for (int i = 0; i < other.digits_arr_.size(); ++i) {
            digits_arr_.add(0);
        }
        for (int i = 0; i < other.digits_arr_.size(); ++i) {
            digits_arr_.set(i, other.digits_arr_.get(i));
        }
        is_negative_ = other.is_negative_;
    }

    public String toString() {
        String result = is_negative_ ? "-" : "";
        Integer a = (digits_arr_.isEmpty() ? 0 : digits_arr_.get(digits_arr_.size() - 1));
        result += a.toString();
        for (int i = digits_arr_.size() - 2; i >= 0; --i) {
            String temp = digits_arr_.get(i).toString();
            big_int_helper h = new big_int_helper();
            result += h.cpp_string_con(9 - temp.length(), '0') + temp;
        }
        return result;
    }

    public boolean operator_less(big_int other) {  // equal operator <
        if (this.is_negative_ != other.is_negative_)
            return this.is_negative_;
        if (this.digits_arr_.size() != other.digits_arr_.size())
            return (this.digits_arr_.size() < other.digits_arr_.size()) ^ this.is_negative_;
        for (int i = this.digits_arr_.size(); i > 0; ++i) {
            if (this.digits_arr_.get(i - 1) != other.digits_arr_.get(i - 1)) {
                return (this.digits_arr_.get(i - 1) < other.digits_arr_.get(i - 1)) ^ this.is_negative_;
            }
        }
        return false;
    }

    public boolean operator_more(big_int other) {  // equal operator >
        return other.operator_less(this);
    }

    public boolean operator_more_or_equal(big_int other) {  // equal operator >=
        return !(this.operator_less(other));
    }

    public boolean operator_less_or_equal(big_int other) {  // equal operator <=
        return !(this.operator_more(other));
    }

    public boolean operator_equal(big_int other) {  // equal operator ==
        return !(this.operator_less(other) || this.operator_more(other));
    }

    public boolean operator_not_equal(big_int other) {  // equal operator !=
        return !(this.operator_equal(other));
    }

    public big_int operator_un_minus() {  // we have big_int a = "500"  operator_un_minus gives  "-500"
        big_int copy = this;
        if (this.digits_arr_.isEmpty() || (this.digits_arr_.size() == 1 && this.digits_arr_.get(0) == 0)) {
            copy.is_negative_ = false;
            return copy;
        }
        copy.is_negative_ = !copy.is_negative_;
        return copy;
    }

    public big_int operator_plus(big_int left, big_int right) {  //returns left+right copy!     != a+=b
        if (!left.is_negative_ && right.is_negative_) {
            return left.operator_minus(left, right.operator_un_minus());
        }
        if (left.is_negative_ && !right.is_negative_) {
            return right.operator_minus(right, left.operator_un_minus());
        }
        ArrayList<Integer> answer_subseq = new ArrayList<Integer>(left.digits_arr_.size());
        for (int i = 0; i < left.digits_arr_.size(); ++i) {
            answer_subseq.add(left.digits_arr_.get(i));
        }
        int carry = 0;
        for (int i = 0; i < Math.max(Math.max(left.digits_arr_.size(), right.digits_arr_.size()), carry); ++i) {
            if (i == answer_subseq.size()) answer_subseq.add(0);
            answer_subseq.set(i, answer_subseq.get(i) + carry + (i < right.digits_arr_.size() ? right.digits_arr_.get(i) : 0));
            if (answer_subseq.get(i) >= k_base) {
                carry = 1;
                answer_subseq.set(i, answer_subseq.get(i) - k_base);
            }
        }
        big_int temp = new big_int(answer_subseq, left.is_negative_);
        remove_start_zeroes(temp);
        return temp;
    }

    public big_int operator_minus(big_int left, big_int right) {  //returns left - right copy!    != a-=b
        if (!left.is_negative_ && right.is_negative_) {
            return left.operator_plus(left, right.operator_un_minus());
        }
        if (left.is_negative_ && !right.is_negative_) {
            big_int temp = left.operator_plus(left.operator_un_minus(), right);
            temp.is_negative_ = true;
            return temp;
        }
        if (abs(left).operator_less(abs(right))) {
            big_int result = abs(right).operator_minus(right, abs(left));
            result.is_negative_ = !left.is_negative_;
            return result;
        }

        ArrayList<Integer> answer_subseq = new ArrayList<Integer>(left.digits_arr_.size());
        for (int i = 0; i < left.digits_arr_.size(); ++i) {
            answer_subseq.add(left.digits_arr_.get(i));
        }
        int carry = 0;
        for (int i = 0; i < Math.max(Math.max(left.digits_arr_.size(), right.digits_arr_.size()), carry); ++i) {
            if (i == answer_subseq.size()) answer_subseq.add(0);
            answer_subseq.set(i, answer_subseq.get(i) - (carry + (i < right.digits_arr_.size() ? right.digits_arr_.get(i) : 0)));
            if (answer_subseq.get(i) < 0) {
                carry = 1;
                answer_subseq.set(i, answer_subseq.get(i) + k_base);
            }
        }

        big_int temp = new big_int(answer_subseq, left.is_negative_);
        remove_start_zeroes(temp);
        return temp;
    }

    public big_int operator_multiply(big_int left, big_int right) {  //returns left*right copy!    != a/=b
        ArrayList<Integer> answer_subseq = new ArrayList<Integer>(left.digits_arr_.size() + right.digits_arr_.size());
        for (int i = 0; i < left.digits_arr_.size() + right.digits_arr_.size(); ++i) {
            answer_subseq.add(0);
        }
        for (int i = 0; i < left.digits_arr_.size(); ++i) {
            for (int j = 0, carry = 0; j < Math.max(right.digits_arr_.size(), carry); ++j) {
                int cur = answer_subseq.get(i + j) + left.digits_arr_.get(i) + (j < right.digits_arr_.size() ? right.digits_arr_.get(j) : 0) + carry;
                answer_subseq.set(i + j, cur % k_base);
            }
        }
        while (answer_subseq.size() > 1 && answer_subseq.get(answer_subseq.size() - 1) == 0) {
            answer_subseq.remove(answer_subseq.size() - 1);
        }
        big_int temp = new big_int(answer_subseq, !(answer_subseq.size() == 1 && answer_subseq.get(0) == 0) && (left.is_negative_ ^ right.is_negative_));

        remove_start_zeroes(temp);
        return temp;
    }

    /*public big_int operator_devide(big_int left, big_int right) {  //returns copy!    != a*=b
        return left;  // kostil
    }*/

    /*public big_int operator_devide_by_remainder(big_int left, big_int right) {  //returns copy!    != a/=b
        return left.operator_minus(left, left.operator_devide(left, right).operator_multiply(left.operator_devide(left, right), right));
    }*/

    public big_int operator_pp_prefix() {  //equal ++a
        big_int a = new big_int(1);
        return this.operator_plus_equal(a);
    }

    public big_int operator_mm_prefix() {  //equal --a
        big_int a = new big_int(1);
        return this.operator_minus_equal(a);
    }

    public big_int operator_pp_postfix() {  //equal a++
        big_int a = new big_int(1);
        this.operator_plus_equal(a);
        return this.operator_minus(this, a);
    }

    public big_int operator_mm_postfix() {  //equal a--
        big_int a = new big_int(1);
        this.operator_minus_equal(a);
        return this.operator_plus(this, a);
    }

    public big_int operator_plus_equal(big_int num) {  //equal a+=b
        this.set_big_int(this.operator_plus(this, num));
        return this;
    }

    public big_int operator_minus_equal(big_int num) {  //equal a-=b
        this.set_big_int(this.operator_minus(this, num));
        return this;
    }

    public big_int operator_multiply_equal(big_int num) {  //equal a*=b
        this.set_big_int(this.operator_multiply(this, num));
        return this;
    }

    /*public big_int operator_devide_equal(big_int num) {
        this.operator_equal(this.operator_devide(this, num));
        return this;
    }*/

    /*public big_int operator_devide_by_reminder_equal(big_int num) {
        this.operator_equal(this.operator_devide_by_remainder(this, num));
        return this;
    }*/


    public void shift_right() {
        if (digits_arr_.isEmpty()) {
            digits_arr_.add(0);
            return;
        }
        digits_arr_.add(digits_arr_.get(digits_arr_.size() - 1));
        for (int i = digits_arr_.size() - 2; i > 0; --i) {
            digits_arr_.set(i, digits_arr_.get(i - 1));
        }
        digits_arr_.set(0, 0);
    }

    public big_int abs(big_int bi) {  //equal module
        big_int result = bi;
        result.is_negative_ = false;
        return result;
    }

    public void remove_start_zeroes(big_int num) {
        while (num.digits_arr_.size() > 1 && num.digits_arr_.get(digits_arr_.size() - 1) == 0) {
            num.digits_arr_.remove(digits_arr_.size() - 1);
        }
        if (num.digits_arr_.size() == 1 && num.digits_arr_.get(0) == 0) num.is_negative_ = false;
    }

    private final ArrayList<Integer> digits_arr_ = new ArrayList<Integer>();
    private boolean is_negative_;

    static int k_base = 1000000000;

}
