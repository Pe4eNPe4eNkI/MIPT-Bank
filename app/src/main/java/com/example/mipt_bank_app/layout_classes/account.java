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

import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.constants;
import com.example.mipt_bank_app.person.person;
import com.example.mipt_bank_app.person.person_builder;
import com.example.mipt_bank_app.person.person_db;
import com.example.mipt_bank_app.person.person_director;


public class account extends Fragment {

    person_db DB_Person;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Button rewrite_btn = (Button) getView().findViewById(R.id.rewrite_button);
        TextView reg = (TextView) getView().findViewById(R.id.exit);
        TextView user_id = (TextView) getView().findViewById(R.id.user_id);

        EditText user_login = (EditText) getView().findViewById(R.id.rewrite_login);
        EditText user_surname = (EditText) getView().findViewById(R.id.rewrite_surname);
        EditText user_name = (EditText) getView().findViewById(R.id.rewrite_name);
        EditText user_address = (EditText) getView().findViewById(R.id.rewrite_address);
        EditText user_passport = (EditText) getView().findViewById(R.id.rewrite_passport);
        EditText user_password1 = (EditText) getView().findViewById(R.id.rewrite_password1);
        EditText user_password2 = (EditText) getView().findViewById(R.id.rewrite_password2);

        DB_Person = new person_db(getContext());
        Cursor cursor = DB_Person.getPerson(constants.person.get_login(), constants.person.get_password());
        cursor.moveToFirst();
        String per_login = cursor.getString(0);
        String per_id = cursor.getString(1);
        String per_password = cursor.getString(2);
        String per_surname = cursor.getString(3);
        String per_name = cursor.getString(4);
        String per_address = cursor.getString(5);
        String per_passport = cursor.getString(6);

        user_id.setText("Id: " + per_id);
        user_login.setText(per_login.toString());
        user_password1.setText(per_password.toString());
        user_password2.setText(per_password.toString());
        user_surname.setText(per_surname.toString());
        user_name.setText(per_name.toString());
        user_address.setText(per_address.toString());
        user_passport.setText(per_passport.toString());

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

                login_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                surname_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                name_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                address_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                passport_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                password1_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                password2_text.setHintTextColor(Color.parseColor("#9D9FA2"));

                password1_text.setTextColor(Color.parseColor("#9D9FA2"));
                password2_text.setTextColor(Color.parseColor("#9D9FA2"));

                String login = login_text.getText().toString();
                String password1 = password1_text.getText().toString();
                String password2 = password2_text.getText().toString();
                String surname = surname_text.getText().toString();
                String name = name_text.getText().toString();
                String address = address_text.getText().toString();
                String passport_id = passport_text.getText().toString();

                person_builder pb = new person_builder();
                pb.set_first_name(name).set_second_name(surname).set_address(address).set_passport_id(passport_id).set_login(login).set_password(password1);
                person_director pd = new person_director();
                person person = pd.createPerson(pb);

                if (person.get_second_name().isEmpty()) {
                    surname_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty surname", Toast.LENGTH_SHORT).show();
                } else if (person.get_first_name().isEmpty()) {
                    name_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty name", Toast.LENGTH_SHORT).show();
                } else if (person.get_password().isEmpty()) {
                    password1_text.setHintTextColor(Color.parseColor("#FAA634"));
                    password2_text.setHintTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Empty password", Toast.LENGTH_SHORT).show();
                } else if (!person.get_password().equals(password2)) {
                    password1_text.setTextColor(Color.parseColor("#FAA634"));
                    password2_text.setTextColor(Color.parseColor("#FAA634"));
                    Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Great!", Toast.LENGTH_SHORT).show();
                }

                Boolean check_insert_data = DB_Person.updateUserData(person);

                if (!check_insert_data) {
                    Toast.makeText(getActivity(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constants.entered = 0;
                constants.have_credit = 0;
                constants.have_debit = 0;
                constants.have_deposit = 0;
                Navigation.findNavController(view).navigate(R.id.navigation_notifications);
            }
        });
    }
}