package com.catchb.web.dto.users;

import lombok.Getter;

@Getter
public class UserinfoDto {
    private String user_id;
    private String user_nickname;
    private int user_credit;

    public UserinfoDto(String user_id, String user_nickname, int user_credit) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_credit = user_credit;
    }
}
