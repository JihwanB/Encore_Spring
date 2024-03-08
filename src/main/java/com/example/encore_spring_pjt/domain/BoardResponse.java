package com.example.encore_spring_pjt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
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
