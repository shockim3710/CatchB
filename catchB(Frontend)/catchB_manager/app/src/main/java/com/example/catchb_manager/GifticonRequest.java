package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;

public class GifticonRequest {

    @SerializedName("item_category")
    public String item_category;

    @SerializedName("item_name")
    public String item_name;

    @SerializedName("item_credit")
    public String item_credit;

    public GifticonRequest(String item_category, String item_name, String item_credit) {
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_credit = item_credit;
    }
}
