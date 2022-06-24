package com.catchb.web.dto.submit;

import com.catchb.domain.submit.Submit;
import lombok.Getter;

@Getter
public class SubmitResponseDto {
    private String user_id;
    private String submit_address;
    private String file_name;
    private String submit_check;
    private String submit_credit;
    private String hashtag;

    public SubmitResponseDto(Submit entity){
        this.user_id = entity.getUser_id();
        this.submit_address = entity.getSubmit_address();
        this.file_name = entity.getFile_name();
        this.submit_check = entity.getSubmit_check();
        this.submit_credit = entity.getSubmit_credit();
        this.hashtag = entity.getHashtag();
    }
}
