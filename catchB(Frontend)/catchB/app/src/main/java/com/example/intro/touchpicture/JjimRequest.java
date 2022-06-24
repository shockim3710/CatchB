package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class JjimRequest {


    @SerializedName("user_id")
    public String user_id;

    @SerializedName("image_id")
    public Long image_id;

    public JjimRequest(String user_id, Long image_id) {
        this.user_id = user_id;
        this.image_id = image_id;
    }
}
