package com.example.encore_spring_pjt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
    private String id;
    private String pwd;
}
