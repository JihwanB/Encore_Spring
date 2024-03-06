package com.example.encore_spring_pjt;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// DI - Autowired, Resource
@SpringBootTest
public class BoardServiceTests {


    // @Autowired
    @Resource(name = "board")
    private BoardService service;

    @Test
    public void ServiceSaveTest() {
        System.out.println("debug Test -> ServiceSaveTest() ");

        BoardRequest request = new BoardRequest();
        request.setTitle("service 수업");
        request.setContent("service를 이용한 mapper 연결");
        request.setWriter("encore");
        request.setNoticeYn(true);
        request.setSecretYn(true);

        Integer idx = service.saveBoard(request);
        System.out.println("입력한 데이터의 키 값을 출력 : " + idx);
    }

    @Test
    public void ServiceFindTest() {
        System.out.println("debug Test -> ServiceFindTest() ");

        BoardRequest request = new BoardRequest();
        request.setIdx(1);
        BoardResponse response = service.findBoard(request);
        System.out.println("\"findBoard result\"\n" + response);
    }

    @Test
    public void ServiceUpdateTest() {
        System.out.println("debug Test -> ServiceUpdateTest() ");

        BoardRequest request = new BoardRequest();
        request.setIdx(1);
        request.setTitle("Title");
        request.setContent("Content");
        request.setWriter("Writer");
        Integer idx = service.updateBoard(request);
        System.out.println("\"updateBoard result\"\n" + "idx : " + idx);
    }

    @Test
    public void ServiceDeleteTest() {
        System.out.println("debug Test -> ServiceDeleteTest() ");

        BoardRequest request = new BoardRequest();
        request.setIdx(4);
        Integer idx = service.deleteBoard(request);
        System.out.println("\"deleteBoard result\"\n" + "idx : " + idx);
    }

    @Test
    public void ServiceListBoardTest() {
        System.out.println("debug Test -> ServiceListBoardTest() ");

        List<BoardResponse> responses = service.listBoard();
        System.out.println("\"listBoard result\"");
        for (BoardResponse response : responses) {
            System.out.println(response);
        }
    }
}
