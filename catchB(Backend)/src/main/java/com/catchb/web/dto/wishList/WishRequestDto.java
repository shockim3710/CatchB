package com.catchb.web.dto.wishList;


import com.catchb.domain.wishList.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishRequestDto {
    private String user_id;
    private Long image_id;

    @Builder
    public WishRequestDto(String user_id, Long image_id) {
        this.user_id = user_id;
        this.image_id = image_id;
    }

    public Wish toEntity(){
        return Wish.builder()
                .user_id(user_id)
                .image_id(image_id)
                .build();
    }
}
