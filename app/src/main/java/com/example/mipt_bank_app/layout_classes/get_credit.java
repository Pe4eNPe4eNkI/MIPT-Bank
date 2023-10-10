package com.example.mipt_bank_app.layout_classes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.bill.bill_factory;
import com.example.mipt_bank_app.bill.bills_db;
import com.example.mipt_bank_app.bill.credit;
import com.example.mipt_bank_app.constants;

public class get_credit extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_get_credit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        TextView withdraw = (TextView) getView().findViewById(R.id.want_sign_in);

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

        Button replenish_b = (Button) getView().findViewById(R.id.sign_in_button);

        replenish_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bills_db bdb = new bills_db(getContext());
                bill_factory bf = new bill_factory();
                credit credit = bf.build_credit(constants.person.get_id());
                if (constants.have_credit == 0) {
                    constants.have_credit = 1;
                    Navigation.findNavController(view).navigate(R.id.navigation_home);
                    bdb.add_bill(credit);
                }
            }
        });
    }
}