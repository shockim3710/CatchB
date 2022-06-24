package com.catchb.web.dto.users;

import com.catchb.domain.users.Users;
import lombok.Getter;

@Getter
public class UsersResponseDto {
    private Long user_num;
    private String user_id;
    private String user_pw;
    private String user_nickname;
    private String user_phone;
    private int user_credit;

    public UsersResponseDto(Users entity){
        this.user_num = entity.getUser_num();
        this.user_id = entity.getUser_id();
        this.user_pw = entity.getUser_pw();
        this.user_nickname = entity.getUser_nickname();
        this.user_phone = entity.getUser_phone();
        this.user_credit = entity.getUser_credit();

    }
}
