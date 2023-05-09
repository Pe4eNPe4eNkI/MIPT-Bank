package com.example.mipt_bank_app.ui.dashboard;

public class history_item {
    private String type;
    private String sum;
    private String id;

    private String operation_id_;

    public history_item(String type, String sum, String id, String operation_id) {
        this.type = type;
        this.sum = sum;
        this.id = id;
        operation_id_ = operation_id;
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

    public String getOperationId() {
        return operation_id_;
    }
}
