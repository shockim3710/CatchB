package com.example.intro.mainpage;

import com.google.gson.annotations.SerializedName;

public class PicturesRequest {

    @SerializedName("pictures_name")
    public String input_pname;

    @SerializedName("pictures_loc")
    public String input_ploc;

    @SerializedName("pictures_path")
    public String inputP_path;

    public PicturesRequest(String input_ploc, String input_pname, String inputP_path) {
        this.input_pname = input_pname;
        this.input_ploc = input_ploc;
        this.inputP_path = inputP_path;
    }
}
