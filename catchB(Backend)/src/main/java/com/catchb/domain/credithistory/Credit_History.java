package com.catchb.domain.credithistory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Credit_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credithistory_num;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String histroyname;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private String credit;

    @Column(nullable = false)
    private String credit_info;

    @Builder
    public Credit_History(String user_id, String histroyname, String credit, String credit_info) {
        this.user_id = user_id;
        this.histroyname = histroyname;
        this.credit = credit;
        this.credit_info = credit_info;
    }

}
