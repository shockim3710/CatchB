package com.catchb.service.comment;

import com.catchb.domain.comment.CommentRepository;
import com.catchb.web.dto.comment.CommentRequestDto;
import com.catchb.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save(CommentRequestDto requestDto){
        return new CommentResponseDto(commentRepository.save(requestDto.toEntity()));
    }

    public List<CommentResponseDto> findByImageId(Long image_id){
        return commentRepository.findByImageId(image_id).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(String user_id, String comment_des){
        commentRepository.deleteComment(user_id, comment_des);
    }
}
