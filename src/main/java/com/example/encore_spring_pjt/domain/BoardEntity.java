package com.example.encore_spring_pjt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "encore_board_tbl")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer idx;
    private String title;
    private String content;
    private String writer;

    @Column(name = "view_cnt")
    private Integer viewCnt;
    @Column(name = "notice_yn")
    private Boolean noticeYn;
    @Column(name = "secret_yn")
    private Boolean secretYn;
    @Column(name = "delete_yn")
    private Boolean deleteYn;
    @Column(name = "insert_time")
    private LocalDateTime insertTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
