package com.example.mipt_bank_app.BigInt;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BigInt {

    public BigInt(int n) {
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

    public BigInt(ArrayList<Integer> al, boolean is_negative) {
        is_negative_ = is_negative;
        for (int i = 0; i < al.size(); ++i) {
            digits_arr_.add(al.get(i));
        }
    }

    public BigInt(String s) {
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
        removeStartZeroes(this);
    }

    /**
     * This method equals to operator =
     * @param other other BigInt class
     */
    public void set_big_int(BigInt other) {  // equal opertaor =
        digits_arr_.clear();
        for (int i = 0; i < other.digits_arr_.size(); ++i) {
            digits_arr_.add(0);
        }
        for (int i = 0; i < other.digits_arr_.size(); ++i) {
            digits_arr_.set(i, other.digits_arr_.get(i));
        }
        is_negative_ = other.is_negative_;
    }

    /**
     * This method gives you string number representation.
     * @return String number.
     */
    @NonNull
    public String toString() {
        String result = is_negative_ ? "-" : "";
        Integer a = (digits_arr_.isEmpty() ? 0 : digits_arr_.get(digits_arr_.size() - 1));
        result += a.toString();
        for (int i = digits_arr_.size() - 2; i >= 0; --i) {
            String temp = digits_arr_.get(i).toString();
            BigIntHelper h = new BigIntHelper();
            result += h.cpp_string_con(9 - temp.length(), '0') + temp;
        }
        return result;
    }

    /**
     * This method is equals to operator < .
     * @param other BigInt class to compare.
     * @return True or False.
     */
    public boolean operatorLess(BigInt other) {
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

    /**
     * This method is equals to operator > .
     * @param other BigInt class to compare.
     * @return True or False.
     */
    public boolean operatorMore(BigInt other) {
        return other.operatorLess(this);
    }

    /**
     * This method is equals to operator >= .
     * @param other BigInt class to compare.
     * @return True or False.
     */
    public boolean operatorMoreOrEqual(BigInt other) {
        return !(this.operatorLess(other));
    }

    /**
     * This method is equals to operator <= .
     * @param other BigInt class to compare.
     * @return True or False.
     */
    public boolean operatorLessOrEqual(BigInt other) {
        return !(this.operatorMore(other));
    }

    /**
     * This method is equals to operator == .
     * @param other BigInt class to compare.
     * @return True or False.
     */
    public boolean operatorEqual(BigInt other) {  // equal operator ==
        return !(this.operatorLess(other) || this.operatorMore(other));
    }

    /**
     * This method multiply your number by -1
     * @return New BigInt
     */
    public BigInt operatorUnMinus() {
        BigInt copy = this;
        if (this.digits_arr_.isEmpty() || (this.digits_arr_.size() == 1 && this.digits_arr_.get(0) == 0)) {
            copy.is_negative_ = false;
            return copy;
        }
        copy.is_negative_ = !copy.is_negative_;
        return copy;
    }

    /**
     * This method calculate sum
     * @param left first BigInt class
     * @param right second BigInt class
     * @return Copy of sum of these classes
     */
    public BigInt operatorPlus(BigInt left, BigInt right) {
        if (!left.is_negative_ && right.is_negative_) {
            return left.operatorMinus(left, right.operatorUnMinus());
        }
        if (left.is_negative_ && !right.is_negative_) {
            return right.operatorMinus(right, left.operatorUnMinus());
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
        BigInt temp = new BigInt(answer_subseq, left.is_negative_);
        removeStartZeroes(temp);
        return temp;
    }

    /**
     * This method calculate difference
     * @param left first BigInt class
     * @param right second BigInt class
     * @return Copy of difference of these classes
     */
    public BigInt operatorMinus(BigInt left, BigInt right) {
        if (!left.is_negative_ && right.is_negative_) {
            return left.operatorPlus(left, right.operatorUnMinus());
        }
        if (left.is_negative_ && !right.is_negative_) {
            BigInt temp = left.operatorPlus(left.operatorUnMinus(), right);
            temp.is_negative_ = true;
            return temp;
        }
        if (abs(left).operatorLess(abs(right))) {
            BigInt result = abs(right).operatorMinus(right, abs(left));
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

        BigInt temp = new BigInt(answer_subseq, left.is_negative_);
        removeStartZeroes(temp);
        return temp;
    }

    /**
     * This method calculate multiplication
     * @param left first BigInt class
     * @param right second BigInt class
     * @return Copy of multiplication of these classes
     */
    public BigInt operatorMultiply(BigInt left, BigInt right) {
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
        BigInt temp = new BigInt(answer_subseq, !(answer_subseq.size() == 1 && answer_subseq.get(0) == 0) && (left.is_negative_ ^ right.is_negative_));

        removeStartZeroes(temp);
        return temp;
    }

    /**
     * This method equals to prefix increment
     * @return BigInt
     */
    public BigInt operator_prefix_increment() {
        BigInt a = new BigInt(1);
        return this.operatorPlusEqual(a);
    }

    /**
     * This method equals to prefix decrement
     * @return BigInt
     */
    public BigInt operator_prefix_decrement() {
        BigInt a = new BigInt(1);
        return this.operatorMinusEqual(a);
    }

    /**
     * This method equals to postfix increment
     * @return BigInt
     */
    public BigInt operator_postfix_increment() {
        BigInt a = new BigInt(1);
        this.operatorPlusEqual(a);
        return this.operatorMinus(this, a);
    }

    /**
     * This method equals to postfix decrement
     * @return BigInt
     */
    public BigInt operator_postfix_decrement() {
        BigInt a = new BigInt(1);
        this.operatorMinusEqual(a);
        return this.operatorPlus(this, a);
    }

    /**
     * This method equals to operator += .
     * @param num BigInt class to add
     * @return BigInt
     */
    public BigInt operatorPlusEqual(BigInt num) {  //equal a+=b
        this.set_big_int(this.operatorPlus(this, num));
        return this;
    }

    /**
     * This method equals to operator -= .
     * @param num BigInt class to subtract
     * @return BigInt
     */
    public BigInt operatorMinusEqual(BigInt num) {
        this.set_big_int(this.operatorMinus(this, num));
        return this;
    }

    /**
     * This method equals to operator *= .
     * @param num BigInt class to multiply
     * @return BigInt
     */
    public BigInt operatorMultiplyEqual(BigInt num) {
        this.set_big_int(this.operatorMultiply(this, num));
        return this;
    }
    public void shiftRight() {
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

    /**
     * This method equals to module the number
     * @return copy of module(BigInt)
     */
    public BigInt abs(BigInt bi) {
        BigInt result = bi;
        result.is_negative_ = false;
        return result;
    }

    public void removeStartZeroes(BigInt num) {
        while (num.digits_arr_.size() > 1 && num.digits_arr_.get(digits_arr_.size() - 1) == 0) {
            num.digits_arr_.remove(digits_arr_.size() - 1);
        }
        if (num.digits_arr_.size() == 1 && num.digits_arr_.get(0) == 0) num.is_negative_ = false;
    }

    private final ArrayList<Integer> digits_arr_ = new ArrayList<Integer>();
    private boolean is_negative_;

    static int k_base = 1000000000;

}
