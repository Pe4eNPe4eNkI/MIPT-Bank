package com.example.mipt_bank_app.ui.dashboard;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mipt_bank_app.R;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<HistoryItem> {
    private int resourceLayout;
    private Context mContext;
    private ArrayList<HistoryItem> historyItems_list;

    public HistoryAdapter (@NonNull Context context, int resource, ArrayList<HistoryItem> historyItems_list) {
        super(context, resource, historyItems_list);
        this.resourceLayout = resource;
        this.mContext = context;
        this.historyItems_list = historyItems_list;
    }

    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        /*return super.getView(position, convertView, parent);*/
        View v = convertView;
        int phraseIndex = position;
        if(convertView == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            convertView = LayoutInflater.from(mContext).inflate(resourceLayout, null);
        }

        HistoryItem p = getItem(position);
        if (p != null) {

            TextView type = convertView.findViewById(R.id.type);
            TextView id = convertView.findViewById(R.id.id_oper);
            TextView sum = convertView.findViewById(R.id.sum);

            type.setText(historyItems_list.get(position).getType());
            id.setText(historyItems_list.get(position).getId());
            sum.setText(historyItems_list.get(position).getSum());
        }
        return convertView;
    }

}
