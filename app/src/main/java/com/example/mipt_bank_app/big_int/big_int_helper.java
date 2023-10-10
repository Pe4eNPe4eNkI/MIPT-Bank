package com.example.mipt_bank_app.big_int;

public class big_int_helper {
    public String cpp_string_con(int size, char ch) {
        String result = "";
        for (int i = 0; i < size; ++i) {
            result += ch;
        }
        return result;
    }
}
