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
import com.example.mipt_bank_app.bill.BillFactory;
import com.example.mipt_bank_app.bill.Debit;
import com.example.mipt_bank_app.Helper;

public class get_debit extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_get_debit, container, false);
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
                BillFactory bf = new BillFactory();
                Debit debit = bf.buildDebit("",Helper.adult.getID(),"");
                if (Helper.have_debit == 0) {
                    Helper.have_debit = 1;
                    Navigation.findNavController(view).navigate(R.id.navigation_home);
                    Helper.billsDB.addBill(debit);
                }
            }
        });
    }
}