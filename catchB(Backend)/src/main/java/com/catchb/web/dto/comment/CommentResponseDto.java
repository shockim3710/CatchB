package com.catchb.web.dto.comment;

import com.catchb.domain.comment.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long comment_id;
    private String user_id;
    private String comment_des;
    private Long image_id;
    private int image_icon;

    public CommentResponseDto(Comment entity){
        this.comment_id = entity.getComment_id();
        this.user_id = entity.getUser_id();
        this.comment_des = entity.getComment_des();
        this.image_id = entity.getImage_id();
        this.image_icon = entity.getImage_icon();
    }

}
