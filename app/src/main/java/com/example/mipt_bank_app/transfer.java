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

public class transfer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView withdraw = (TextView) getView().findViewById(R.id.back_to_bill_lobby);

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

        Button transfer_button = (Button) getView().findViewById(R.id.transfer_confirm_button);

        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer_operation transfer = new transfer_operation(new bills_db(getContext()), new person_db(getContext()));
                EditText money = (EditText) getView().findViewById(R.id.sum);
                EditText reciver_bill_id = (EditText) getView().findViewById(R.id.receiver_bill_id);
                EditText password = (EditText) getView().findViewById(R.id.password);
                if (money != null && reciver_bill_id != null && password != null) {
                    String temp = money.getText().toString();
                    String reciver_bill = reciver_bill_id.getText().toString();
                    if (constants.person.get_password().equals(password.getText().toString())) {
                        transfer.executeTransferOperation(constants.person.get_id(), reciver_bill, temp, constants.operation);
                        Navigation.findNavController(view).navigate(R.id.navigation_home);
                    } else {
                        Toast.makeText(getActivity(), "Incorrect Password! ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Enter all fields! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}