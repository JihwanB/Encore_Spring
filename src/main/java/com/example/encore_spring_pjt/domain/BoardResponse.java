package com.example.encore_spring_pjt.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardResponse {
    private Integer idx;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private boolean noticeYn;
    private boolean secretYn;
    private boolean deleteYn;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
}
