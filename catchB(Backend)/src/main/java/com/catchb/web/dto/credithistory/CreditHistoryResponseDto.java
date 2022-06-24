package com.catchb.web.dto.credithistory;

import com.catchb.domain.credithistory.Credit_History;
import lombok.Getter;

@Getter
public class CreditHistoryResponseDto {
    private Long credithistory_num;
    private String user_id;
    private String histroyname;
    private String credit;
    private String credit_info;

    public CreditHistoryResponseDto(Credit_History entity){
        this.credithistory_num = entity.getCredithistory_num();
        this.user_id = entity.getUser_id();
        this.histroyname = entity.getHistroyname();
        this.credit = entity.getCredit();
        this.credit_info = entity.getCredit_info();
    }
}
