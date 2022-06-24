package com.catchb.web.controller.comment;

import com.catchb.service.comment.CommentService;
import com.catchb.web.dto.comment.CommentRequestDto;
import com.catchb.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/comment")
public class CommentAPIController {
    private final CommentService commentService;
    //댓글 등록

    @PostMapping
    public CommentResponseDto save(@RequestBody CommentRequestDto requestDto){
        return commentService.save(requestDto);
    }
    //댓글 조회
    @GetMapping(value = "/search/{image_id}")
    public List<CommentResponseDto> findByImageID(@PathVariable Long image_id) throws IOException{
        return commentService.findByImageId(image_id);
    }
    //댓글 삭제
    @DeleteMapping("/delete/{user_id}/{comment_des}")
    public void deleteComment(@PathVariable("user_id")String user_id, @PathVariable("comment_des")String comment_des){
        commentService.deleteComment(user_id, comment_des);
    }
}
