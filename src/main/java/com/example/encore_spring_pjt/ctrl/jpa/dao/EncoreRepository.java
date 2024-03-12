package com.example.encore_spring_pjt.ctrl.jpa.dao;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncoreRepository extends JpaRepository<JpaEntity, Integer> {
    // findByXXX();
//    List<JpaEntity> findAll();
}
