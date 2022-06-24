package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;

public class ImageRequest {

    @SerializedName("image_address")
    public String input_address;

    @SerializedName("image_credit")
    public String input_credit;

    @SerializedName("image_starttime")
    public String input_start_time;

    @SerializedName("image_endtime")
    public String input_end_time;

    @SerializedName("image_description")
    public String input_hashtag;

    @SerializedName("image_hint")
    public String input_hint;

    @SerializedName("image_route")
    public String input_route;

    public ImageRequest(String input_address, String input_credit, String input_start_time, String input_end_time, String input_hashtag, String input_hint, String input_image) {
        this.input_address = input_address;
        this.input_credit = input_credit;
        this.input_start_time = input_start_time;
        this.input_end_time = input_end_time;
        this.input_hashtag = input_hashtag;
        this.input_hint = input_hint;
        this.input_route = input_image;
    }
}