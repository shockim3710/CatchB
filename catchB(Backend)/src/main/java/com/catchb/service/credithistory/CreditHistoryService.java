package com.catchb.service.credithistory;


import com.catchb.domain.credithistory.CreditHistoryRepository;
import com.catchb.web.dto.credithistory.CreditHistoryRequestDto;
import com.catchb.web.dto.credithistory.CreditHistoryResponseDto;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreditHistoryService {
    private final CreditHistoryRepository creditHistoryRepository;

    @Transactional
    public CreditHistoryResponseDto save(CreditHistoryRequestDto requestDto){
        return new CreditHistoryResponseDto(creditHistoryRepository.save(requestDto.toEntity()));
    }

    public List<CreditHistoryResponseDto> findcredithistory(String user_id){
        return creditHistoryRepository.findcredithistory(user_id).stream()
                .map(CreditHistoryResponseDto::new)
                .collect(Collectors.toList());
    }


}
