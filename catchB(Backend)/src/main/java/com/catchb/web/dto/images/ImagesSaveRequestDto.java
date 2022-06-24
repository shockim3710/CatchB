package com.catchb.web.dto.images;

import com.catchb.domain.images.Images;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImagesSaveRequestDto {
    private String image_address;
    private String image_starttime;
    private String image_endtime;
    private String image_description;
    private String image_credit;
    private String image_route;
    private String image_name;
    private String image_hint;

    public ImagesSaveRequestDto(String image_address, String image_credit, String image_starttime, String image_endtime, String image_description, String image_route, String image_name, String image_hint){
        this.image_address = image_address;
        this.image_credit = image_credit;
        this.image_starttime = image_starttime;
        this.image_endtime = image_endtime;
        this.image_description = image_description;
        this.image_route = image_route;
        this.image_name = image_name;
        this.image_hint = image_hint;

    }
    public Images toEntity() {
        return Images.builder()
                .image_address(image_address)
                .image_credit(image_credit)
                .image_starttime(image_starttime)
                .image_endtime(image_endtime)
                .image_description(image_description)
                .image_route(image_route)
                .image_name(image_name)
                .image_hint(image_hint)
                .build();
    }
}
