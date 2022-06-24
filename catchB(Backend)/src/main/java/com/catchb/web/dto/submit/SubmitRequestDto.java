package com.catchb.web.dto.submit;

import com.catchb.domain.submit.Submit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubmitRequestDto {
    private String user_id;
    private String submit_address;
    private String file_name;
    private String submit_check;
    private String submit_credit;
    private String hashtag;

    @Builder
    public SubmitRequestDto(String user_id, String submit_address, String file_name, String submit_check, String submit_credit, String hashtag) {
        this.user_id = user_id;
        this.submit_address = submit_address;
        this.file_name = file_name;
        this.submit_check = submit_check;
        this.submit_credit = submit_credit;
        this.hashtag = hashtag;
    }

    public Submit toEntity(){
        return Submit.builder()
                .user_id(user_id)
                .submit_address(submit_address)
                .file_name(file_name)
                .submit_check(submit_check)
                .submit_credit(submit_credit)
                .hashtag(hashtag)
                .build();
    }
}
