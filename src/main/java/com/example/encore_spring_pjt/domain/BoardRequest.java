package com.example.encore_spring_pjt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardRequest {
    private Integer idx;
    private String title;
    private String content;
    private String writer;
    private boolean noticeYn;
    private boolean secretYn;
}
