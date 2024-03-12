package com.example.encore_spring_pjt.ctrl.jpa.ctrl;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import com.example.encore_spring_pjt.ctrl.jpa.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jpa")
@RequiredArgsConstructor
public class JpaTestController {

    // 의존성 주입 (DI) - service
    private final JpaService service;

    // 사용자의 모든 정보 조회
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JpaEntity>> list() {
        System.out.println("debug JpaTestController client path /jpa/list");
        System.out.println("debug >>> service, " + service);
        // service method call
        List<JpaEntity> list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JpaEntity> list(@RequestBody JpaEntity params) {
        System.out.println("debug JpaTestController client path /jpa/save");
        System.out.println("debug >>> params, " + params);
        return new ResponseEntity<>(service.save(params), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("seq") Integer seq) {
        System.out.println("debug JpaTestController client path /jpa/delete");
        System.out.println("debug >>> seq, " + seq);
        service.delete(seq);
        System.out.println("debug >>> JpaTestController delete success");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
