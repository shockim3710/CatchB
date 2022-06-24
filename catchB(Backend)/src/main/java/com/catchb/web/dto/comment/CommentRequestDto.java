package com.catchb.web.dto.comment;

import com.catchb.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String user_id;
    private String comment_des;
    private Long image_id;
    private int image_icon;

    @Builder
    public CommentRequestDto(String user_id, String comment_des, Long image_id, int image_icon) {
        this.user_id = user_id;
        this.comment_des = comment_des;
        this.image_id = image_id;
        this.image_icon = image_icon;
    }

    public Comment toEntity(){
        return Comment.builder()
                .user_id(user_id)
                .comment_des(comment_des)
                .image_id(image_id)
                .image_icon(image_icon)
                .build();
    }
}
