package com.example.encore_spring_pjt.service;

import com.example.encore_spring_pjt.ctrl.rest.dao.PostRepository;
import com.example.encore_spring_pjt.domain.BoardEntity;
import com.example.encore_spring_pjt.domain.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("post")
@RequiredArgsConstructor
public class PostServiceImpl {
    private final PostRepository postRepository;

    public Integer saveBoard(BoardEntity params) {
        System.out.println("debug >>>> PostService saveBoard : ");
        BoardEntity entity = postRepository.save(params);
        return entity.getIdx();
    }

    public Optional<BoardEntity> findBoard(BoardEntity params) {
        System.out.println("debug >>>> PostService findBoard : ");
        return postRepository.findById(params.getIdx());
    }

    public Integer updateBoard(BoardRequest params) {
        return null;
    }

    public Integer deleteBoard(BoardRequest params) {
        return null;
    }

    public List<BoardEntity> listBoard() {
        System.out.println("debug >>> postService listBoard >>>>> " + postRepository);
        return postRepository.findAll();
    }

    public Integer cntBoard() {
        System.out.println("debug >>>> PostService cntBoard : ");
        return Math.toIntExact(postRepository.count());
    }

}
