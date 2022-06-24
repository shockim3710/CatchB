package com.catchb.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String comment_des;

    @Column(nullable = false)
    private Long image_id;

    @Column(nullable = false)
    private int image_icon;

    @Builder
    public Comment( String user_id, String comment_des, Long image_id, int image_icon) {
        this.user_id = user_id;
        this.comment_des = comment_des;
        this.image_id = image_id;
        this.image_icon = image_icon;
    }
}
