package com.example.intro.signup;

import com.google.gson.annotations.SerializedName;

public class IdSameRequest {
    @SerializedName("user_id")

    public String inputID;

    public IdSameRequest(String inputID) {
        this.inputID = inputID;

    }
}
