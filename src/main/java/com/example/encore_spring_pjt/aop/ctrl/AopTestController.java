package com.example.encore_spring_pjt.aop.ctrl;

import com.example.encore_spring_pjt.aop.domain.DataRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopTestController {

    @GetMapping("/test01")
    public ResponseEntity<Void> test01() {
        // System.out.println("비즈니스 로직 수행 전 로깅"); // common concern
        // 비즈니스 로직 수행 (Core concern)
        System.out.println("ctrl test01() core concern");
        // System.out.println("비즈니스 로직 수행 후 로깅");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/test02/{msg}")
    public ResponseEntity<Void> test02(@PathVariable("msg") String msg) {
        // System.out.println("비즈니스 로직 수행 전 로깅");
        // 비즈니스 로직 수행 (Core concern)
        System.out.println("ctrl test02() core concern, " + msg);
        // System.out.println("비즈니스 로직 수행 후 로깅");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/test03")
    public ResponseEntity<DataRequest> test03(@RequestBody DataRequest param) {
        System.out.println("ctrl test03() core concern , ");
        return new ResponseEntity<>(param, HttpStatus.OK);
    }

    @GetMapping("/test04")
    public ResponseEntity<DataRequest> test04(@RequestBody DataRequest param) throws Exception {
        System.out.println("ctrl test04() core concern , ");
        throw new Exception("예외발생");
    }

}
