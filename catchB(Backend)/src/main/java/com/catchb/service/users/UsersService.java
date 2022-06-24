package com.catchb.service.users;

import com.catchb.domain.users.Users;
import com.catchb.domain.users.UsersRepository;
import com.catchb.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    @Transactional
    public UsersResponseDto save(UsersSaveRequestDto requestDto){
        return new UsersResponseDto(usersRepository.save(requestDto.toEntity()));
    }

    public UsersResponseDto findById(Long user_num){
        Users entity = usersRepository.findById(user_num)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾지 못함. user_id =" + user_num));

        return new UsersResponseDto(entity);
    }

    public List<UsersResponseDto> findByUserid(String user_id){
        return usersRepository.findByUserid(user_id)
                .stream().map(UsersResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<UsersResponseDto> getUsers() {
        return usersRepository.findAll()
                .stream().map(UsersResponseDto::new)
                .collect(Collectors.toList());
    }
    //LoginUser
    public UsersResultDto LoginUser(String id, String pw){
        String user_pw = usersRepository.findPw(id);
        String result = "";
        if (user_pw==null) {
            result = "0"; //일치하는 아이디가 없습니다
            return new UsersResultDto(result);
        }
        if (pw.equals(user_pw)) {
            result = "1"; //로그인 성공
        }
        else {
            result = "2"; //비밀번호가 틀립니다
        }
        return new UsersResultDto(result);
    }

    //CheckUser   // findByUserid
    public UserCheckDto CheckUser(String id){
        String user_id = usersRepository.findUserId(id);
        String result = "";
        if (user_id == null){
            result = "0"; // 중복되는 아이디가 없음. 회원가입 가능한 아이디
            return new UserCheckDto(result);
        }
        else{
            result = "1"; // 중복되는 아이디가 있습니다.회원가입 불가능한 아이디
            return new UserCheckDto(result);
        }
    }

    //Userinfo
    public UserinfoDto Userinfo(String id){
        String user_nickname = usersRepository.findNn(id);
        int user_credit = usersRepository.findCd(id);
        return new UserinfoDto(id, user_nickname, user_credit);
    }

    //힌트 사용(20Credit 차감)
    @Transactional
    public void useHint(String user_id){
        usersRepository.useHint(user_id);
    }

    //정답처리(Credit 적립)
    @Transactional
    public void addCredit(int image_credit, String user_id){
        usersRepository.addCredit(image_credit, user_id);
    }

    //기프티콘 구매
    @Transactional
    public void usegift(int giftcredit, String user_id){
        usersRepository.usegift(giftcredit, user_id);
    }

}

