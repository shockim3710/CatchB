package com.catchb.domain.submit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitRepository extends JpaRepository<Submit,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM submit WHERE submit_check = 0")
    List<Submit> findsubmit();

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE submit SET submit_check = 1 WHERE user_id = :user_id AND hashtag = :hashtag AND submit_check = 0")
    void processSubmit(@Param("user_id")String user_id, @Param("hashtag")String hashtag);
}
