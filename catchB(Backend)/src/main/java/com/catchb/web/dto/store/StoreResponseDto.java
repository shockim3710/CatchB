package com.catchb.web.dto.store;

import com.catchb.domain.store.Store;

public class StoreResponseDto {
    private Long store_id;
    private String item_category;
    private String item_name;
    private String item_credit;
    private String file_name;
    private String user_id;

    public StoreResponseDto(Store entity) {
        this.store_id = entity.getStore_id();
        this.item_category = entity.getItem_category();
        this.item_name = entity.getItem_name();
        this.item_credit = entity.getItem_credit();
        this.file_name = entity.getFile_name();
        this.user_id = entity.getUser_id();
    }
}
