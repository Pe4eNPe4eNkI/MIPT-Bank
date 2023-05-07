package com.example.mipt_bank_app;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PersonDB DB_Person;

    public Account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static Account newInstance(String param1, String param2) {
        Account fragment = new Account();
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*TextView cl = (TextView) getView().findViewById(R.id.block);
        cl.setText("HUIHUI");*/

        Button rewrite_btn = (Button) getView().findViewById(R.id.rewrite_button);
        TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

        EditText login_re = (EditText) getView().findViewById(R.id.sign_up_login);
        EditText password1_re = (EditText) getView().findViewById(R.id.rewrite_password1);
        EditText password2_re = (EditText) getView().findViewById(R.id.rewrite_password2);
        EditText surname_re = (EditText) getView().findViewById(R.id.sign_up_surname);
        EditText name_re = (EditText) getView().findViewById(R.id.sign_up_namereal);
        EditText address_re = (EditText) getView().findViewById(R.id.rewrite_address);
        EditText passport_re = (EditText) getView().findViewById(R.id.rewrite_passport);

        DB_Person = new PersonDB(getContext());
        rewrite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginTXT = login_re.getText().toString();
                String password1TXT = password1_re.getText().toString();
                String password2TXT = password2_re.getText().toString();
                String first_nameTXT = surname_re.getText().toString();
                String second_nameTXT = name_re.getText().toString();
                String addressTXT = address_re.getText().toString();
                String passport_idTXT = passport_re.getText().toString();

                if (!password1TXT.equals(password2TXT)) {
                    password1_re.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    password2_re.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }

                Boolean check_insert_data = DB_Person.updateUserData(loginTXT,
                                                                     password1TXT,
                                                                     first_nameTXT,
                                                                     second_nameTXT,
                                                                     addressTXT,
                                                                     passport_idTXT);

                /*if (!check_insert_data) {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.entered = 0;
                Navigation.findNavController(view).navigate(R.id.navigation_notifications);
            }
        });
    }
}