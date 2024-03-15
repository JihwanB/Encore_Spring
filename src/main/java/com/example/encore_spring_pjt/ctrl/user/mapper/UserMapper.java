package com.example.encore_spring_pjt.ctrl.user.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
@Mapper
public interface UserMapper {
    UserResponse loginRow(UserRequest params); // .xml과 연결되는 tag id=""
    void insertRow(UserRequest params);
    String getPwd(UserRequest params);
}