package com.catchb.domain.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(nativeQuery = true, value = "SELECT count(item_name) FROM Store WHERE item_name=:item_name and user_id IS NULL")
    int countItem(@Param("item_name") String item_name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Store SET user_id=:user_id WHERE store_id = (SELECT min from (SELECT MIN(store_id) as min FROM store WHERE user_id IS NULL AND item_name=:item_name)as t)")
    void useItem(@Param("user_id")String user_id, @Param("item_name")String item_name);

    @Query(nativeQuery = true,value = "SELECT * FROM store where user_id=:user_id")
    List<Store> findByCredit(@Param("user_id") String user_id);
}
