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


public class refill extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refill, container, false);
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

        Button refill_button = (Button) getView().findViewById(R.id.refill_confirm_button);

        refill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bills_db db = new bills_db(getContext());
                refill_operation refill = new refill_operation(db, new person_db(getContext()));
                EditText money = (EditText) getView().findViewById(R.id.refill_sum);
                String temp = money.getText().toString();
                refill.execute_operation(constants.person.get_id(), temp, constants.operation);
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });
    }
}