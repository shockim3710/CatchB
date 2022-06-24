package com.catchb.web.dto.store;

import lombok.Getter;

@Getter
public class StoreCountDto {
    private int count;

    public StoreCountDto(int count){
        this.count = count;
    }
}
