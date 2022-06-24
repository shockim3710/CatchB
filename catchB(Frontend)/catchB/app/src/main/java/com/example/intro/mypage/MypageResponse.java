package com.example.intro.mypage;

import com.google.gson.annotations.SerializedName;

public class MypageResponse {
    @SerializedName("user_nickname")
    public String inputNICKNAME;

    @SerializedName("user_id")
    public String inputID;

    @SerializedName("user_credit")
    public int inputCREDIT;


    public String getUser_nickname() {
        return inputNICKNAME;
    }
    public String getUser_id2() { return inputID; }
    public int getUser_credit() { return inputCREDIT; }

}


