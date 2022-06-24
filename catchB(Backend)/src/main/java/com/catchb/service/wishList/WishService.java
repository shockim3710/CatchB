package com.catchb.service.wishList;

import com.catchb.domain.wishList.WishRepository;
import com.catchb.web.dto.wishList.WishRequestDto;
import com.catchb.web.dto.wishList.WishResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishService {
    private final WishRepository wishRepository;

    //찜 등록
    @Transactional
    public WishResponseDto save(WishRequestDto requestDto){
        return new WishResponseDto(wishRepository.save(requestDto.toEntity()));
    }

    // 찜 조회(사용자 ID와 찜할 이미지의 ID로 조회)
    public List<WishResponseDto> findByWishId(String user_id, Long image_id){
        return wishRepository.findByWish(user_id, image_id)
                .stream().map(WishResponseDto::new)
                .collect(Collectors.toList());
    }

    // 찜 삭제
    @Transactional
    public void deleteWish(String user_id, Long image_id){
        wishRepository.deleteWish(user_id, image_id);
    }

    // 사용자의 찜 목록 List로 조회
    public List<WishResponseDto> findByImageid(String user_id){
        return wishRepository.findByImageid(user_id)
                .stream().map(WishResponseDto::new)
                .collect(Collectors.toList());
    }
}
