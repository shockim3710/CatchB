package com.catchb.domain.users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicInsert
@Component
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_num;

    @Column(nullable = false)
    private String user_id;
    @Column(nullable = false)
    private String user_pw;

    @Column(nullable = true)

    private String user_nickname;

    @Column(nullable = true)
    private String user_phone;

    @ColumnDefault("0")
    private int user_credit;

    @Builder
    public Users(String user_id, String user_pw, String user_nickname, String user_phone, int user_credit){
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nickname = user_nickname;
        this.user_phone = user_phone;
        this.user_credit = user_credit;
    }
}
