package com.catchb.web.dto.users;

import lombok.Getter;

@Getter
public class UserCheckDto {
    private String result;
    public UserCheckDto(String result){
        this.result = result;
    }
}
