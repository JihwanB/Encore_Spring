package com.example.encore_spring_pjt.ctrl.jpa.dao;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EncoreRepository extends JpaRepository<JpaEntity, Integer> {
    // JPQL
    @Query("SELECT U FROM ENCORE_JPA_TEST_TBL U WHERE U.seq=:seq")
    JpaEntity findByCustomerId(@Param("seq") Integer seq);

    // findByColumnName (property)
    // findById(), findByPwd(), findByNameLike()
    // findByIdAndPwd(String id, String pwd) : where id = ? and pwd = ?
    // findByIdOrPwd(String id, String pwd) : where id = ? or pwd = ?
    // findByIdOrderByAsc()

    // JPA 의 Update 는 find(select) 후에 save(update)
    // 사용자정의 메서드를 정의해서 편하게 작업할 수 있음 - JPQL
    // @Query - Select
    // @Query - DML (Insert, Update, Delete) with @Modifying
    // @Param - data binding
    @Transactional
    @Modifying
    @Query("UPDATE ENCORE_JPA_TEST_TBL U SET U.id=:id, U.pwd=:pwd, U.name=:name WHERE U.seq=:seq")
    void updateEntity(@Param("seq") Integer seq, @Param("id") String id
                    , @Param("pwd") String pwd, @Param("name") String name);

    List<JpaEntity> findByName(String name);

    // @Query("SELECT U FROM ENCORE_JPA_TEST_TBL U WHERE U.name LIKE %:name%")
    List<JpaEntity> findByNameLike(String name);

}
