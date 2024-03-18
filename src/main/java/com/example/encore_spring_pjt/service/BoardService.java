package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Integer saveBoard(BoardRequest params);

    Optional<BoardResponse> findBoard(BoardRequest params);

    Integer updateBoard(BoardRequest params);

    Integer deleteBoard(BoardRequest params);

    // 페이지 처리로 매개변수타입 추가
    // List<BoardResponse> listBoard();
    // Integer cntBoard();
    List<BoardResponse> listBoard(PageDTO params);
    Integer cntBoard(PageDTO params);


    Optional<BoardResponse> findBoardNoIncrement(BoardRequest params);
}
