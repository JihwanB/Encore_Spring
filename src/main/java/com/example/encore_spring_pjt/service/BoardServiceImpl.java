package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("board")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    /*
    @Autowired
    private BoardMapper boardMapper;
     */

    private final BoardMapper boardMapper;

    @Transactional
    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> BoardService saveBoard : " + boardMapper);
        boardMapper.save(params);

        return params.getIdx();
    }

    @Transactional
    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        System.out.println("debug >>>> BoardService findBoard ");
        boardMapper.updateViewCnt(params);
        return Optional.ofNullable(boardMapper.findByIdx(params));
    }

    @Transactional
    @Override
    public Integer updateBoard(BoardRequest params) {
        System.out.println("debug >>>> BoardService updateBoard ");
        boardMapper.updateByIdx(params);

        return params.getIdx();
    }

    @Transactional
    @Override
    public Integer deleteBoard(BoardRequest params) {
        System.out.println("debug >>>> BoardService deleteBoard ");
        boardMapper.deleteByIdx(params);

        return params.getIdx();
    }

/*
    @Override
    public List<BoardResponse> listBoard() {
        System.out.println("debug >>>> BoardService listBoard ");

        return boardMapper.findAll(params);
    }

    @Override
    public Integer cntBoard() {
        System.out.println("debug >>>> BoardService cntBoard ");

        return boardMapper.count(params);
    }
 */

    @Override
    public List<BoardResponse> listBoard(PageDTO params) {
        System.out.println("debug >>>> BoardService listBoard ");
        System.out.println("debug >>> service params , " + params);
        return boardMapper.findAll(params);
    }

    @Override
    public Integer cntBoard(PageDTO params) {
        System.out.println("debug >>>> BoardService cntBoard ");

        return boardMapper.count(params);
    }

    @Transactional
    @Override
    public Optional<BoardResponse> findBoardNoIncrement(BoardRequest params) {
        System.out.println("debug >>>> BoardService findBoardNotCnt ");
        return Optional.ofNullable(boardMapper.findByIdx(params));
    }

}


