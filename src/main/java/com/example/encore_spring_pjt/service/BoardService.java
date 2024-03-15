package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Integer saveBoard(BoardRequest params);

    Optional<BoardResponse> findBoard(BoardRequest params);

    Integer updateBoard(BoardRequest params);

    Integer deleteBoard(BoardRequest params);

    List<BoardResponse> listBoard();

    Integer cntBoard();

    Optional<BoardResponse> findBoardNoIncrement(BoardRequest params);
}
