package com.catchb.web.controller.users;

import com.catchb.service.users.UsersService;
import com.catchb.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/users")
public class UsersAPIController {
    private final UsersService usersService;
    //회원 가입
    @PostMapping
    public UsersResponseDto save(@RequestBody UsersSaveRequestDto requestDto)
    {
        return usersService.save(requestDto);
    }
    //인덱스로 조회
    @GetMapping(value = "/{user_num}")
    public UsersResponseDto findById(@PathVariable Long user_num){
        return usersService.findById(user_num);
    }
    //사용자 ID로 조회
    @GetMapping(value = "/user_id/{user_id}")
    public List<UsersResponseDto> findByUserid(@PathVariable String user_id){
        return usersService.findByUserid(user_id);
    }
    //사용자에게 보여질 정보만 조회
    @GetMapping(value = "/user_info")
    public List<UsersResponseDto> findUser(){
        return usersService.getUsers();
    }

    //로그인 요청
    @RequestMapping(value= "/test", method = {RequestMethod.GET})
    public UsersResultDto LoginPage(@RequestParam(value="user_id") String userId, @RequestParam(value="user_pw") String userPw){

        return usersService.LoginUser(userId, userPw);
    }

    //회원가입시 아이디중복 확인 요청
    @RequestMapping(value = "/test2", method = {RequestMethod.GET})
    public UserCheckDto CheckPage(@RequestParam(value = "user_id") String userId){
        return usersService.CheckUser(userId);
    }
    //UserInfo
    @RequestMapping(value = "/userinfo", method = {RequestMethod.GET})
    public UserinfoDto UserInfo(@RequestParam(value="user_id") String user_id){
        return usersService.Userinfo(user_id);
    }
    //힌트 사용
    @PostMapping(value = "/usehint/{user_id}")
    public void useHint(@PathVariable String user_id){
        usersService.useHint(user_id);
    }

    //정답 처리(credit 적립)
    @PostMapping(value = "/addcredit/{image_credit}/{user_id}")
    public void addCredit(@PathVariable int image_credit, @PathVariable String user_id){
        usersService.addCredit(image_credit, user_id);
    }

    //기프티콘 구매
    @PostMapping(value = "/purchase/{giftcredit}/{user_id}")
    public void usegift(@PathVariable int giftcredit, @PathVariable String user_id){
        usersService.usegift(giftcredit, user_id);
    }
}