package com.catchb.web.dto.users;
import com.catchb.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String user_id;
    private String user_pw;
    private String user_nickname;
    private String user_phone;
    private int user_credit;

    @Builder
    public UsersSaveRequestDto(String user_id, String user_pw, String user_nickname, String user_phone, int user_credit){
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nickname = user_nickname;
        this.user_phone = user_phone;
        this.user_credit = user_credit;
    }

    public Users toEntity() {
        return Users.builder()
                .user_id(user_id)
                .user_pw(user_pw)
                .user_nickname(user_nickname)
                .user_phone(user_phone)
                .user_credit(user_credit)
                .build();
    }
}