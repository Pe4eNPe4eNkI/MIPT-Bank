package com.example.mipt_bank_app.BigInt;

public class BigIntHelper {

    public String cpp_string_con(int size, char ch) {
        String result = "";
        for (int i = 0; i < size; ++i) {
            result += ch;
        }
        return result;
    }
}
