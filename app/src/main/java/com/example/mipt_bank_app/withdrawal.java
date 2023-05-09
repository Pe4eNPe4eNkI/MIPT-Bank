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


public class withdrawal extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_withdrawal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView refill = (TextView) getView().findViewById(R.id.back_to_bill_lobby);

        refill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

        Button withdrawal_button = (Button) getView().findViewById(R.id.withdrawal_confirm_button);

        withdrawal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                withdrawal_operation refill = new withdrawal_operation(new bills_db(getContext()), new person_db(getContext()));
                EditText money = (EditText) getView().findViewById(R.id.sum);
                if (money != null) {
                    String temp = money.getText().toString();
                    refill.executeOperation(constants.person.get_id(), temp, constants.operation);
                    Navigation.findNavController(view).navigate(R.id.navigation_home);
                } else {
                    Toast.makeText(getActivity(), "Enter sum! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}