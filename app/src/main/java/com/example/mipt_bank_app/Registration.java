package com.example.mipt_bank_app;

import android.app.AlertDialog;
import android.database.Cursor;
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
        if (Constants.entered == 0) {
            TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.navigation_notifications);
                }
            });

            Button btn_up = (Button) getView().findViewById(R.id.sign_in_button);
            btn_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersonDB pdb = new PersonDB(getContext());

                    EditText surname_text = (EditText) getView().findViewById(R.id.sign_in_surname);
                    EditText name_text = (EditText) getView().findViewById(R.id.sign_in_namereal);
                    EditText address_text = (EditText) getView().findViewById(R.id.sign_in_address);
                    EditText passport_id_text = (EditText) getView().findViewById(R.id.sign_in_passport);
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_in_login);
                    EditText password_text = (EditText) getView().findViewById(R.id.sign_in_password);

                    String surname = surname_text == null ? "" : surname_text.getText().toString();
                    String name = name_text == null ? "" : name_text.getText().toString();
                    String address = address_text == null ? "" : address_text.getText().toString();
                    String passport_id = passport_id_text == null ? "" : passport_id_text.getText().toString();
                    String login = login_text == null ? "" : login_text.getText().toString();
                    String password = password_text == null ? "" : password_text.getText().toString();

                    if (!surname.isEmpty() && !name.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
                        pdb.insertUserData(login, password, name, surname, address, passport_id);
                        Constants.entered = 1;
                        Navigation.findNavController(view).navigate(R.id.account);
                    } else {
                        Toast.makeText(getActivity(), "Enter important fields", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
        }
    }
}