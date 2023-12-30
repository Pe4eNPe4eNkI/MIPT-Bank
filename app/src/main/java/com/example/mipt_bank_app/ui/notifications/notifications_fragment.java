package com.example.mipt_bank_app.ui.notifications;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentNotificationsBinding;
import com.example.mipt_bank_app.Person.Adult;
import com.example.mipt_bank_app.Person.AdultBuilder;
import com.example.mipt_bank_app.Person.AdultParams;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class notifications_fragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notifications_view_model notificationsViewModel =
                new ViewModelProvider(this).get(notifications_view_model.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (Helper.entered == 0) {
            TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.registration);
                }
            });

            Button btn_in = (Button) getView().findViewById(R.id.sign_in_button);


            btn_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_in_login);
                    EditText password_text = (EditText) getView().findViewById(R.id.sign_in_password);
                    login_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    password_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    login_text.setTextColor(Color.parseColor("#9D9FA2"));
                    password_text.setTextColor(Color.parseColor("#9D9FA2"));

                    String login = login_text.getText().toString();
                    String password = password_text.getText().toString();

                    if (Helper.personDB.personFind(login) && !login.isEmpty() && !password.isEmpty()) {

                        Cursor cursor = Helper.personDB.getPerson(login);
                        cursor.moveToFirst();
                        String id = cursor.getString(Helper.personDbColumnNumber.get("id"));
                        String name = cursor.getString(Helper.personDbColumnNumber.get("first_name"));
                        String surname = cursor.getString(Helper.personDbColumnNumber.get("second_name"));
                        String address = cursor.getString(Helper.personDbColumnNumber.get("address"));
                        String passportId = cursor.getString(Helper.personDbColumnNumber.get("passport_id"));
                        String passwordFromDb = cursor.getString(Helper.personDbColumnNumber.get("password"));
                        String salt = cursor.getString(Helper.personDbColumnNumber.get("salt"));

                        AdultParams adultParams = new AdultParams(name, surname, address, passportId, login, password);

                        AdultBuilder adultBuilder = new AdultBuilder(adultParams);
                        adultBuilder.build();
                        Adult adult = adultBuilder.getPerson();
                        adult.setID(id);
                        try {
                            if(Helper.stringHash.comparePasswords(passwordFromDb, salt, password)){
                                Helper.adult = adult;
                                Helper.entered = 1;
                                Toast.makeText(getActivity(), "Great!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.account);
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (login.isEmpty() && password.isEmpty()) {
                        login_text.setHintTextColor(Color.parseColor("#FAA634"));
                        password_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Incorrect login or password", Toast.LENGTH_SHORT).show();
                    } else if (!login.isEmpty() && password.isEmpty()) {
                        password_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty password", Toast.LENGTH_SHORT).show();
                    } else if (login.isEmpty() && !password.isEmpty()) {
                        login_text.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty login", Toast.LENGTH_SHORT).show();
                    } else if (!Helper.personDB.personFind(login)) {
                        password_text.setHintTextColor(Color.parseColor("#FAA634"));
                        login_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Invalid login", Toast.LENGTH_SHORT).show();
                    } else if (!Helper.personDB.personFind(login)) {
                        login_text.setTextColor(Color.parseColor("#FAA634"));
                        password_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Incorrect login or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(Helper.entered == 1 ){
            Navigation.findNavController(view).navigate(R.id.account);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}