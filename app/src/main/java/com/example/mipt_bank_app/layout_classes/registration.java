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

import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.constants;
import com.example.mipt_bank_app.person.person;
import com.example.mipt_bank_app.person.person_builder;
import com.example.mipt_bank_app.person.person_db;
import com.example.mipt_bank_app.person.person_director;

public class registration extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (constants.entered == 0) {
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
                    person_db pdb = new person_db(getContext());

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

                    person_builder pb = new person_builder();
                    pb.set_first_name(name).set_second_name(surname).set_address(address).set_passport_id(passport_id).set_login(login).set_password(password1);
                    person_director pd = new person_director();
                    person person = pd.createPerson(pb);

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
                    } else if (pdb.personFind(login)) {
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
                    } else {
                        if (pdb.insertUserData(person)) {
                            Navigation.findNavController(view).navigate(R.id.navigation_notifications);
                            Toast.makeText(getActivity(), "Great!\t" + pdb.getMaxId(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), " I can't registrate you!    " + pdb.getMaxId(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
    }
}