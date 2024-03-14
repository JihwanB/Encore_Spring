package com.example.encore_spring_pjt.ctrl.user.service;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserResponse loginService(UserRequest request) {
        System.out.println("debug >>>> service loginService ");
        return userMapper.loginRow(request);
    }

}
