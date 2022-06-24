package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class UserPictureUplodeResponse {
    @SerializedName("user_name")
    public String user_name;

    @SerializedName("submit_address")
    public String pictures_loc;

    @SerializedName("file_name")
    public String pictures_name;
}
