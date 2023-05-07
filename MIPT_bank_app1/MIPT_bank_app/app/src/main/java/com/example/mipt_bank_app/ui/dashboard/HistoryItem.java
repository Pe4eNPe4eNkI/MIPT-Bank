package com.example.mipt_bank_app.ui.dashboard;

public class HistoryItem {
    private String type;
    private String sum;
    private String id;

    public HistoryItem(String type, String sum, String id) {
        this.type = type;
        this.sum = sum;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getSum() {
        return sum;
    }

    public String getId() {
        return id;
    }
}
