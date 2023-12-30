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
import com.example.mipt_bank_app.operations.transfer_operation;

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
                transfer_operation transfer = new transfer_operation(Helper.billsDB, Helper.personDB, Helper.operationDB);
                EditText money = (EditText) getView().findViewById(R.id.sum);
                EditText receiver_bill_id = (EditText) getView().findViewById(R.id.receiver_bill_id);
                EditText password = (EditText) getView().findViewById(R.id.password);

                if (money != null && receiver_bill_id != null && password != null) {
                    String temp = money.getText().toString();
                    String receiver_bill = receiver_bill_id.getText().toString();
                    String password_tr = password.getText().toString();

                    password.setHintTextColor(Color.parseColor("#9D9FA2"));
                    money.setHintTextColor(Color.parseColor("#9D9FA2"));
                    receiver_bill_id.setHintTextColor(Color.parseColor("#9D9FA2"));

                    password.setTextColor(Color.parseColor("#9D9FA2"));

                    if (receiver_bill.isEmpty()) {
                        receiver_bill_id.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty recipient's bill id!", Toast.LENGTH_SHORT).show();
                    } else if (temp.isEmpty()) {
                        money.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Invalid sum!", Toast.LENGTH_SHORT).show();
                    } else if (password_tr.isEmpty()) {
                        password.setHintTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Empty Password! ", Toast.LENGTH_SHORT).show();
                    } else if (!Helper.adult.getPassword().equals(password.getText().toString())) {
                        password.setTextColor(Color.parseColor("#FAA634"));
                        Toast.makeText(getActivity(), "Incorrect Password! ", Toast.LENGTH_SHORT).show();
                    } else {
                        transfer.executeTransferOperation(Helper.adult.getID(), receiver_bill, temp, Helper.operation);
                        Navigation.findNavController(view).navigate(R.id.navigation_home);
                    }
                }
            }
        });
    }
}