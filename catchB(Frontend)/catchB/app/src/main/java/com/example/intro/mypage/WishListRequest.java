package com.example.intro.mypage;

import com.google.gson.annotations.SerializedName;

public class WishListRequest {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("image_id")
    public Long image_id;

    @SerializedName("wish_num")
    public Long wish_num;

    public WishListRequest(String user_id, Long image_id, Long wish_num) {
        this.user_id = user_id;
        this.image_id = image_id;
        this.wish_num = wish_num;
    }
}
