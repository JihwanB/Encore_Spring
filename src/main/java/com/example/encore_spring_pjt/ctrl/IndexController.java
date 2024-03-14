package com.example.encore_spring_pjt.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /*
    @RequestMapping("/index.hanwha")
    public String index(HttpSession session) {
        System.out.println("debug >>> IndexController client path : / ");

        UserResponse response = (UserResponse)session.getAttribute("loginUser");
        System.out.println("debug >>> session Y/N , " + response);
        if (response == null) {
            return "index";
        } else {
            return "redirect:/board/list.hanwha";
        }
    }
    */

    // 세션유무 다른 처리 방식으로
    // Spring Framework 에서 권장하는 방식
    /*
    @RequestMapping("/index.hanwha")
    public String index(@SessionAttribute(required = false, name = "loginUser") UserResponse response, Model model) {
        System.out.println("debug >>> IndexController client path : / ");
        System.out.println("debug >>> session Y/N , " + response);
        if (response == null) {
            return "index";
        } else {
            return "redirect:/board/list.hanwha";
        }
    }
    */

    @RequestMapping("/index.hanwha")
    public String index() {
        System.out.println("debug >>> IndexController client path : / ");
        return "index";
    }

}
