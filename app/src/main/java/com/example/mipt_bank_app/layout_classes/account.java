package com.example.mipt_bank_app.layout_classes;

import android.graphics.Color;
import android.os.Bundle;
import android.database.Cursor;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mipt_bank_app.PinCodeDB;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.Person.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class account extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Button rewrite_btn = (Button) getView().findViewById(R.id.rewrite_button);
        TextView exit = (TextView) getView().findViewById(R.id.exit);
        TextView user_id = (TextView) getView().findViewById(R.id.user_id);

        EditText user_login = (EditText) getView().findViewById(R.id.rewrite_login);
        EditText user_surname = (EditText) getView().findViewById(R.id.rewrite_surname);
        EditText user_name = (EditText) getView().findViewById(R.id.rewrite_name);
        EditText user_address = (EditText) getView().findViewById(R.id.rewrite_address);
        EditText user_passport = (EditText) getView().findViewById(R.id.rewrite_passport);
        EditText user_password1 = (EditText) getView().findViewById(R.id.rewrite_password1);
        EditText user_password2 = (EditText) getView().findViewById(R.id.rewrite_password2);
        EditText user_pinCode = (EditText) getView().findViewById(R.id.rewrite_pinCode);

        Cursor cursor = Helper.personDB.getPerson(Helper.adult.getLogin());
        cursor.moveToFirst();
        String per_login = cursor.getString(Helper.personDbColumnNumber.get("login"));
        String per_id = cursor.getString(Helper.personDbColumnNumber.get("id"));
        String per_password = cursor.getString(Helper.personDbColumnNumber.get("password"));
        String per_surname = cursor.getString(Helper.personDbColumnNumber.get("second_name"));
        String per_name = cursor.getString(Helper.personDbColumnNumber.get("first_name"));
        String per_address = cursor.getString(Helper.personDbColumnNumber.get("address"));
        String per_passport = cursor.getString(Helper.personDbColumnNumber.get("passport_id"));

        PinCodeDB pinCodeDB = new PinCodeDB(getContext());
        String pinCode = pinCodeDB.checkPerson().second;

        user_id.setText("Id: " + per_id);
        user_login.setText(per_login.toString());
        user_password1.setText(per_password.toString());
        user_password2.setText(per_password.toString());
        user_surname.setText(per_surname.toString());
        user_name.setText(per_name.toString());
        user_address.setText(per_address.toString());
        user_passport.setText(per_passport.toString());
        user_pinCode.setText(pinCode);

        rewrite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText login_text = (EditText) getView().findViewById(R.id.rewrite_login);
                EditText password1_text = (EditText) getView().findViewById(R.id.rewrite_password1);
                EditText password2_text = (EditText) getView().findViewById(R.id.rewrite_password2);
                EditText surname_text = (EditText) getView().findViewById(R.id.rewrite_surname);
                EditText name_text = (EditText) getView().findViewById(R.id.rewrite_name);
                EditText address_text = (EditText) getView().findViewById(R.id.rewrite_address);
                EditText passport_text = (EditText) getView().findViewById(R.id.rewrite_passport);
                EditText pinCodeText = (EditText) getView().findViewById(R.id.rewrite_pinCode);

                login_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                surname_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                name_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                address_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                passport_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                password1_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                password2_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                pinCodeText.setHintTextColor(Color.parseColor("#9D9FA2"));

                password1_text.setTextColor(Color.parseColor("#9D9FA2"));
                password2_text.setTextColor(Color.parseColor("#9D9FA2"));

                String login = login_text.getText().toString();
                String password1 = password1_text.getText().toString();
                String password2 = password2_text.getText().toString();
                String surname = surname_text.getText().toString();
                String name = name_text.getText().toString();
                String address = address_text.getText().toString();
                String passport_id = passport_text.getText().toString();
                String rewritePinCode = pinCodeText.getText().toString();

                AdultParams adultParams = new AdultParams(name, surname, address, passport_id, login, password1);

                AdultBuilder adultBuilder = new AdultBuilder(adultParams);
                adultBuilder.build();
                Adult adult = adultBuilder.getPerson();

                if (adult.getSurname().isEmpty()) {
                    surname_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty surname", Toast.LENGTH_SHORT).show();
                } else if (adult.getName().isEmpty()) {
                    name_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty name", Toast.LENGTH_SHORT).show();
                } else if (adult.getPassword().isEmpty()) {
                    password1_text.setHintTextColor(Color.parseColor("#FAA634"));
                    password2_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty password", Toast.LENGTH_SHORT).show();
                } else if (!adult.getPassword().equals(password2)) {
                    password1_text.setTextColor(Color.parseColor("#FAA634"));
                    password2_text.setTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
                } else if (rewritePinCode.length() < 4 || rewritePinCode.length() > 5) {
                    pinCodeText.setTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "PinCode must be 4 symbols", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Great!", Toast.LENGTH_SHORT).show();
                    Boolean check_insert_data = null;
                    try {
                        check_insert_data = Helper.personDB.updateUserData(adult);
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException |
                             UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }

                    if (!check_insert_data) {
                        Toast.makeText(getActivity(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }

                    pinCodeDB.addPerson(login, password1, rewritePinCode);
                }


            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinCodeDB.deletePinCode();
                Helper.entered = 0;
                Helper.haveCredit = 0;
                Helper.haveDebit = 0;
                Helper.haveDeposit = 0;
                Navigation.findNavController(view).navigate(R.id.navigation_notifications);
            }
        });
    }
}