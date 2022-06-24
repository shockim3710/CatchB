package com.catchb.web.dto.images;

import com.catchb.domain.images.Images;
import lombok.Getter;

@Getter
public class ImagesResponseDto {
    private Long image_id;
    private String image_route;
    private String image_address;
    private String image_starttime;
    private String image_endtime;
    private String image_credit;
    private String image_description;
    private String image_name;
    private String image_hint;

    public ImagesResponseDto(Images entity){
        this.image_id = entity.getImage_id();
        this.image_address = entity.getImage_address();
        this.image_credit = entity.getImage_credit();
        this.image_starttime = entity.getImage_starttime();
        this.image_endtime = entity.getImage_endtime();
        this.image_description = entity.getImage_description();
        this.image_route = entity.getImage_route();
        this.image_name = entity.getImage_name();
        this.image_hint = entity.getImage_hint();
    }
}
