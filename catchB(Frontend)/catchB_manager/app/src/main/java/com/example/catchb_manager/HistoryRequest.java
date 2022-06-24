package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;

public class HistoryRequest {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("histroyname")
    public String histry_name;

    @SerializedName("credit")
    public String credit;

    @SerializedName("credit_info")
    public String credit_info;

    public HistoryRequest(String user_id, String histry_name, String credit, String credit_info) {
        this.user_id = user_id;
        this.histry_name = histry_name;
        this.credit = credit;
        this.credit_info = credit_info;
    }
}
