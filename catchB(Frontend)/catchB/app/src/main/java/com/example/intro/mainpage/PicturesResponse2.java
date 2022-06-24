package com.example.intro.mainpage;

import com.google.gson.annotations.SerializedName;

public class PicturesResponse2 {
    @SerializedName("image_address")
    public String pictures_loc;

    @SerializedName("image_name")
    public String pictures_name;

    @SerializedName("image_id")
    public Long pictures_id;

    @SerializedName("image_route")
    public String pictures_path;

    @SerializedName("image_starttime")
    public String pictures_start;

    @SerializedName("image_endtime")
    public String pictures_end;

    @SerializedName("image_credit")
    public String pictures_credit;

    @SerializedName("image_description")
    public String pictures_des;

    @SerializedName("image_hint")
    public String image_hint;


}