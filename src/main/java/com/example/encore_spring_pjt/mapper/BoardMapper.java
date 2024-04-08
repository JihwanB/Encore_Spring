package com.example.encore_spring_pjt.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.domain.CommentEntity;

/*
 encore_board_tbl 과crud작업을 위한 추상메서드 선언
 Mapper는 기존의 DAO와 동일한 작업을 진행하는 것.
 */

//@mapper은 객체생성인데 orm으로 관리될 수 있는 애인거임.
@Mapper
public interface BoardMapper {
	//insert
	public void save(BoardRequest params);

	//select (single finder)
	public Optional<BoardResponse> findByIdx(BoardRequest paramRequest);

	//update (title, content, writer) - idx
	public void updateByIdx(BoardRequest params);
	//레코드의 건수를 count

	//public int        count();
	public int count(PageDTO params);

	//delete - idx
	public void deleteByIdx(BoardRequest params);

	// select (multi finder)
	//public List<BoardResponse> findAll();
	public List<BoardResponse> findAll(PageDTO params);

	// 조회수를 증가시키는 메서드 추가
	public void updateByCnt(BoardRequest params);

	//comment add
	List<CommentEntity> findComment(BoardRequest params);

	void commentSave(CommentEntity params);

	void commentDel(CommentEntity params);

}
