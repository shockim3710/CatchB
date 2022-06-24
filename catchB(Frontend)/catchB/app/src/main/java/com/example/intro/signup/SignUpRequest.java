package com.example.intro.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("user_id")

    public String inputID;

    @SerializedName("user_pw")

    public String inputPW;

    @SerializedName("user_nickname")

    public String inputPHONE;

    @SerializedName("user_phone")

    public String inputNICKNAME;



    public SignUpRequest(String inputID,String inputPW ,String inputNICKNAME ,String inputPHONE) {
        this.inputID = inputID;
        this.inputPHONE = inputPHONE;
        this.inputPW = inputPW;
        this.inputNICKNAME = inputNICKNAME;
    }

}
