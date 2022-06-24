package com.example.intro.signup;

public class SignUpDTO {
    String user_id;
    String user_pw;
    String user_nickname;
    String user_phone;


    public SignUpDTO(String user_id, String user_pw,
            String user_nickname, String user_phone) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nickname = user_nickname;
        this.user_phone = user_phone;
    }
}
