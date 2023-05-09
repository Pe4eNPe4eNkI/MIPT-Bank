package com.example.mipt_bank_app.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.constants;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.bills_db;
import com.example.mipt_bank_app.databinding.FragmentHomeBinding;

public class home_fragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        home_view_model homeViewModel =
                new ViewModelProvider(this).get(home_view_model.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageButton transfer = (ImageButton) getView().findViewById(R.id.transfer);
        ImageButton refill = (ImageButton) getView().findViewById(R.id.refill);
        ImageButton withdrawal = (ImageButton) getView().findViewById(R.id.withdrawal);

        transfer.setEnabled(false);
        refill.setEnabled(false);
        withdrawal.setEnabled(false);

        TextView type_selected = (TextView) getView().findViewById(R.id.type_selected);
        TextView selected_1 = (TextView) getView().findViewById(R.id.selected_1);
        TextView selected_2 = (TextView) getView().findViewById(R.id.selected_2);
        TextView selected_3 = (TextView) getView().findViewById(R.id.selected_3);
        TextView selected_num_1 = (TextView) getView().findViewById(R.id.selected_num_1);
        TextView selected_num_2 = (TextView) getView().findViewById(R.id.selected_num_2);
        TextView selected_num_3 = (TextView) getView().findViewById(R.id.selected_num_3);

        TextView card_id_selected = (TextView) getView().findViewById(R.id.card_id_selected);

        TextView selected_descriprion = (TextView) getView().findViewById(R.id.selected_descriprion);
        TextView debit_description = (TextView) getView().findViewById(R.id.debit_description);
        TextView credit_description = (TextView) getView().findViewById(R.id.credit_description);
        TextView deposit_description = (TextView) getView().findViewById(R.id.deposit_description);

        TextView textView9 = (TextView) getView().findViewById(R.id.textView9);
        TextView textView13 = (TextView) getView().findViewById(R.id.textView13);
        TextView debit_available = (TextView) getView().findViewById(R.id.debit_available);
        TextView debit_cashback = (TextView) getView().findViewById(R.id.debit_cashback);

        TextView textView7 = (TextView) getView().findViewById(R.id.textView7);
        TextView textView12 = (TextView) getView().findViewById(R.id.textView12);
        TextView credit_available = (TextView) getView().findViewById(R.id.credit_available);
        TextView credit_debt = (TextView) getView().findViewById(R.id.credit_debt);

        TextView textView14 = (TextView) getView().findViewById(R.id.textView14);
        TextView textView10 = (TextView) getView().findViewById(R.id.textView10);
        TextView deposit_available = (TextView) getView().findViewById(R.id.deposit_available);
        TextView deposit_income = (TextView) getView().findViewById(R.id.deposit_income);

        ConstraintLayout debit = (ConstraintLayout) getView().findViewById(R.id.debit);
        ConstraintLayout credit = (ConstraintLayout) getView().findViewById(R.id.credit);
        ConstraintLayout deposit = (ConstraintLayout) getView().findViewById(R.id.deposit);

        ConstraintLayout selector_debit = (ConstraintLayout) getView().findViewById(R.id.selector_debit);
        ConstraintLayout selector_credit = (ConstraintLayout) getView().findViewById(R.id.selector_credit);
        ConstraintLayout selector_deposit = (ConstraintLayout) getView().findViewById(R.id.selector_deposit);

        bills_db bdb = new bills_db(getContext());

        if (constants.person != null && constants.entered != 0) {


            if (bdb.try_find_bill(constants.person, constants.BILL_KIND_DEBIT)) {
                constants.have_debit = 1;
                Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_DEBIT);
                cursor.moveToFirst();
                String avalible = cursor.getString(3);
                String cashback = cursor.getString(4);
                debit_available.setText(avalible + " $");
                debit_cashback.setText(cashback + " $");

            }
            if (bdb.try_find_bill(constants.person, constants.BILL_KIND_CREDIT)) {
                constants.have_credit = 1;
                Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_CREDIT);
                cursor.moveToFirst();
                String avalible = cursor.getString(3);
                String debt = cursor.getString(4);
                credit_available.setText(avalible + " $");
                credit_debt.setText(debt + " $");
            }
            if (bdb.try_find_bill(constants.person, constants.BILL_KIND_DEPOSIT)) {
                constants.have_deposit = 1;
                Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_DEPOSIT);
                cursor.moveToFirst();
                String avalible = cursor.getString(3);
                String income = cursor.getString(4);
                deposit_available.setText(avalible + " $");
                deposit_income.setText(income + " $");
            }
        }


        if (constants.entered == 1) {
            selected_descriprion.setVisibility(View.INVISIBLE);

            credit_description.setVisibility(View.INVISIBLE);
            deposit_description.setVisibility(View.INVISIBLE);


            type_selected.setVisibility(View.VISIBLE);
            selected_1.setVisibility(View.VISIBLE);
            selected_2.setVisibility(View.VISIBLE);
            selected_3.setVisibility(View.VISIBLE);
            selected_num_1.setVisibility(View.VISIBLE);
            selected_num_2.setVisibility(View.VISIBLE);
            selected_num_3.setVisibility(View.VISIBLE);

            debit.setClickable(true);
            credit.setClickable(true);
            deposit.setClickable(true);
        } else {
            selected_descriprion.setVisibility(View.VISIBLE);
            debit_description.setVisibility(View.VISIBLE);
            credit_description.setVisibility(View.VISIBLE);
            deposit_description.setVisibility(View.VISIBLE);

            transfer.setVisibility(View.INVISIBLE);
            refill.setVisibility(View.INVISIBLE);
            withdrawal.setVisibility(View.INVISIBLE);
            type_selected.setVisibility(View.INVISIBLE);
            selected_1.setVisibility(View.INVISIBLE);
            selected_2.setVisibility(View.INVISIBLE);
            selected_3.setVisibility(View.INVISIBLE);
            selected_num_1.setVisibility(View.INVISIBLE);
            selected_num_2.setVisibility(View.INVISIBLE);
            selected_num_3.setVisibility(View.INVISIBLE);

            debit.setClickable(false);
            credit.setClickable(false);
            deposit.setClickable(false);
        }

        if (constants.have_debit == 1) {
            debit_description.setVisibility(View.INVISIBLE);
            textView9.setVisibility(View.VISIBLE);
            textView13.setVisibility(View.VISIBLE);
            debit_available.setVisibility(View.VISIBLE);
            debit_cashback.setVisibility(View.VISIBLE);
        } else {
            debit_description.setVisibility(View.VISIBLE);
            textView9.setVisibility(View.INVISIBLE);
            textView13.setVisibility(View.INVISIBLE);
            debit_available.setVisibility(View.INVISIBLE);
            debit_cashback.setVisibility(View.INVISIBLE);
        }

        if (constants.have_credit == 1) {
            credit_description.setVisibility(View.INVISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textView12.setVisibility(View.VISIBLE);
            credit_available.setVisibility(View.VISIBLE);
            credit_debt.setVisibility(View.VISIBLE);
        } else {
            credit_description.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.INVISIBLE);
            textView12.setVisibility(View.INVISIBLE);
            credit_available.setVisibility(View.INVISIBLE);
            credit_debt.setVisibility(View.INVISIBLE);
        }

        if (constants.have_deposit == 1) {
            deposit_description.setVisibility(View.INVISIBLE);
            textView14.setVisibility(View.VISIBLE);
            textView10.setVisibility(View.VISIBLE);
            deposit_available.setVisibility(View.VISIBLE);
            deposit_income.setVisibility(View.VISIBLE);
        } else {
            deposit_description.setVisibility(View.VISIBLE);
            textView14.setVisibility(View.INVISIBLE);
            textView10.setVisibility(View.INVISIBLE);
            deposit_available.setVisibility(View.INVISIBLE);
            deposit_income.setVisibility(View.INVISIBLE);
        }

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.transfer2);
            }
        });

        refill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.refill2);
            }
        });

        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.withdrawal2);
            }
        });


        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constants.entered == 1 && constants.have_debit == 0) {
                    Navigation.findNavController(view).navigate(R.id.get_debit);
                } else if (constants.entered == 1 && constants.have_debit == 1 && constants.selected_key != 1) {
                    constants.selected_key = 1;
                    selector_debit.setVisibility(View.VISIBLE);
                    selector_credit.setVisibility(View.INVISIBLE);
                    selector_deposit.setVisibility(View.INVISIBLE);
                    type_selected.setText("DEBIT CARD");
                    selected_1.setText("Available");
                    selected_2.setText("Cashback");
                    selected_3.setVisibility(View.INVISIBLE);
                    selected_num_3.setVisibility((View.INVISIBLE));
                    card_id_selected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);

                    Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_DEBIT);
                    cursor.moveToFirst();
                    String card_id = cursor.getString(1);
                    String avalible = cursor.getString(3);
                    String cashback = cursor.getString(4);

                    selected_num_1.setText(avalible + "$");
                    selected_num_2.setText(cashback + "$");
                    card_id_selected.setText("Card id:" + card_id);

                    constants.operation = "debit";

                } else if (constants.entered == 1 && constants.have_debit == 1 && constants.selected_key == 1) {
                    constants.selected_key = 0;
                    selector_debit.setVisibility(View.INVISIBLE);
                    type_selected.setText("CURRENT ACCOUNT");
                    selected_1.setText("Available");
                    selected_2.setText("Debt");
                    selected_3.setText("Income");
                    selected_3.setVisibility(View.VISIBLE);
                    selected_num_3.setVisibility((View.VISIBLE));
                    card_id_selected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);
                    selected_num_1.setText("0$");
                    selected_num_2.setText("0$");
                    selected_num_3.setText("0$");
                }
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constants.entered == 1 && constants.have_credit == 0) {
                    Navigation.findNavController(view).navigate(R.id.getCredit);
                } else if (constants.entered == 1 && constants.have_credit == 1 && constants.selected_key != 2) {
                    constants.selected_key = 2;
                    selector_debit.setVisibility(View.INVISIBLE);
                    selector_credit.setVisibility(View.VISIBLE);
                    selector_deposit.setVisibility(View.INVISIBLE);
                    type_selected.setText("CREDIT CARD");
                    selected_1.setText("Available");
                    selected_2.setText("Debt");
                    selected_3.setVisibility(View.INVISIBLE);
                    selected_num_3.setVisibility((View.INVISIBLE));
                    card_id_selected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);

                    Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_CREDIT);
                    cursor.moveToFirst();
                    String card_id = cursor.getString(1);
                    String avalible = cursor.getString(3);
                    String debt = cursor.getString(4);

                    selected_num_1.setText(avalible + "$");
                    selected_num_2.setText(debt + "$");
                    card_id_selected.setText("Card id: " + card_id);

                    constants.operation = "credit";

                } else if (constants.entered == 1 && constants.have_credit == 1 && constants.selected_key == 2) {
                    constants.selected_key = 0;
                    selector_credit.setVisibility(View.INVISIBLE);
                    type_selected.setText("CURRENT ACCOUNT");
                    selected_1.setText("Available");
                    selected_2.setText("Debt");
                    selected_3.setText("Income");
                    selected_3.setVisibility(View.VISIBLE);
                    selected_num_3.setVisibility((View.VISIBLE));
                    card_id_selected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);
                    selected_num_1.setText("0$");
                    selected_num_2.setText("0$");
                    selected_num_3.setText("0$");
                }
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constants.entered == 1 && constants.have_deposit == 0) {
                    Navigation.findNavController(view).navigate(R.id.getDeposit);
                } else if (constants.entered == 1 && constants.have_deposit == 1 && constants.selected_key != 3) {
                    constants.selected_key = 3;
                    selector_debit.setVisibility(View.INVISIBLE);
                    selector_credit.setVisibility(View.INVISIBLE);
                    selector_deposit.setVisibility(View.VISIBLE);
                    type_selected.setText("DEPOSIT CARD");
                    selected_1.setText("Available");
                    selected_2.setText("Income");
                    selected_3.setVisibility(View.INVISIBLE);
                    selected_num_3.setVisibility((View.INVISIBLE));
                    card_id_selected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);


                    Cursor cursor = bdb.get_bill(constants.person.get_id(), constants.BILL_KIND_DEPOSIT);
                    cursor.moveToFirst();
                    String card_id = cursor.getString(1);
                    String avalible = cursor.getString(3);
                    String income = cursor.getString(4);

                    selected_num_1.setText(avalible + "$");
                    selected_num_2.setText(income + "$");
                    card_id_selected.setText("Card id: " + card_id);

                    constants.operation = "deposit";
                } else if (constants.entered == 1 && constants.have_deposit == 1 && constants.selected_key == 3) {
                    constants.selected_key = 0;
                    selector_deposit.setVisibility(View.INVISIBLE);
                    type_selected.setText("CURRENT ACCOUNT");
                    selected_1.setText("Available");
                    selected_2.setText("Debt");
                    selected_3.setText("Income");
                    selected_3.setVisibility(View.VISIBLE);
                    selected_num_3.setVisibility((View.VISIBLE));
                    card_id_selected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);
                    selected_num_1.setText("0$");
                    selected_num_2.setText("0$");
                    selected_num_3.setText("0$");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}