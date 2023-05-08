package com.example.mipt_bank_app.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.MyDialogFragment;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentDashboardBinding;
import com.example.mipt_bank_app.Constants;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ListView history_list = (ListView) getView().findViewById(R.id.history);
        TextView text_hist = (TextView) getView().findViewById(R.id.textView_his);
        Constants.oper_counter = 1;

        if (Constants.entered == 1) {
            history_list.setVisibility(View.VISIBLE);
            text_hist.setText("History of your operations");
        }

        else {
            history_list.setVisibility(View.INVISIBLE);
            text_hist.setText("Please, enter account");
        }

        ArrayList<HistoryItem> historyItems_list;

        historyItems_list = new ArrayList<>();

        /*historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));
        historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: 001"));*/



        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, catNames);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, catNames);*/

        HistoryAdapter adapter_h = new HistoryAdapter(getActivity(), R.layout.list_item, historyItems_list);

        int a = adapter_h.getCount();

        history_list.setAdapter(adapter_h);

        for (int k = 0; k <= 20; k++) {
            historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: " + Integer.toString(Constants.oper_counter)));
            Constants.oper_counter += 1;
            adapter_h.notifyDataSetChanged();
        }

        /*Button bt = (Button) getView().findViewById(R.id.button_test);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyItems_list.add(new HistoryItem("Withdraw" + "\n", "1000$", "id: " + Integer.toString(Constants.oper_counter)));
                Constants.oper_counter += 1;
                adapter_h.notifyDataSetChanged();
            }
        });*/

        history_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*FragmentManager manager = getFragmentManager();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(manager, "myDialog");*/

                /*MyDialogFragment myDialogFragment = new MyDialogFragment();
                FragmentManager manager = getFragmentManager();
                //myDialogFragment.show(manager, "dialog");

                FragmentTransaction transaction = manager.beginTransaction();
                myDialogFragment.show(transaction, "dialog");*/

                new AlertDialog.Builder(getContext()).setTitle(Html.fromHtml("<font color='#5E5E5E'>Do you want to cancel the operation?</font>")).setPositiveButton(Html.fromHtml("<font color='#FAA634'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        historyItems_list.remove(i);
                        adapter_h.notifyDataSetChanged();
                        Constants.oper_counter -= 1;
                    }
                }).setNegativeButton(Html.fromHtml("<font color='#9D9FA2'>No</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}