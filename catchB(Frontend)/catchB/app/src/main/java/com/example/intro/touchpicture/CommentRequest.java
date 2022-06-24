package com.example.intro.touchpicture;

import com.google.gson.annotations.SerializedName;

public class CommentRequest {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("comment_des")
    public String comment_des;

    @SerializedName("image_id")
    public Long image_id;

    @SerializedName("image_icon")
    public int imageIcon;

    public CommentRequest(String user_id, String comment_des, Long image_id, int imageIcon) {
        this.user_id = user_id;
        this.comment_des = comment_des;
        this.image_id = image_id;
        this.imageIcon = imageIcon;
    }
}
