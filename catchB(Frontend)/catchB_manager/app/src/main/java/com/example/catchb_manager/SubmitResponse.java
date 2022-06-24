package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;

public class SubmitResponse {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("submit_address")
    public String submit_address;

    @SerializedName("submit_credit")
    public String submit_credit;

    @SerializedName("file_name")
    public String file_name;

    @SerializedName("hashtag")
    public String hashtag;

    @SerializedName("submit_check")
    public String submit_check;



}
