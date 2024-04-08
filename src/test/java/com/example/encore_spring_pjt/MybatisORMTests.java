package com.example.encore_spring_pjt;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MybatisORMTests {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void ormSave() {
		System.out.println("debug mapper >>>>>>>>>>>>>>>>>>>>>> " + boardMapper);

		BoardRequest request = BoardRequest.builder()
			.title("orm 수업")
			.content("mybatis mapping")
			.writer("encore")
			.noticeYn(true)
			.secretYn(true)
			.build();

		boardMapper.save(request);
		System.out.println("debug >>>>>> save success ");
	}

	@Test
	public void ormFind() throws JsonProcessingException {
		System.out.println("debug finder >>>>>>>>>>>>>>>>>>>>>> ");

		BoardRequest request = BoardRequest.builder()
			.idx(1)
			.build();

		Optional<BoardResponse> response = boardMapper.findByIdx(request);
		System.out.println("debug find result >>>>> ");
		System.out.println(response);

		System.out.println(">>>>>>>>>>>>>>>>>>> json ");
		String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(response);
		System.out.println(boardJson);
	}

	@Test
	public void ormUpdate() {
		System.out.println("debug update >>>>>>>>>>>>>>> ");

		BoardRequest request = BoardRequest.builder()
			.idx(1)
			.title("제목 수정")
			.content("변경된 내용")
			.writer("jslim")
			.build();

		boardMapper.updateByIdx(request);

		System.out.println(request);
		System.out.println("debug >>>>>>>>> update success ");
	}

	@Test
	public void ormCount(PageDTO params) {
		System.out.println("debug count >>>>>>>>>>>>>>> ");

		System.out.println(boardMapper.count(params));

		System.out.println("debug >>>>>>>>>>>> count success ");
	}

	@Test
	public void ormDelete() {
		System.out.println("debug delete >>>>>>>>>>>>>> ");

		BoardRequest request = BoardRequest.builder()
			.idx(2)
			.build();

		boardMapper.deleteByIdx(request);

		System.out.println(request);
		System.out.println("debug >>>>>>>>>>>> delete success");
	}

	@Test
	public void ormFindAll(PageDTO params) {
		System.out.println("debug findAll >>>>>>>>>>>>>>>>");

		List<BoardResponse> boardResponseList = boardMapper.findAll(params);
		for (BoardResponse response : boardResponseList) {
			System.out.println(response);
		}

		System.out.println("debug >>>>>>>>>>>>> findAll success ");
	}

}
