package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.ctrl.board.util.Pagination;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        boardMapper.save(params);

        return params.getIdx();
    }

    @Transactional
    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        boardMapper.updateViewCnt(params);
        return Optional.ofNullable(boardMapper.findByIdx(params));
    }

    @Transactional
    @Override
    public Integer updateBoard(BoardRequest params) {
        boardMapper.updateByIdx(params);

        return params.getIdx();
    }

    @Transactional
    @Override
    public Integer deleteBoard(BoardRequest params) {
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
    // public List<BoardResponse> listBoard(PageDTO params) {}
    public PageResponse<BoardResponse> listBoard(PageDTO params) {
        int recordCnt = boardMapper.count(params);
        if (recordCnt <= 0) {
            return new PageResponse<>(Collections.emptyList(), null);
        }
        // Pagination 객체를 이용해서 계산을 하기 위해서는 params 객체를 넘겨줘야 함
        Pagination pagination = new Pagination(recordCnt, params);
        params.setPagination(pagination);
        List<BoardResponse> list = boardMapper.findAll(params);
        return new PageResponse<>(list, pagination);
    }

    @Override
    public Integer cntBoard(PageDTO params) {
        return boardMapper.count(params);
    }

    @Transactional
    @Override
    public Optional<BoardResponse> findBoardNoIncrement(BoardRequest params) {
        return Optional.ofNullable(boardMapper.findByIdx(params));
    }

}


