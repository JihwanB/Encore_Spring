package com.example.encore_spring_pjt;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EncoreSpringPjtApplicationTests {

    @Autowired
    private ApplicationContext context;

    // SqlSessionFactory DI
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Test
    public void testContext() {
        // Dependency Lookup : getBean();
        try {
            System.out.println("==============================================");
            System.out.println("debug >>>> ");
            System.out.println(context.getBean("encore"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void contextLoads() {
    }

}
