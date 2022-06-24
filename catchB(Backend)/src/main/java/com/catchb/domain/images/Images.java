package com.catchb.domain.images;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;

    @Column(nullable = false)
    private String image_route;

    @Column(nullable = false)
    private String image_address;

    //제출 시작일
    @Column(nullable = false)
    private String image_starttime;

    //제출 마감일
    @Column(nullable = false)
    private String image_endtime;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private String image_credit;

    @Column(nullable = false)
    private String image_description;

    @Column(nullable = false)
    private String image_name;

    @Column(nullable = false)
    private String image_hint;

    @Builder
    public Images(String image_address, String image_credit, String image_starttime, String image_endtime, String image_description, String image_route, String image_name,String image_hint){
        this.image_address = image_address;
        this.image_credit = image_credit;
        this.image_starttime = image_starttime;
        this.image_endtime = image_endtime;
        this.image_description = image_description;
        this.image_route = image_route;
        this.image_name = image_name;
        this.image_hint = image_hint;
    }
}
