package com.example.encore_spring_pjt.ctrl.rest.dao;

import com.example.encore_spring_pjt.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<BoardEntity, Integer> {
    //    @Query("SELECT U FROM encore_board_tbl U WHERE U.deleteYn=false")
//    List<BoardEntity> findALL();
//    @Query("SELECT COUNT(U) FROM encore_board_tbl U WHERE U.deleteYn=false")
//    Integer countByDeleteYnFalse();
}
