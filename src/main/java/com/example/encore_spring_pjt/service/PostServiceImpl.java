package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("post")
@RequiredArgsConstructor
public class PostServiceImpl implements BoardService{

    // @Autowired
    private final BoardMapper boardMapper;

    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> postService SaveBoard : " + boardMapper);
        return null;
    }

    @Override
    public BoardResponse findBoard(BoardRequest params) {
        return null;
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

}
