package com.catchb.domain.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

    // 아이디 sql 구문
    @Query(nativeQuery = true, value = "SELECT * FROM Users WHERE user_id =:id")
    List<Users> findByUserid(@Param("id") String user_id);

    //아이디 String sql 구문
    @Query(nativeQuery = true, value = "SELECT user_id FROM Users WHERE user_id=:id")
    String findUserId(@Param("id") String user_id);

    // 패스워드 sql 구문
    @Query(nativeQuery = true, value = "SELECT user_pw FROM Users WHERE user_id =:id")
    String findPw(@Param("id") String user_id);

    // 닉네임 sql 구문
    @Query(nativeQuery = true, value ="SELECT user_nickname FROM Users WHERE user_id =:id")
    String findNn(@Param("id") String user_id);

    // 크레딧 sql 구문
    @Query(nativeQuery = true, value ="SELECT user_credit FROM Users WHERE user_id =:id")
    int findCd(@Param("id") String user_id);

    //힌트사용 sql 구문
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Users SET user_credit = user_credit - 20 WHERE user_id = :user_id")
    void useHint(@Param("user_id")String user_id);

    //문제 제출 정답 처리 sql구문
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Users SET user_credit = user_credit + :image_credit WHERE user_id = :user_id")
    void addCredit(@Param("image_credit")int image_credit, @Param("user_id")String user_id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Users SET user_credit = user_credit - :giftcredit WHERE user_id = :user_id")
    void usegift(@Param("giftcredit")int giftcredit, @Param("user_id")String user_id);
}
