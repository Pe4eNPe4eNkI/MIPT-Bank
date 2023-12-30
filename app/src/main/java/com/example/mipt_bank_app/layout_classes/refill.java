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
import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.operations.refill_operation;

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

                refill_operation refill = new refill_operation(Helper.billsDB, Helper.personDB, Helper.operationDB);
                EditText money = (EditText) getView().findViewById(R.id.sum);
                money.setHintTextColor(Color.parseColor("#9D9FA2"));
                if (money != null) {
                    String temp = money.getText().toString();
                    if (temp.isEmpty()) {
                        money.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Invalid sum!", Toast.LENGTH_SHORT).show();
                    } else {
                        refill.executeOperation(Helper.adult.getID(), temp, Helper.operation);
                        Navigation.findNavController(view).navigate(R.id.navigation_home);
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter sum! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}