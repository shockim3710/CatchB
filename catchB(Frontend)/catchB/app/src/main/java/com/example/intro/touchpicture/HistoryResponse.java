package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class HistoryResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("histroyname")
    public String histry_name;

    @SerializedName("credit")
    public String credit;

    @SerializedName("credit_info")
    public String credit_info;

    public String getUser_id() {
        return user_id;
    }

    public String getHistry_name() {
        return histry_name;
    }

    public String getCredit() {
        return credit;
    }

    public String getCredit_info() {
        return credit_info;
    }
}
