package com.example.encore_spring_pjt;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisORMTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void ormSave() {
        System.out.println("debug mapper >>>>>>>>>>>>>>>>>>>>>> " + boardMapper);
        BoardRequest request = new BoardRequest();
        request.setTitle("orm 수업");
        request.setContent("mybatis mapping");
        request.setWriter("encore");
        request.setNoticeYn(true);
        request.setSecretYn(true);
        boardMapper.save(request);
        System.out.println("debug >>>>>> save success ");
    }

    @Test
    public void ormFind() throws JsonProcessingException {
        System.out.println("debug finder >>>>>>>>>>>>>>>>>>>>>> ");
        BoardRequest request = new BoardRequest();
        request.setIdx(1);
        BoardResponse response = boardMapper.findByIdx(request);
        System.out.println("debug find result >>>>> ");
        System.out.println(response);
        System.out.println(">>>>>>>>>>>>>>>>>>> json ");
        String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(response);
        System.out.println(boardJson);
    }

    @Test
    public void ormUpdate() {
        System.out.println("debug update >>>>>>>>>>>>>>> ");
        BoardRequest request = new BoardRequest();
        request.setIdx(1);
        request.setTitle("제목 수정");
        request.setContent("변경된 내용");
        request.setWriter("jslim");
        boardMapper.updateByIdx(request);
        System.out.println(request);
        System.out.println("debug >>>>>>>>> update success ");
    }

    @Test
    public void ormCount() {
        System.out.println("debug count >>>>>>>>>>>>>>> ");
        System.out.println(boardMapper.count());
        System.out.println("debug >>>>>>>>>>>> count success ");
    }

    @Test
    public void ormDelete() {
        System.out.println("debug delete >>>>>>>>>>>>>> ");
        BoardRequest request = new BoardRequest();
        request.setIdx(2);
        boardMapper.deleteByIdx(request);
        System.out.println(request);
        System.out.println("debug >>>>>>>>>>>> delete success");
    }

    @Test
    public void ormFindAll() {
        System.out.println("debug findAll >>>>>>>>>>>>>>>>");
        List<BoardResponse> boardResponseList = boardMapper.findAll();
        for (BoardResponse response : boardResponseList) {
            System.out.println(response);
        }
        System.out.println("debug >>>>>>>>>>>>> findAll success ");
    }

}
