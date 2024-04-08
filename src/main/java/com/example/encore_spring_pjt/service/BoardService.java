package com.example.encore_spring_pjt.service;

import java.util.List;
import java.util.Optional;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.domain.CommentEntity;

public interface BoardService {
	public Integer saveBoard(BoardRequest params); //사용자 키인한 값 서비스로 넘김- 서비스는 dao로 넘김

	//public BoardResponse findBoard(BoardRequest params);
	public Optional<BoardResponse> findBoard(BoardRequest params);

	// 조회수 중복방지 메서드 추가
	public Optional<BoardResponse> findBoardNotView(BoardRequest params);

	public void findBoardUpCnt(BoardRequest params);

	public Integer updateBoard(BoardRequest params);

	public Integer deleteBoard(BoardRequest params);

	//페이지 처리로  인자가 변경
	//public List<BoardResponse> listBoard();
	//public Integer cntBoard();

	public PageResponse<BoardResponse> listBoard(PageDTO params);

	public Integer cntBoard(PageDTO params);

	// comment
	List<CommentEntity> findBoardComment(BoardRequest params);

	List<CommentEntity> commentSave(CommentEntity params);

	List<CommentEntity> commentDel(CommentEntity params);

}