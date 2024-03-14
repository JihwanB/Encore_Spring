package com.example.encore_spring_pjt.ctrl.user.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String pwd;
    private String name;
    private Integer point;
}
