package com.example.encore_spring_pjt.ctrl.board;

import com.example.encore_spring_pjt.domain.BoardEntity;
import com.example.encore_spring_pjt.service.PostServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post_jpa") // postman test uri : http:// ip:port/board/jpa
public class BoardJpaController {
    @Resource(name = "post")
    private PostServiceImpl service;

    // 전체조회
    // postman test uri : http:// ip:port/board/jpa/list
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardEntity>> list() {
        System.out.println("debug >>> ctrl client path /board/jpa/list");
        List<BoardEntity> list = service.listBoard();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{idx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<BoardEntity>> find(BoardEntity params) {
        Optional<BoardEntity> entity = service.findBoard(params);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping(name = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> count() {
        return new ResponseEntity<>(service.cntBoard(), HttpStatus.OK);
    }

    @PostMapping(name = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody BoardEntity params) {
        return new ResponseEntity<>(service.saveBoard(params), HttpStatus.OK);
    }
}