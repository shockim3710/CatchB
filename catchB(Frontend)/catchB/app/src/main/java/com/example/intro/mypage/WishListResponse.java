package com.example.intro.mypage;

import com.google.gson.annotations.SerializedName;

public class WishListResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("image_id")
    public Long image_id;
}
