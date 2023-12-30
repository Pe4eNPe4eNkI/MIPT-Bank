package com.example.mipt_bank_app.layout_classes;

import android.graphics.Color;
import android.os.Bundle;

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
import com.example.mipt_bank_app.Person.Adult;
import com.example.mipt_bank_app.Person.AdultBuilder;
import com.example.mipt_bank_app.Person.AdultParams;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class registration extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (Helper.entered == 0) {
            TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.navigation_notifications);
                }
            });

            Button btn_up = (Button) getView().findViewById(R.id.sign_up_button);
            btn_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditText surname_text = (EditText) getView().findViewById(R.id.sign_up_surname);
                    EditText name_text = (EditText) getView().findViewById(R.id.sign_up_name);
                    EditText address_text = (EditText) getView().findViewById(R.id.sign_up_address);
                    EditText passport_id_text = (EditText) getView().findViewById(R.id.sign_up_passport);
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_up_login);
                    EditText password1_text = (EditText) getView().findViewById(R.id.sign_up_password1);
                    EditText password2_text = (EditText) getView().findViewById(R.id.sign_up_password2);
                    EditText pinCodeText = (EditText) getView().findViewById(R.id.sign_up_pincode);

                    String surname = surname_text == null ? "" : surname_text.getText().toString();
                    String name = name_text == null ? "" : name_text.getText().toString();
                    String address = address_text == null ? "" : address_text.getText().toString();
                    String passport_id = passport_id_text == null ? "" : passport_id_text.getText().toString();
                    String login = login_text == null ? "" : login_text.getText().toString();
                    String password1 = password1_text == null ? "" : password1_text.getText().toString();
                    String password2 = password2_text == null ? "" : password2_text.getText().toString();
                    String pinCode = pinCodeText == null ? "" : pinCodeText.getText().toString();

                    AdultParams adultParams = new AdultParams(name, surname, address, passport_id, login, password1);

                    AdultBuilder adultBuilder = new AdultBuilder(adultParams);
                    adultBuilder.build();
                    Adult adult = adultBuilder.getPerson();



                    login_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    surname_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    name_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    password1_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    password2_text.setHintTextColor(Color.parseColor("#9D9FA2"));

                    login_text.setTextColor(Color.parseColor("#9D9FA2"));
                    password1_text.setTextColor(Color.parseColor("#9D9FA2"));
                    password2_text.setTextColor(Color.parseColor("#9D9FA2"));

                    if (surname.isEmpty()) {
                        surname_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty surname", Toast.LENGTH_SHORT).show();
                    } else if (name.isEmpty()) {
                        name_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty name", Toast.LENGTH_SHORT).show();
                    } else if (login.isEmpty()) {
                        login_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty login", Toast.LENGTH_SHORT).show();
                    } else if (Helper.personDB.personFind(login)) {
                        login_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "This login is already occupied", Toast.LENGTH_SHORT).show();
                    } else if (!password1.equals(password2)) {
                        password1_text.setTextColor(Color.parseColor("#FAA634"));
                        password2_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
                    } else if (password1.isEmpty()) {
                        password1_text.setHintTextColor(Color.parseColor("#FAA634"));
                        password2_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty password", Toast.LENGTH_SHORT).show();
                    } else if (pinCode.length() < 4 || pinCode.length() > 5) {
                        pinCodeText.setHintTextColor(Color.parseColor("#FAA634"));
                    } else {
                        try {
                            if (Helper.personDB.insertUserData(adult)) {

                                PinCodeDB pinCodeDB = new PinCodeDB(getContext());
                                pinCodeDB.addPerson(login, password1, pinCode);
                                adult.setID(Helper.personDB.getMaxIdPP());
                                Helper.adult = adult;
                                Helper.entered = 1;
                                Navigation.findNavController(view).navigate(R.id.action_registration_to_account);
                                Toast.makeText(getActivity(), "Great!\t" + Helper.personDB.getMaxId(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), " I can't registrate you!    " + Helper.personDB.getMaxId(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException |
                                 UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

        }
    }
}