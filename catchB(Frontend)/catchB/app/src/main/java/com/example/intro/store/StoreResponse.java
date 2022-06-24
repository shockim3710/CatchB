package com.example.intro.store;

import com.google.gson.annotations.SerializedName;

public class StoreResponse {
    @SerializedName("item_credit")
    public int item_credit;

    @SerializedName("count")
    public int count;

    public int getItem_credit() {
        return item_credit;
    }

    public int getCount(){
        return count;
    }
}
