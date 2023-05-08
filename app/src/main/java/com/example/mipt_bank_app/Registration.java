package com.example.mipt_bank_app;

import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

public class Registration extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (Constants.entered == 0) {
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
                    PersonDB pdb = new PersonDB(getContext());

                    EditText surname_text = (EditText) getView().findViewById(R.id.sign_up_surname);
                    EditText name_text = (EditText) getView().findViewById(R.id.sign_up_name);
                    EditText address_text = (EditText) getView().findViewById(R.id.sign_up_address);
                    EditText passport_id_text = (EditText) getView().findViewById(R.id.sign_up_passport);
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_up_login);
                    EditText password1_text = (EditText) getView().findViewById(R.id.sign_up_password1);
                    EditText password2_text = (EditText) getView().findViewById(R.id.sign_up_password2);

                    String surname = surname_text == null ? "" : surname_text.getText().toString();
                    String name = name_text == null ? "" : name_text.getText().toString();
                    String address = address_text == null ? "" : address_text.getText().toString();
                    String passport_id = passport_id_text == null ? "" : passport_id_text.getText().toString();
                    String login = login_text == null ? "" : login_text.getText().toString();
                    String password1 = password1_text == null ? "" : password1_text.getText().toString();
                    String password2 = password2_text == null ? "" : password2_text.getText().toString();

                    if (surname.isEmpty()) {
                        surname_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(getActivity(), "Empty surname", Toast.LENGTH_SHORT).show();
                    } else if (name.isEmpty()) {
                        name_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(getActivity(), "Empty name", Toast.LENGTH_SHORT).show();
                    } else if (login.isEmpty()) {
                        login_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(getActivity(), "Empty login", Toast.LENGTH_SHORT).show();
                    } else if (!password1.equals(password2)) {
                        password1_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        password2_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
                    } else if (password1.isEmpty()) {
                        password1_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        password2_text.getBackground().setColorFilter(Color.parseColor("#FAA634"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(getActivity(), "Empty password", Toast.LENGTH_SHORT).show();
                    } else {
                        pdb.insertUserData(login, password1, name, surname, address, passport_id);
                        Constants.entered = 1;
                        Navigation.findNavController(view).navigate(R.id.account);
                        Toast.makeText(getActivity(), "Great!    " + pdb.getMaxId() , Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}