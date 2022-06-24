package com.example.catchb_manager;

import com.google.gson.annotations.SerializedName;


public class ImageResponse {
    @SerializedName("image_id")
    public Long image_id;

    @SerializedName("image_route")
    public String image_route;

    @SerializedName("image_address")
    public String image_address;

    @SerializedName("image_starttime")
    public String image_starttime;

    @SerializedName("image_endtime")
    public String image_endtime;

    @SerializedName("image_credit")
    public String image_credit;

    @SerializedName("image_description")
    public String image_description;

    @SerializedName("image_hint")
    public String image_hint;

    @SerializedName("image_name")
    public String image_name;

}
