package com.catchb.web.dto.wishList;


import com.catchb.domain.wishList.Wish;
import lombok.Getter;

@Getter
public class WishResponseDto {
    private Long wish_num;
    private String user_id;
    private Long image_id;

    public WishResponseDto(Wish entity){
        this.wish_num = entity.getWish_num();
        this.user_id = entity.getUser_id();
        this.image_id = entity.getImage_id();
    }
}
