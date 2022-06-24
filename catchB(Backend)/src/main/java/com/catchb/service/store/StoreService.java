package com.catchb.service.store;

import com.catchb.domain.store.Store;
import com.catchb.domain.store.StoreRepository;
import com.catchb.web.dto.store.StoreCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private Store store;

    @Transactional
    public Store save(String item_category, String item_name, String item_credit, String file_name){
        store = new Store(item_category,item_name,item_credit, file_name);
        return  storeRepository.save(store);
    }

    public StoreCountDto countDto(String item_name){
        int count = storeRepository.countItem(item_name);
        return new StoreCountDto(count);
    }

    @Transactional
    public void useItem(String user_id, String item_name){
        storeRepository.useItem(user_id, item_name);
    }

    public List<Store> findByCredit(String user_id){ return storeRepository.findByCredit(user_id);}
}
