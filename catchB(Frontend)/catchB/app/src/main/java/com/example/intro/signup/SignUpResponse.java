package com.example.intro.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("user_id")
    public String user_id;

    public SignUpResponse(String user_id) {
        this.user_id = user_id;
    }
}
