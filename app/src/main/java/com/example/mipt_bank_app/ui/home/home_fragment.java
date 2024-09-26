package com.example.mipt_bank_app.ui.home;

import android.annotation.SuppressLint;
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

import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.BillDir.Bill;
import com.example.mipt_bank_app.databinding.FragmentHomeBinding;

public class home_fragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

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

        TextView typeSelected = (TextView) getView().findViewById(R.id.type_selected);
        TextView available = (TextView) getView().findViewById(R.id.selected_1);
        TextView debt = (TextView) getView().findViewById(R.id.selected_2);
        TextView income = (TextView) getView().findViewById(R.id.selected_3);
        TextView availableSum = (TextView) getView().findViewById(R.id.selected_num_1);
        TextView debtSum = (TextView) getView().findViewById(R.id.selected_num_2);
        TextView incomeSum = (TextView) getView().findViewById(R.id.selected_num_3);

        TextView cardIdSelected = (TextView) getView().findViewById(R.id.card_id_selected);

        TextView selectedDescription = (TextView) getView().findViewById(R.id.selected_descriprion);
        TextView debitDescription = (TextView) getView().findViewById(R.id.debit_description);
        TextView creditDescription = (TextView) getView().findViewById(R.id.credit_description);
        TextView depositDescription = (TextView) getView().findViewById(R.id.deposit_description);

        TextView textView9 = (TextView) getView().findViewById(R.id.textView9);
        TextView textView13 = (TextView) getView().findViewById(R.id.textView13);
        TextView debitAvailable = (TextView) getView().findViewById(R.id.debit_available);
        TextView debitCashback = (TextView) getView().findViewById(R.id.debit_cashback);

        TextView textView7 = (TextView) getView().findViewById(R.id.textView7);
        TextView textView12 = (TextView) getView().findViewById(R.id.textView12);
        TextView creditAvailable = (TextView) getView().findViewById(R.id.credit_available);
        TextView creditDebt = (TextView) getView().findViewById(R.id.credit_debt);

        TextView textView14 = (TextView) getView().findViewById(R.id.textView14);
        TextView textView10 = (TextView) getView().findViewById(R.id.textView10);
        TextView depositAvailable = (TextView) getView().findViewById(R.id.deposit_available);
        TextView depositIncome = (TextView) getView().findViewById(R.id.deposit_income);

        ConstraintLayout debit = (ConstraintLayout) getView().findViewById(R.id.debit);
        ConstraintLayout credit = (ConstraintLayout) getView().findViewById(R.id.credit);
        ConstraintLayout deposit = (ConstraintLayout) getView().findViewById(R.id.deposit);

        ConstraintLayout selectorDebit = (ConstraintLayout) getView().findViewById(R.id.selector_debit);
        ConstraintLayout selectorCredit = (ConstraintLayout) getView().findViewById(R.id.selector_credit);
        ConstraintLayout selectorDeposit = (ConstraintLayout) getView().findViewById(R.id.selector_deposit);

        if (Helper.adult != null && Helper.entered != 0) {


            if (Helper.billsDB.tryFindBill(Helper.adult, Helper.BILL_KIND_DEBIT)) {
                Helper.haveDebit = 1;
                Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                cursor.moveToFirst();
                String balanceFromDb = cursor.getString(Helper.billDbColumnNumber.get("balance"));
                String spentMoney = cursor.getString(Helper.billDbColumnNumber.get("spent_money"));
                Double cashback = Double.parseDouble(spentMoney) / 100;
                debitAvailable.setText(balanceFromDb + " $");
                debitCashback.setText(cashback + " $");

            }
            if (Helper.billsDB.tryFindBill(Helper.adult, Helper.BILL_KIND_CREDIT)) {
                Helper.haveCredit = 1;
                Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                cursor.moveToFirst();
                String balanceFromDb = cursor.getString(Helper.billDbColumnNumber.get("balance"));
                String debtFromDb = cursor.getString(Helper.billDbColumnNumber.get("debt"));
                creditAvailable.setText(balanceFromDb + " $");
                creditDebt.setText(debtFromDb + " $");
            }
            if (Helper.billsDB.tryFindBill(Helper.adult, Helper.BILL_KIND_DEPOSIT)) {
                Helper.haveDeposit = 1;
                Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                cursor.moveToFirst();
                String balanceFromDb = cursor.getString(Helper.billDbColumnNumber.get("balance"));
                String incomeFromDb = cursor.getString(Helper.billDbColumnNumber.get("percent"));
                depositAvailable.setText(balanceFromDb + " $");
                depositIncome.setText(incomeFromDb + " $");
            }
        }


        if (Helper.entered == 1) {
            selectedDescription.setVisibility(View.INVISIBLE);

            creditDescription.setVisibility(View.INVISIBLE);
            depositDescription.setVisibility(View.INVISIBLE);


            typeSelected.setVisibility(View.VISIBLE);
            available.setVisibility(View.VISIBLE);
            debt.setVisibility(View.VISIBLE);
            income.setVisibility(View.VISIBLE);
            availableSum.setVisibility(View.VISIBLE);
            debtSum.setVisibility(View.VISIBLE);
            incomeSum.setVisibility(View.VISIBLE);

            debit.setClickable(true);
            credit.setClickable(true);
            deposit.setClickable(true);

            double globalAvailable = 0.0;
            double globalDebt = 0.0;
            double globalIncome = 0.0;

            if (Helper.haveDebit == 1) {
                Cursor cursorDebit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                cursorDebit.moveToFirst();

                double debitBalance = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("balance")));
                double spentMoney = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("spent_money")));

                Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(debitBalance));
                bill.setUniqueProperty(Double.toString(spentMoney));
                bill.update();


                globalAvailable += Double.parseDouble(bill.getCashSize());
                globalIncome += Double.parseDouble(bill.getUniqueProperty().getThird());
            }

            if (Helper.haveCredit == 1) {
                Cursor cursorCredit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                cursorCredit.moveToFirst();

                double creditBalance = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("balance")));
                double debtFromDb = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("debt")));

                Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(creditBalance));
                bill.setUniqueProperty(Double.toString(debtFromDb));
                bill.update();

                globalAvailable += Double.parseDouble(bill.getCashSize());
                globalDebt += Double.parseDouble(bill.getUniqueProperty().getSecond());
            }

            if (Helper.haveDeposit == 1) {
                Cursor cursorDeposit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                cursorDeposit.moveToFirst();

                double depositBalance = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("balance")));
                double incomeFromDb = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("percent")));

                Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(depositBalance));
                bill.setUniqueProperty(Double.toString(incomeFromDb));
                bill.update();

                globalAvailable += Double.parseDouble(bill.getCashSize());
                globalIncome += Double.parseDouble(bill.getUniqueProperty().getSecond());
            }

            availableSum.setText(globalAvailable+ "$");
            debtSum.setText(globalDebt + "$");
            incomeSum.setText(globalIncome + "$");
        } else {
            selectedDescription.setVisibility(View.VISIBLE);
            debitDescription.setVisibility(View.VISIBLE);
            creditDescription.setVisibility(View.VISIBLE);
            depositDescription.setVisibility(View.VISIBLE);

            transfer.setVisibility(View.INVISIBLE);
            refill.setVisibility(View.INVISIBLE);
            withdrawal.setVisibility(View.INVISIBLE);
            typeSelected.setVisibility(View.INVISIBLE);
            available.setVisibility(View.INVISIBLE);
            debt.setVisibility(View.INVISIBLE);
            income.setVisibility(View.INVISIBLE);
            availableSum.setVisibility(View.INVISIBLE);
            debtSum.setVisibility(View.INVISIBLE);
            incomeSum.setVisibility(View.INVISIBLE);

            debit.setClickable(false);
            credit.setClickable(false);
            deposit.setClickable(false);
        }

        if (Helper.haveDebit == 1) {
            debitDescription.setVisibility(View.INVISIBLE);
            textView9.setVisibility(View.VISIBLE);
            textView13.setVisibility(View.VISIBLE);
            debitAvailable.setVisibility(View.VISIBLE);
            debitCashback.setVisibility(View.VISIBLE);
        } else {
            debitDescription.setVisibility(View.VISIBLE);
            textView9.setVisibility(View.INVISIBLE);
            textView13.setVisibility(View.INVISIBLE);
            debitAvailable.setVisibility(View.INVISIBLE);
            debitCashback.setVisibility(View.INVISIBLE);
        }

        if (Helper.haveCredit == 1) {
            creditDescription.setVisibility(View.INVISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textView12.setVisibility(View.VISIBLE);
            creditAvailable.setVisibility(View.VISIBLE);
            creditDebt.setVisibility(View.VISIBLE);
        } else {
            creditDescription.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.INVISIBLE);
            textView12.setVisibility(View.INVISIBLE);
            creditAvailable.setVisibility(View.INVISIBLE);
            creditDebt.setVisibility(View.INVISIBLE);
        }

        if (Helper.haveDeposit == 1) {
            depositDescription.setVisibility(View.INVISIBLE);
            textView14.setVisibility(View.VISIBLE);
            textView10.setVisibility(View.VISIBLE);
            depositAvailable.setVisibility(View.VISIBLE);
            depositIncome.setVisibility(View.VISIBLE);
        } else {
            depositDescription.setVisibility(View.VISIBLE);
            textView14.setVisibility(View.INVISIBLE);
            textView10.setVisibility(View.INVISIBLE);
            depositAvailable.setVisibility(View.INVISIBLE);
            depositIncome.setVisibility(View.INVISIBLE);
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
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (Helper.entered == 1 && Helper.haveDebit == 0) {
                    Navigation.findNavController(view).navigate(R.id.get_debit);
                } else if (Helper.entered == 1 && Helper.haveDebit == 1 && Helper.selected_key != 1) {
                    Helper.selected_key = 1;
                    selectorDebit.setVisibility(View.VISIBLE);
                    selectorCredit.setVisibility(View.INVISIBLE);
                    selectorDeposit.setVisibility(View.INVISIBLE);
                    typeSelected.setText("DEBIT CARD");
                    available.setText("Available");
                    debt.setText("Cashback");
                    income.setVisibility(View.INVISIBLE);
                    incomeSum.setVisibility((View.INVISIBLE));
                    cardIdSelected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);

                    Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                    cursor.moveToFirst();
                    String cardId = cursor.getString(Helper.billDbColumnNumber.get("bill_id"));
                    String available = cursor.getString(Helper.billDbColumnNumber.get("balance"));
                    String spentMoney = cursor.getString(Helper.billDbColumnNumber.get("spent_money"));

                    availableSum.setText(available + "$");
                    debtSum.setText(Double.parseDouble(spentMoney)/100 + "$");
                    cardIdSelected.setText("Card id:" + cardId);

                    Helper.selectedCardId = cardId;

                } else if (Helper.entered == 1 && Helper.haveDebit == 1 && Helper.selected_key == 1) {
                    Helper.selected_key = 0;
                    selectorDebit.setVisibility(View.INVISIBLE);
                    typeSelected.setText("CURRENT ACCOUNT");
                    available.setText("Available");
                    debt.setText("Debt");
                    income.setText("Income");
                    income.setVisibility(View.VISIBLE);
                    incomeSum.setVisibility((View.VISIBLE));
                    cardIdSelected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);

                    double globalAvailable = 0.0;
                    double globalDebt = 0.0;
                    double globalIncome = 0.0;

                    if (Helper.haveDebit == 1) {
                        Cursor cursorDebit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                        cursorDebit.moveToFirst();

                        double debitBalance = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("balance")));
                        double spentMoney = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("spent_money")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(debitBalance));
                        bill.setUniqueProperty(Double.toString(spentMoney));
                        bill.update();


                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getThird());
                    }

                    if (Helper.haveCredit == 1) {
                        Cursor cursorCredit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                        cursorCredit.moveToFirst();

                        double creditBalance = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("balance")));
                        double debtFromDb = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("debt")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(creditBalance));
                        bill.setUniqueProperty(Double.toString(debtFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalDebt += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    if (Helper.haveDeposit == 1) {
                        Cursor cursorDeposit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                        cursorDeposit.moveToFirst();

                        double depositBalance = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("balance")));
                        double incomeFromDb = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("percent")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(depositBalance));
                        bill.setUniqueProperty(Double.toString(incomeFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    availableSum.setText(globalAvailable + "$");
                    debtSum.setText(globalDebt + "$");
                    incomeSum.setText(globalIncome + "$");
                }
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Helper.entered == 1 && Helper.haveCredit == 0) {
                    Navigation.findNavController(view).navigate(R.id.getCredit);
                } else if (Helper.entered == 1 && Helper.haveCredit == 1 && Helper.selected_key != 2) {
                    Helper.selected_key = 2;
                    selectorDebit.setVisibility(View.INVISIBLE);
                    selectorCredit.setVisibility(View.VISIBLE);
                    selectorDeposit.setVisibility(View.INVISIBLE);
                    typeSelected.setText("CREDIT CARD");
                    available.setText("Available");
                    debt.setText("Debt");
                    income.setVisibility(View.INVISIBLE);
                    incomeSum.setVisibility((View.INVISIBLE));
                    cardIdSelected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);

                    Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                    cursor.moveToFirst();
                    String card_id = cursor.getString(1);
                    String avalible = cursor.getString(3);
                    String debt = cursor.getString(4);

                    availableSum.setText(avalible + "$");
                    debtSum.setText(debt + "$");
                    cardIdSelected.setText("Card id: " + card_id);

                    Helper.selectedCardId = card_id;

                } else if (Helper.entered == 1 && Helper.haveCredit == 1 && Helper.selected_key == 2) {
                    Helper.selected_key = 0;
                    selectorCredit.setVisibility(View.INVISIBLE);
                    typeSelected.setText("CURRENT ACCOUNT");
                    available.setText("Available");
                    debt.setText("Debt");
                    income.setText("Income");
                    income.setVisibility(View.VISIBLE);
                    incomeSum.setVisibility((View.VISIBLE));
                    cardIdSelected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);
                    double globalAvailable = 0.0;
                    double globalDebt = 0.0;
                    double globalIncome = 0.0;

                    if (Helper.haveDebit == 1) {
                        Cursor cursorDebit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                        cursorDebit.moveToFirst();

                        double debitBalance = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("balance")));
                        double spentMoney = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("spent_money")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(debitBalance));
                        bill.setUniqueProperty(Double.toString(spentMoney));
                        bill.update();


                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getThird());
                    }

                    if (Helper.haveCredit == 1) {
                        Cursor cursorCredit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                        cursorCredit.moveToFirst();

                        double creditBalance = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("balance")));
                        double debtFromDb = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("debt")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(creditBalance));
                        bill.setUniqueProperty(Double.toString(debtFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalDebt += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    if (Helper.haveDeposit == 1) {
                        Cursor cursorDeposit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                        cursorDeposit.moveToFirst();

                        double depositBalance = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("balance")));
                        double incomeFromDb = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("percent")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(depositBalance));
                        bill.setUniqueProperty(Double.toString(incomeFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    availableSum.setText(globalAvailable + "$");
                    debtSum.setText(globalDebt + "$");
                    incomeSum.setText(globalIncome + "$");
                }
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Helper.entered == 1 && Helper.haveDeposit == 0) {
                    Navigation.findNavController(view).navigate(R.id.getDeposit);
                } else if (Helper.entered == 1 && Helper.haveDeposit == 1 && Helper.selected_key != 3) {
                    Helper.selected_key = 3;
                    selectorDebit.setVisibility(View.INVISIBLE);
                    selectorCredit.setVisibility(View.INVISIBLE);
                    selectorDeposit.setVisibility(View.VISIBLE);
                    typeSelected.setText("DEPOSIT CARD");
                    available.setText("Available");
                    debt.setText("Income");
                    income.setVisibility(View.INVISIBLE);
                    incomeSum.setVisibility((View.INVISIBLE));
                    cardIdSelected.setVisibility(View.VISIBLE);
                    transfer.setVisibility(View.VISIBLE);
                    refill.setVisibility(View.VISIBLE);
                    withdrawal.setVisibility(View.VISIBLE);
                    transfer.setEnabled(true);
                    refill.setEnabled(true);
                    withdrawal.setEnabled(true);


                    Cursor cursor = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                    cursor.moveToFirst();
                    String cardId = cursor.getString(Helper.billDbColumnNumber.get("bill_id"));
                    String available = cursor.getString(Helper.billDbColumnNumber.get("balance"));
                    String income = cursor.getString(Helper.billDbColumnNumber.get("percent"));

                    availableSum.setText(available + "$");
                    debtSum.setText(income + "$");
                    cardIdSelected.setText("Card id: " + cardId);

                    Helper.selectedCardId = cardId;
                } else if (Helper.entered == 1 && Helper.haveDeposit == 1 && Helper.selected_key == 3) {
                    Helper.selected_key = 0;
                    selectorDeposit.setVisibility(View.INVISIBLE);
                    typeSelected.setText("CURRENT ACCOUNT");
                    available.setText("Available");
                    debt.setText("Debt");
                    income.setText("Income");
                    income.setVisibility(View.VISIBLE);
                    incomeSum.setVisibility((View.VISIBLE));
                    cardIdSelected.setVisibility(View.INVISIBLE);
                    transfer.setVisibility(View.INVISIBLE);
                    refill.setVisibility(View.INVISIBLE);
                    withdrawal.setVisibility(View.INVISIBLE);
                    transfer.setEnabled(false);
                    refill.setEnabled(false);
                    withdrawal.setEnabled(false);
                    double globalAvailable = 0.0;
                    double globalDebt = 0.0;
                    double globalIncome = 0.0;

                    if (Helper.haveDebit == 1) {
                        Cursor cursorDebit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEBIT);
                        cursorDebit.moveToFirst();

                        double debitBalance = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("balance")));
                        double spentMoney = new Double(cursorDebit.getString(Helper.billDbColumnNumber.get("spent_money")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(debitBalance));
                        bill.setUniqueProperty(Double.toString(spentMoney));
                        bill.update();


                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getThird());
                    }

                    if (Helper.haveCredit == 1) {
                        Cursor cursorCredit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_CREDIT);
                        cursorCredit.moveToFirst();

                        double creditBalance = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("balance")));
                        double debtFromDb = new Double(cursorCredit.getString(Helper.billDbColumnNumber.get("debt")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(creditBalance));
                        bill.setUniqueProperty(Double.toString(debtFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalDebt += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    if (Helper.haveDeposit == 1) {
                        Cursor cursorDeposit = Helper.billsDB.getBill(Helper.adult.getID(), Helper.BILL_KIND_DEPOSIT);
                        cursorDeposit.moveToFirst();

                        double depositBalance = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("balance")));
                        double incomeFromDb = new Double(cursorDeposit.getString(Helper.billDbColumnNumber.get("percent")));

                        Bill bill = Helper.debitFactory.buildBill("", "", Double.toString(depositBalance));
                        bill.setUniqueProperty(Double.toString(incomeFromDb));
                        bill.update();

                        globalAvailable += Double.parseDouble(bill.getCashSize());
                        globalIncome += Double.parseDouble(bill.getUniqueProperty().getSecond());
                    }

                    availableSum.setText(globalAvailable + "$");
                    debtSum.setText(globalDebt + "$");
                    incomeSum.setText(globalIncome + "$");
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