package com.example.mipt_bank_app;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registration extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registration() {
        // Required empty public constructor
    }

    PersonDB DB_Person;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registration.
     */
    // TODO: Rename and change types and number of parameters
    public static Registration newInstance(String param1, String param2) {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*TextView cl = (TextView) getView().findViewById(R.id.block);
        cl.setText("HUIHUI");*/
        TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

        EditText login_sign_up = (EditText) getView().findViewById(R.id.sign_up_login);
        EditText password1_sign_up = (EditText) getView().findViewById(R.id.sign_up_password1);
        EditText password2_sign_up = (EditText) getView().findViewById(R.id.sign_up_password2);
        EditText surname_sign_up = (EditText) getView().findViewById(R.id.sign_up_surname);
        EditText name_sign_up = (EditText) getView().findViewById(R.id.sign_up_namereal);
        EditText address_sign_up = (EditText) getView().findViewById(R.id.sign_up_address);
        EditText passport_sign_up = (EditText) getView().findViewById(R.id.sign_up_passport);

        DB_Person = new PersonDB(this);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_notifications);
            }
        });

        Button btn = (Button) getView().findViewById(R.id.sign_in_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginTXT = login_sign_up.getText().toString();
                String password1TXT = password1_sign_up.getText().toString();
                String password2TXT = password2_sign_up.getText().toString();
                String first_nameTXT = surname_sign_up.getText().toString();
                String second_nameTXT = name_sign_up.getText().toString();
                String addressTXT = address_sign_up.getText().toString();
                String passport_idTXT = passport_sign_up.getText().toString();

                if (!password1TXT.equals(password2TXT)) {
                    Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                } else if (loginTXT.isEmpty() && first_nameTXT.isEmpty() && second_nameTXT.isEmpty() && password1TXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "fill in all required fields", Toast.LENGTH_SHORT).show();
                }

                Boolean check_insert_data = DB_Person.insertUserData(loginTXT,
                                                                     password1TXT,
                                                                     first_nameTXT,
                                                                     second_nameTXT,
                                                                     addressTXT,
                                                                     passport_idTXT);

                if (!check_insert_data) {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

                Constants.entered = 1;
                Navigation.findNavController(view).navigate(R.id.account);
            }
        });
    }
}