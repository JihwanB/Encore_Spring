package com.example.encore_spring_pjt.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentEntity {
	private Integer id;
	private String writer;
	private String content;
	private LocalDateTime regdate;
	private Integer idx;
}
