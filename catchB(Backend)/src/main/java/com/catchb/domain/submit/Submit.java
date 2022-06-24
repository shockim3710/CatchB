package com.catchb.domain.submit;

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
public class Submit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submit_id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String submit_address;

    @Column(nullable = false)
    private String file_name;

    @Column(columnDefinition = "INTEGER",nullable = false)
    @ColumnDefault("0")
    private String submit_check; // 0이면 체크후 1이면 체크전

    @Column(columnDefinition = "INTEGER",nullable = false)
    private String submit_credit;

    @Column(nullable = false)
    private String hashtag;

    @Builder
    public Submit(String user_id, String submit_address, String file_name, String submit_check, String submit_credit, String hashtag) {
        this.user_id = user_id;
        this.submit_address = submit_address;
        this.file_name = file_name;
        this.submit_check = submit_check;
        this.submit_credit = submit_credit;
        this.hashtag = hashtag;
    }
}
