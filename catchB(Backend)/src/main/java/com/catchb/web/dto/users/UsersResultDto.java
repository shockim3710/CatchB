package com.catchb.web.dto.users;

import lombok.Getter;

@Getter
public class UsersResultDto {
    private String result;
    public UsersResultDto(String result){
        this.result = result;
    }

}
