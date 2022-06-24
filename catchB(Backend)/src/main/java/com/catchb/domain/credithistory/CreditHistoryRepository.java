package com.catchb.domain.credithistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CreditHistoryRepository extends JpaRepository<Credit_History, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM credit_history WHERE user_id =:id")
    List<Credit_History> findcredithistory(@Param("id") String user_id);
}
