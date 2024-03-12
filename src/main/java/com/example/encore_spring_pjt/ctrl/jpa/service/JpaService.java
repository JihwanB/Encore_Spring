package com.example.encore_spring_pjt.ctrl.jpa.service;

import com.example.encore_spring_pjt.ctrl.jpa.dao.EncoreRepository;
import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class JpaService {
    // DAO(Interface)
    // JPA - Repository
    // Mybatis - Mapper
    // 의존성 주입으로 Repository 를 받아야 함
    @Autowired
    private EncoreRepository encoreRepository;

    public List<JpaEntity> findAll() {
        System.out.println("debug >>>> service findAll");
        // repository call
        /*
        for (JpaEntity entity : list) {
            System.out.println(entity);
        }
        */

        /*
        List<JpaEntity> list = new ArrayList<>(encoreRepository.findAll());
        list.forEach(System.out::println);
        */

        return encoreRepository.findAll();
    }

    public JpaEntity save(@RequestBody JpaEntity params) {
        System.out.println("debug >>>> service save");
        // JPA - save();
        return encoreRepository.save(params);
    }

    public void delete(Integer seq) {
        System.out.println("debug >>>> service delete, " + seq);
        encoreRepository.deleteById(seq);
    }

}