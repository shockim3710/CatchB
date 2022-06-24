package com.catchb.service.submit;

import com.catchb.domain.submit.Submit;
import com.catchb.domain.submit.SubmitRepository;
import com.catchb.web.dto.submit.SubmitResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubmitService {
    private final SubmitRepository submitRepository;
    private Submit submit;

    @Transactional
    public Submit save(String user_id, String submit_address, String file_name, String submit_check, String submit_credit, String hashtag){
        submit = new Submit(user_id, submit_address,file_name,submit_check, submit_credit,hashtag);
        return submitRepository.save(submit);
    }

    public List<SubmitResponseDto> findsubmit(){
        return submitRepository.findsubmit().stream()
                .map(SubmitResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void processSubmit(String user_id, String hashtag){
        submitRepository.processSubmit(user_id, hashtag);
    }
}
