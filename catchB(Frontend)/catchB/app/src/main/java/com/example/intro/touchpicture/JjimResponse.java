package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class JjimResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("image_id")
    public Long image_id;

    @SerializedName("wish_num")
    public Long wish_num;
}
