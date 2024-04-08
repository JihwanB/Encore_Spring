package com.example.encore_spring_pjt.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.ctrl.board.util.Pagination;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.domain.CommentEntity;
import com.example.encore_spring_pjt.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service("board")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	//service는 dao(mapper)를 가져!, 의존관계 주입 받는 중
	@Transactional
	@Override
	public Integer saveBoard(BoardRequest params) {
		System.out.println("debug >>> board service saveboard : " + boardMapper);
		boardMapper.save(params);
		return params.getIdx();
	}

	@Transactional
	@Override
	public Optional<BoardResponse> findBoard(BoardRequest params) {
		System.out.println("debug>>>>service findBoard");
		boardMapper.updateByCnt(params);
		Optional<BoardResponse> response = boardMapper.findByIdx(params);

		//comment add
		//mapper - sql(select * from encore_board_comment_tbl where id = #{};)
		//response.setCommentList();
		//return response;
		//List<CommentEntity> lst = boardMapper.findComment(params);
		//response.get().setCommentLst(lst);

		return response;

	}

	@Transactional
	@Override
	public void findBoardUpCnt(BoardRequest params) {
		System.out.println("debug >>> service findBoardNotCnt ");
		boardMapper.updateByCnt(params);
	}

	@Transactional
	@Override
	public Integer updateBoard(BoardRequest params) {
		System.out.println("debug >>> service updateBoard");
		boardMapper.updateByIdx(params);
		return params.getIdx();
	}

	@Transactional
	@Override
	public Integer deleteBoard(BoardRequest params) {
		System.out.println("debug >>> service deleteBoard");
		boardMapper.deleteByIdx(params);
		return params.getIdx();
	}

	/* 페이지 처리로 변경
	@Override
	public List<BoardResponse> listBoard() {
		System.out.println("debug >>> service impl - list");
		return boardMapper.findAll();
	}

	@Override
	public Integer cntBoard() {
		System.out.println("debug >>> service impl - cnt");
		return boardMapper.count();
	}
	*/
	@Override
	public PageResponse<BoardResponse> listBoard(PageDTO params) {
		//페이지 처리와 페이지네이션을 위해서는 전체 게시글 수가 필요
		int recordCnt = boardMapper.count(params);
		if (recordCnt <= 0) {
			return new PageResponse<>(Collections.emptyList(), null);
		}
		//Pagination 객체를 이용해서 계산을 하기 위해서는 params 객체 넘겨줘야한다.
		Pagination pagination = new Pagination(recordCnt, params);
		params.setPagination(pagination);
		List<BoardResponse> list = boardMapper.findAll(params);
		return new PageResponse<>(list, pagination);
	}

	/*
	@Override
	public Integer cntBoard() {

	   return boardMapper.count();
	}
	*/
	@Override
	public Integer cntBoard(PageDTO params) {
		System.out.println("debug >>> service cntBoard");
		return boardMapper.count(params);
	}

	//조회수 중복방지 메서드 추가
	@Override
	public Optional<BoardResponse> findBoardNotView(BoardRequest params) {
		System.out.println("debug >>> service findBoardNotView ");
		Optional<BoardResponse> response = boardMapper.findByIdx(params);
		return response;
	}

	@Override
	public List<CommentEntity> findBoardComment(BoardRequest params) {
		System.out.println("debug >>> service findBoardComment");
		return boardMapper.findComment(params);
	}

	@Override
	@Transactional
	public List<CommentEntity> commentSave(CommentEntity params) {
		System.out.println("debug >>> service commentSave");
		// insert - select
		boardMapper.commentSave(params);
		// 코드 재활용으로 매개변수 타입의 문제 발생
		BoardRequest board = BoardRequest.builder()
			.idx(params.getIdx())
			.build();

		List<CommentEntity> list = boardMapper.findComment(board);
		System.out.println("debug >>> service comment entity size , " + list.size());
		return list;
	}

	@Override
	@Transactional
	public List<CommentEntity> commentDel(CommentEntity params) {
		System.out.println("debug >>> service commentDel");
		// delete - select
		boardMapper.commentDel(params);
		// 코드 재활용으로 매개변수 타입의 문제 발생
		BoardRequest board = BoardRequest.builder()
			.idx(params.getIdx())
			.build();

		List<CommentEntity> list = boardMapper.findComment(board);
		System.out.println("debug >>> service comment entity size , " + list.size());
		return list;
	}
}
