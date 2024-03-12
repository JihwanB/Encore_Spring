package com.example.encore_spring_pjt.ctrl.valid;

import com.example.encore_spring_pjt.ctrl.valid.domain.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/valid")
public class RestValidController {

    // json{key:value, key:value, ...} 형식의 데이터가 전달이 되었을 때를 가정
    // BindingResult : 유효성 검증에 실패한 메시지를 모아서 관리하는 객체
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO params, BindingResult bindingResult) {
        System.out.println("debug RestValidController client path /valid/create");
        System.out.println("debug >>> params, " + params);

        /*
        if (params.getAge() > 200) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(params);
        }
        */
        if (bindingResult.hasErrors()) {
            System.out.println("contains an error message");
            // StringBuffer buffer = new StringBuffer();

            List<ObjectError> list = bindingResult.getAllErrors();
            Map<String, String> map = new HashMap<>();
            for (ObjectError objectError : list) {
                String msg = objectError.getDefaultMessage();
                FieldError field = (FieldError) objectError;
                // buffer.append("debug >>> ").append(field.getField()).append(" : ").append(msg).append("\n");
//                System.out.println("debug >>> " + field.getField() + "\t" + msg);
                map.put(field.getField(), msg);
            }
            // System.out.println(buffer);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            // return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("doesn't contain an error message");
        }

        return new ResponseEntity<>(params, HttpStatus.OK);
    }
}
