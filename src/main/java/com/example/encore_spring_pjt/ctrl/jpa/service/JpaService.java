package com.example.encore_spring_pjt.ctrl.jpa.service;

import com.example.encore_spring_pjt.ctrl.jpa.dao.EncoreRepository;
import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
        System.out.println("debug >>>> service delete , " + seq);
        encoreRepository.deleteById(seq);
    }

    public Optional<JpaEntity> find(Integer seq) {
        System.out.println("debug >>>> service find , " + seq);
        //return encoreRepository.findById(seq);
        return Optional.ofNullable(encoreRepository.findByCustomerId(seq));
    }

    public void update(JpaEntity entity) {
        System.out.println("debug >>>> service update , " + entity);
        // JPA 의 Update : find 먼저 하고나서 save
        /*
        Optional<JpaEntity> data = encoreRepository.findById(entity.getSeq());
        System.out.println("debug >>> service update , " + data);
        if (data.isPresent()) {
            data.get().setSeq(entity.getSeq());
            data.get().setId(entity.getId());
            data.get().setPwd(entity.getPwd());
            data.get().setName(entity.getName());
            encoreRepository.save(data.get());
        }
        */
        encoreRepository.updateEntity(entity.getSeq(), entity.getId(), entity.getPwd(), entity.getName());
    }

    public List<JpaEntity> findName(String name) {
        System.out.println("debug >>>> service findName , " + name);
        return encoreRepository.findByName(name);
    }

    public List<JpaEntity> findNameLike(String name) {
        System.out.println("debug >>>> service findNameLike , " + name);
        return encoreRepository.findByNameLike(name);
    }
}