package com.example.encore_spring_pjt.ctrl.user;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") // => http:// server ip : port/user
public class UserController {

    @PostMapping("/login.hanwha") // => http:// server ip : port/user/login.hanwha
    public String login(UserRequest params) {
    // public String login(String id, String pwd) {
    // public String login(@RequestParam(name = "id") String id,
    //                      @RequestParam(name = "pwd") String pwd) {

        System.out.println("debug UserController client path /user/login.hanwha");
        System.out.println("param value (ID) >>> " + params.getId());
        System.out.println("param value (PWD) >>> " + params.getPwd());
        return "redirect:/board/list.hanwha";
    }

}
