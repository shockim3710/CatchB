package com.example.intro.store;

import com.google.gson.annotations.SerializedName;

public class storageResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("item_category")
    public String item_category;

    @SerializedName("item_name")
    public String item_name;

    @SerializedName("item_credit")
    public String item_credit;

    @SerializedName("file_name")
    public String file_name;
}
