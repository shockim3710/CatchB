package com.catchb.domain.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    @Column(nullable = false)
    private String item_category;

    @Column(nullable = false)
    private String item_name;

    @Column(columnDefinition = "INTEGER",nullable = false)
    private String item_credit;

    @Column(nullable = false)
    private String file_name;

    private String user_id;

    @Builder
    public Store(String item_category, String item_name, String item_credit, String file_name) {
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_credit = item_credit;
        this.file_name = file_name;
    }
}
