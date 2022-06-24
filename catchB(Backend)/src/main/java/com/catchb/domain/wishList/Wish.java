package com.catchb.domain.wishList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wish_num;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private Long image_id;

    @Builder
    public Wish( String user_id, Long image_id) {
        this.user_id = user_id;
        this.image_id = image_id;
    }
}
