package com.example.encore_spring_pjt.ctrl;

import com.example.encore_spring_pjt.domain.BoardResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/*
 http code : 200, 404(not found), 405(bad request)), etc.

 @RequestMapping(path, method)
 -- GetMapping(<a href="">)
 -- PostMapping(<form action="" method="get|post">)

 @RestController(json, asynchronous)
 @Controller (full browsing, REST API - @ResponseBody)
 */
@Controller
public class TestController {

    /*
     http://server_ip:port/
     return type : void, String, ModelAndView -> full browsing
     return type : DTO, List, Map, Set, ResponseEntity -> REST API 서비스
    */

    /*
    @RequestMapping("/")
    public ModelAndView index() {
        System.out.println(">>>>> TestController path / , callback function index()");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("msg", "hi~, JSP");
        return mv;
    }
     */

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println(">>>>> TestController path / , callback function index()");
        model.addAttribute("msg", "Welcome To SpringBoot with JSP");
        return "index";
    }

    @RequestMapping("/test")
    public void test(Model model) {
        System.out.println(">>>>> TestController path /test , callback function index()");
    }

    @GetMapping("/json")
    @ResponseBody
    public List<BoardResponse> json() {
        System.out.println(">>>>> TestController path /json, callback function() ");
        BoardResponse response = BoardResponse.builder()
                .title("json title")
                .content("json content")
                .writer("encore")
                .build();
        List<BoardResponse> list = new ArrayList<>();
        list.add(response);list.add(response);list.add(response);list.add(response);

        return list;
    }

}
