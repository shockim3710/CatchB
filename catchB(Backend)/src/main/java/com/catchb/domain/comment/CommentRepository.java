package com.catchb.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM comment WHERE image_id=:image_id")
    List<Comment> findByImageId(@Param("image_id") Long image_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM comment where user_id=:user_id and comment_des=:comment_des")
    void deleteComment(@Param("user_id") String user_id, @Param("comment_des") String comment_des);
}
