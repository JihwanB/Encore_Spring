package com.example.encore_spring_pjt.mapper;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 encore_board_tbl 과 CRUD 작업을 위한 추상메서드 선언
 Mapper 는 기존의 DAO 와 동일한 작업을 진행하는 것
 */
@Mapper
public interface BoardMapper {
    // insert
    void save(BoardRequest params);

    // select (single finder)
    BoardResponse findByIdx(BoardRequest params);

    // update (title, content, writer)
    void updateByIdx(BoardRequest params);

    // 레코드의 건수를 count
    int count();

    // delete (idx)
    void deleteByIdx(BoardRequest params);

    // select (multi finder)
    List<BoardResponse> findAll();

    // 조회수를 증가시키는 메서드 추가
    void updateViewCnt(BoardRequest params);

}
