package com.example.encore_spring_pjt.ctrl.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ENCORE_JPA_TEST_TBL")
public class JpaEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer seq;
    @Column
    private String id;
    @Column
    private String pwd;
    @Column
    private String name;

}
