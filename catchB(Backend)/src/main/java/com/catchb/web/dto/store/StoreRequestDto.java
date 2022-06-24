package com.catchb.web.dto.store;

import com.catchb.domain.store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreRequestDto {
    private String item_category;
    private String item_name;
    private String item_credit;
    private String file_name;

    public StoreRequestDto(String item_category, String item_name, String item_credit, String file_name) {
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_credit = item_credit;
        this.file_name = file_name;
    }

    public Store toEntity(){
        return Store.builder()
                .item_category(item_category)
                .item_name(item_name)
                .item_credit(item_credit)
                .file_name(file_name)
                .build();
    }
}
