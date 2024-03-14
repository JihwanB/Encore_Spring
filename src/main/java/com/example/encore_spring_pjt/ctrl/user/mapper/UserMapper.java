package com.example.encore_spring_pjt.ctrl.user.mapper;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserResponse loginRow(UserRequest request);

}
