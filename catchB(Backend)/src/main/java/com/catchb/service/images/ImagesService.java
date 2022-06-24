package com.catchb.service.images;

import com.catchb.domain.images.Images;
import com.catchb.domain.images.ImagesRepository;
import com.catchb.web.dto.images.ImagesResponseDto;
import com.catchb.web.dto.images.ImagesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImagesService {
    private final ImagesRepository imagesRepository;
    private Images images;

    int count = 0;
    @Transactional
    public Images save(String image_address, String image_credit, String image_starttime, String image_endtime, String image_description, String image_route, String image_name, String image_hint){
        images = new Images(image_address, image_credit, image_starttime, image_endtime, image_description, image_route, image_name,image_hint);
        return imagesRepository.save(images);
    }
    @Transactional
    public Long save(ImagesSaveRequestDto requestDto) {
        return imagesRepository.save(requestDto.toEntity()).getImage_id();
    }


    public ImagesResponseDto findById (Long image_id){
        Images entity = imagesRepository.findById(image_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사진이 존재하지 않습니다. id =" + image_id));

        return new ImagesResponseDto(entity);
    }

    public List<ImagesResponseDto> findByAddress (String image_address){
        return imagesRepository.findByAddress(image_address)
                .stream().map(ImagesResponseDto::new)
                .collect(Collectors.toList());
    }
    public List<ImagesResponseDto> findByName(String image_name){
        return imagesRepository.findByName(image_name)
                .stream().map(ImagesResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ImagesResponseDto> findById2(Long image_id){
        return imagesRepository.finById2(image_id)
                .stream().map(ImagesResponseDto::new)
                .collect(Collectors.toList());
    }
}
