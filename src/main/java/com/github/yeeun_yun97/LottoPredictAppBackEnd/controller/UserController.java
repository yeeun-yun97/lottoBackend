package com.github.yeeun_yun97.LottoPredictAppBackEnd.controller;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.*;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.security.JwtTokenUtil;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.security.JwtUserDetailsService;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value="/user")
public class UserController {

    private final UserService userService;
    private final JwtUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) throws SimpleException {
        RegisterResponse response= userService.register(registerRequest.getEmail(),registerRequest.getPassword(),registerRequest.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws SimpleException {
//        LoginResponse response = userService.login(loginRequest.getEmail(),loginRequest.getPassword());
        LoginResponse response = userDetailsService.authenticateByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/readUserInfo")
    public ResponseEntity<ReadUserInfoResponse> readUserInfo(@RequestBody @Valid ReadUserInfoRequest request) throws SimpleException{
        ReadUserInfoResponse response= userService.readUserInfo(request.getUser_id());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveNumber")
    public ResponseEntity<SimpleOkResponse> CreateNumber(@RequestBody CreateNumberRequest request)throws SimpleException {
        SimpleOkResponse response = userService.saveNumber(request.getNumbers(), request.getUser_id());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/readHistory")
    public ResponseEntity<Page<ReadSaveResponse>> ReadSaveHistory(@RequestBody ReadSaveRequest readSaveRequest) throws SimpleException {
        Page<ReadSaveResponse> response=userService.readSaveHistory(1,readSaveRequest.getPage_num(),readSaveRequest.getUser_id());
        return ResponseEntity.ok(response);
    }

        @PostMapping("/saveHistory")
    public ResponseEntity<SimpleOkResponse> updateSaveToHistory(@RequestBody UpdateSaveRequest updateSaveRequest) throws SimpleException{
            SimpleOkResponse response= this.userService.updateSaveToHistory(updateSaveRequest.getPredict_id(), updateSaveRequest.getUser_id());
        return ResponseEntity.ok(response);
    }
}
