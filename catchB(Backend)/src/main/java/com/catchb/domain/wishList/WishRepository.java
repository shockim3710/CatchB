package com.catchb.domain.wishList;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM wish where user_id=:user_id and image_id=:image_id")
    List<Wish> findByWish(@Param("user_id") String user_id, @Param("image_id") Long image_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM wish where user_id=:user_id and image_id=:image_id")
    void deleteWish(@Param("user_id") String user_id, @Param("image_id") Long image_id);

    @Query(nativeQuery = true, value = "SELECT * FROM wish where user_id=:user_id")
    List<Wish> findByImageid(@Param("user_id") String user_id);


}