package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class CommentResponse {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("comment_des")
    public String comment_des;

    @SerializedName("image_icon")
    public int image_icon;

    public String getUser_id() { return user_id; }
    public String getComment_des() {
        return comment_des;
    }
    public int getImage_icon() { return image_icon; }
}
