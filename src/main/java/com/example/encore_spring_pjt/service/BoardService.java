package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;

import java.util.List;

public interface BoardService {
    Integer saveBoard(BoardRequest params);

    BoardResponse findBoard(BoardRequest params);

    Integer updateBoard(BoardRequest params);

    Integer deleteBoard(BoardRequest params);

    List<BoardResponse> listBoard();

}
