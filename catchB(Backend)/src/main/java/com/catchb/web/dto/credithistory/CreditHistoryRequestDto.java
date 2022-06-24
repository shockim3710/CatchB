package com.catchb.web.dto.credithistory;

import com.catchb.domain.credithistory.Credit_History;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreditHistoryRequestDto {
    private String user_id;
    private String histroyname;
    private String credit;
    private String credit_info;

    @Builder
    public  CreditHistoryRequestDto(String user_id, String histroyname, String credit, String credit_info){
        this.user_id = user_id;
        this.histroyname = histroyname;
        this.credit = credit;
        this.credit_info = credit_info;
    }

    public Credit_History toEntity(){
        return Credit_History.builder()
                .user_id(user_id)
                .histroyname(histroyname)
                .credit(credit)
                .credit_info(credit_info)
                .build();
    }

}
