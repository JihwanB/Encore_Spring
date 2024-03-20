package com.example.encore_spring_pjt.ctrl.rest;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 HTTP 통신 client 가 웹 서버에게(WAS) 요청의 목적을 알리는 수단
 - GET, POST, PUT, DELETE (CRUD)
 --- GET(읽기 전용) : read
 --- POST(새로운 자원을 생성) : update, insert => 레코드의 수가 늘어나는 건 아니지만, 변경되는 데이터를 새로운 자원으로 봄
 --- PUT(새로운 자원을 생성) : insert, update
 --- DELETE(자원을 삭제) : delete
 - e.g., Http:// server_ip : port / board ? idx = 1

 HTTP 응답코드의 상태코드
 1XX(정보) : 요청이 성공했고, 서버는 프로세스를 계속 진행하겠다는 의미
 2XX(성공) : 요청이 성공했고, 서버는 해당 요청을 수용
 3XX(redirect 오류) : 요청이 제대로 처리되지 않아서, 재요청 작업이 필요
 4XX(client 오류) : 요청의 분법이 잘못되었거나, 요청을 처리할 수 없을 때, Mapper 와 sync 가 안맞을 때
 5XX(server 오류) : 요청에 대해서 실패(NullPointerException (= NPE)) - 대체로 개발자 실수

 RestController 통신을 위한 템플릿이 제공(RequestEntity, ResponseEntity)
 */
@RestController
@RequestMapping("/board_rest")
public class RestBoardController {
    // Dependency Injection
    @Resource(name = "board")
    private BoardService service;

    // 전체조회
    @GetMapping(value = "list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PageResponse<BoardResponse>> list(@RequestBody PageDTO params) {
        System.out.println("debug RestBoardController client path /board_rest/list");

        PageResponse<BoardResponse> list = service.listBoard(params);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 삭제 - PathVariable
    @DeleteMapping(value = "/delete/{idx}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("idx") Integer idx) {
        System.out.println("debug RestBoardController client path /board_rest/delete/idx");

        BoardRequest request = BoardRequest.builder()
                .idx(idx)
                .build();
        Integer result = service.deleteBoard(request);
        System.out.println("debug >>>>> " + result + "번 게시글 삭제완료");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 게시물 번호로 조회
    @GetMapping(value = "/view/{idx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<BoardResponse>> view(@PathVariable("idx") Integer idx) {
        System.out.println("debug RestBoardController client path /board_rest/view/idx");

        BoardRequest request = BoardRequest.builder()
                .idx(idx)
                .build();
        Optional<BoardResponse> response = service.findBoard(request);

        System.out.println("debug >>>> isPresent() " + response.isPresent());
        System.out.println("debug >>>> viewResult, " + response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    // 게시물 번호로 수정
    @PutMapping(value = "/update/{idx}/{title}/{content}/{writer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("idx") Integer idx,
                                       @PathVariable("title") String title,
                                       @PathVariable("content") String content,
                                       @PathVariable("writer") String writer) {
        System.out.println("debug RestBoardController client path /board_rest/update/idx");

        BoardRequest request = BoardRequest.builder()
                .idx(idx)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        Integer result = service.updateBoard(request);
        System.out.println("debug >>>>> " + result + "번 게시글 수정완료");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */

    // 객체 형식으로 테스트 진행
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(BoardRequest params) {
        System.out.println("debug RestBoardController client path /board_rest/update");
        System.out.println("debug >>> params, " + params);

        Integer result = service.updateBoard(params);
        System.out.println("debug >>>>> " + result + "번 게시글 수정완료");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/write", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> write(BoardRequest params) {
        System.out.println("debug RestBoardController client path /board_rest/write");
        System.out.println("debug >>> params, " + params);

        Integer result = service.saveBoard(params);
        Map<String, String> map = new HashMap<>();
        map.put("msg", result + "번 게시글 입력완료");
        System.out.println("debug >>>>> " + result + "번 게시글 입력완료");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
