package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("post")
@RequiredArgsConstructor
public class PostServiceImpl implements BoardService{

    // @Autowired
    private final BoardMapper boardMapper;

    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> PostService saveBoard : " + boardMapper);
        return null;
    }

    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        return Optional.empty();
    }

    @Override
    public Integer updateBoard(BoardRequest params) {
        return null;
    }

    @Override
    public Integer deleteBoard(BoardRequest params) {
        return null;
    }

    @Override
    public List<BoardResponse> listBoard() {
        return null;
    }

    @Override
    public Integer cntBoard() {
        return null;
    }

}
