package com.example.mipt_bank_app.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentDashboardBinding;
import com.example.mipt_bank_app.Helper;
import com.example.mipt_bank_app.operations.i_easy_money_operation;
import com.example.mipt_bank_app.operations.i_ne_easy_money_operation;
import com.example.mipt_bank_app.operations.operation_db;
import com.example.mipt_bank_app.operations.refill_operation;
import com.example.mipt_bank_app.operations.transfer_operation;
import com.example.mipt_bank_app.operations.withdrawal_operation;

import java.util.ArrayList;

public class dashboard_fragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboard_view_model dashboardViewModel =
                new ViewModelProvider(this).get(dashboard_view_model.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ListView history_list = (ListView) getView().findViewById(R.id.history);
        TextView text_hist = (TextView) getView().findViewById(R.id.textView_his);

        if (Helper.entered == 1) {
            history_list.setVisibility(View.VISIBLE);
            text_hist.setText("History of your operations");
            ArrayList<history_item> historyItems = new ArrayList<history_item>();

            if (find_operations(Helper.adult.getID()) != null) {
                historyItems = find_operations(Helper.adult.getID());
            }


            history_adapter adapter_h = new history_adapter(getActivity(), R.layout.list_item, historyItems);

            if (!historyItems.isEmpty()) {
                adapter_h.notifyDataSetChanged();
            }
            history_list.setAdapter(adapter_h);


            if (!historyItems.isEmpty()) {
                adapter_h.notifyDataSetChanged();
            }

            ArrayList<history_item> finalHistoryItems = historyItems;
            history_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    new AlertDialog.Builder(getContext()).setTitle(Html.fromHtml("<font color='#5E5E5E'>Do you want to cancel the operation?</font>")).setPositiveButton(Html.fromHtml("<font color='#FAA634'>Yes</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            String type = finalHistoryItems.get(i).getType();
                            String oper_id = finalHistoryItems.get(i).getOperationId();
                            i_easy_money_operation easy_oper = null;
                            i_ne_easy_money_operation ne_easy_oper = null;

                            String sub_type = type.substring(0, type.length() - 1);

                            if (sub_type.equals(Helper.REFIL)) {
                                easy_oper = new refill_operation(Helper.billsDB, Helper.personDB, Helper.operationDB);
                            } else if (sub_type.equals(Helper.WITHDRAWAL)) {
                                easy_oper = new withdrawal_operation(Helper.billsDB, Helper.personDB, Helper.operationDB);
                            } else if (sub_type.equals(Helper.TRANSFER)) {
                                ne_easy_oper = new transfer_operation(Helper.billsDB, Helper.personDB, Helper.operationDB);
                            }
                            Cursor cursor = Helper.operationDB.find_bill(oper_id);
                            cursor.moveToFirst();
                            String sender_card_id = cursor.getString(0);
                            String reciver_card_id = cursor.getString(1);
                            String sum = cursor.getString(2);
                            String sender_id = cursor.getString(3);
                            String reciver_id = cursor.getString(4);
                            String operation_type = cursor.getString(5);

                            if (!sender_card_id.equals("")) {
                                Cursor cursor1 = Helper.billsDB.get_bill(sender_card_id);
                                cursor1.moveToFirst();
                                String card_type2 = cursor1.getString(0);
                                if (ne_easy_oper != null) {
                                    ne_easy_oper.cancelTransferOperation(sender_card_id, reciver_card_id, sum, card_type2);
                                }
                            } else {
                                Cursor cursor1 = Helper.billsDB.get_bill(reciver_id);
                                cursor1.moveToFirst();
                                String card_type = cursor1.getString(0);
                                if (easy_oper != null) {
                                    easy_oper.cancelOperation(reciver_id, sum, card_type);
                                }
                            }

                            Helper.operationDB.deleteData(oper_id);
                            finalHistoryItems.remove(i);
                            adapter_h.notifyDataSetChanged();
                        }
                    }).setNegativeButton(Html.fromHtml("<font color='#9D9FA2'>No</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }
            });
        } else {
            history_list.setVisibility(View.INVISIBLE);
            text_hist.setText("Please, enter account");
        }

    }

    public ArrayList<history_item> find_operations(String id) {
        operation_db odb = new operation_db(getContext());
        ArrayList<history_item> temp = new ArrayList<history_item>();
        Cursor cursor = odb.getOperations(Helper.adult.getID());
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String card_id = cursor.getString(0);
                String sum = cursor.getString(2);
                String operation_type = cursor.getString(5);
                String operation_id = cursor.getString(6);

                temp.add(new history_item(operation_type + "\n", sum, "id: " + operation_id, operation_id));
                Helper.oper_counter += 1;
            } while (cursor.moveToNext());
            return temp;
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}