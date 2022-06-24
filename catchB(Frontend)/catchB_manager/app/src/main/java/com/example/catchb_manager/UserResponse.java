package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("user_credit")
    public int user_credit;

    public String getUser_id() { return user_id; }
    public int getUser_credit() { return user_credit; }
}