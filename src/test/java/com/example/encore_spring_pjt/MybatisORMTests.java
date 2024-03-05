package com.example.encore_spring_pjt;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void ormFind() {
        System.out.println("debug finder >>>>>>>>>>>>>>>>>>>>>> ");
        BoardRequest request = new BoardRequest();
        request.setIdx(1);
        BoardResponse response = boardMapper.findByIdx(request);
        System.out.println("debug find result >>>>> ");
        System.out.println(response);
    }
}
