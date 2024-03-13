package com.example.encore_spring_pjt.ctrl.jpa.ctrl;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import com.example.encore_spring_pjt.ctrl.jpa.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        System.out.println("debug >>> service , " + service);
        // service method call
        List<JpaEntity> list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JpaEntity> list(@RequestBody JpaEntity params) {
        System.out.println("debug JpaTestController client path /jpa/save");
        System.out.println("debug >>> params , " + params);

        return new ResponseEntity<>(service.save(params), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("seq") Integer seq) {
        System.out.println("debug JpaTestController client path /jpa/delete/seq");
        service.delete(seq);
        System.out.println("debug >>> JpaTestController delete success");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 사용자 한명 정보 조회
    @GetMapping(value = "/find/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<JpaEntity>> find(@PathVariable("seq") Integer seq) {
        System.out.println("debug JpaTestController client path /jpa/find/seq");

        Optional<JpaEntity> entity = service.find(seq);
        System.out.println("debug >>> JpaTestController find result entity , " + entity);
        System.out.println("debug >>> JpaTestController find result entity.get() , " + entity.get());

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    // 기본키로 사용자정보 수정
    // raw 데이터 형식으로 데이터 전달되었다고 가정
    @PutMapping(value = "/update/{seq}/{id}/{pwd}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(JpaEntity entity) {
        System.out.println("debug JpaTestController client path /jpa/update/seq/id/pwd/name");
        System.out.println("debug >>> params, " + entity);
        service.update(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/findName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JpaEntity>> findName(@PathVariable("name") String name) {
        System.out.println("debug JpaTestController client path /jpa/findName/name");
        System.out.println("debug >>> params name, " + name);
        List<JpaEntity> list = service.findName(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/findNameLike/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JpaEntity>> findNameLike(@PathVariable("name") String name) {
        System.out.println("debug JpaTestController client path /jpa/findNameLike/name");
        System.out.println("debug >>> params name, " + name);
        List<JpaEntity> list = service.findNameLike("%"+name+"%");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
