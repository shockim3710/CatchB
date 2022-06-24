package com.example.intro.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_id")
    public String inputID;

    @SerializedName("user_pw")
    public String inputPW;

    public String getInputID() {
        return inputID;
    }

    public String getInputPW() {
        return inputPW;
    }

    public void setInputID(String inputID) {
        this.inputID = inputID;
    }

    public void setInputPW(String inputPW) {
        this.inputPW = inputPW;
    }

    public LoginRequest(String inputID, String inputPW) {
        this.inputID = inputID;
        this.inputPW = inputPW;
    }

}
