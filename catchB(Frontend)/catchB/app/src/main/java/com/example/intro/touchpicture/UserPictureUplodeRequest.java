package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class UserPictureUplodeRequest {
    @SerializedName("user_name")
    public String user_name;

    @SerializedName("submit_address")
    public String pictures_loc;

    @SerializedName("file_name")
    public String pictures_name;

    @SerializedName("submit_credit")
    public String submit_credit;


    @SerializedName("hashtag")
    public String hashtag;

    public UserPictureUplodeRequest(String user_name, String pictures_loc, String pictures_name,String submit_credit) {
        this.user_name = user_name;
        this.pictures_loc = pictures_loc;
        this.pictures_name = pictures_name;
        this.submit_credit = submit_credit;
    }
}
