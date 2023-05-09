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

import com.example.mipt_bank_app.constants;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentNotificationsBinding;
import com.example.mipt_bank_app.person;
import com.example.mipt_bank_app.person_builder;
import com.example.mipt_bank_app.person_db;
import com.example.mipt_bank_app.person_director;

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
        if (constants.entered == 0) {
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
                    person_db pdb = new person_db(getContext());
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_in_login);
                    EditText password_text = (EditText) getView().findViewById(R.id.sign_in_password);
                    login_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    password_text.setHintTextColor(Color.parseColor("#9D9FA2"));
                    login_text.setTextColor(Color.parseColor("#9D9FA2"));
                    password_text.setTextColor(Color.parseColor("#9D9FA2"));

                    String login = login_text.getText().toString();
                    String password = password_text.getText().toString();

                    if (pdb.personFind(login, password) && !login.isEmpty() && !password.isEmpty()) {

                        Cursor cursor = pdb.getPerson(login, password);
                        cursor.moveToFirst();
                        String id = cursor.getString(1);
                        String first_name = cursor.getString(3);
                        String second_name = cursor.getString(4);
                        String address = cursor.getString(5);
                        String passport_id = cursor.getString(6);

                        person_builder pb = new person_builder();
                        pb.set_first_name(first_name).set_second_name(second_name).set_address(address).set_passport_id(passport_id).set_login(login).set_password(password);
                        person_director pd = new person_director();
                        person person1 = pd.createPerson(pb);
                        person1.set_id(id);

                        constants.person = person1;

                        constants.entered = 1;
                        Toast.makeText(getActivity(), "Great!", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.account);
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
                    } else if (!pdb.personFind(login)) {
                        password_text.setHintTextColor(Color.parseColor("#FAA634"));
                        login_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Invalid login", Toast.LENGTH_SHORT).show();
                    } else if (!pdb.personFind(login, password)) {
                        login_text.setTextColor(Color.parseColor("#FAA634"));
                        password_text.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Incorrect login or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}