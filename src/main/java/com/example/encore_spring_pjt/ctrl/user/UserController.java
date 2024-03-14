package com.example.encore_spring_pjt.ctrl.user;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user") // => http:// server ip : port/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login.hanwha") // => http:// server ip : port/user/login.hanwha
    public String login(UserRequest request, HttpSession session, RedirectAttributes attributes) {
        // public String login(String id, String pwd) {
        // public String login(@RequestParam(name = "id") String id,
        //                      @RequestParam(name = "pwd") String pwd) {

        System.out.println("debug UserController client path /user/login.hanwha");
        System.out.println("param value (ID) >>> " + request.getId());
        System.out.println("param value (PWD) >>> " + request.getPwd());

        UserResponse response = userService.loginService(request);
        System.out.println("debug >>> ctrl result , " + response);

        if (response != null) {
            session.setAttribute("loginUser", response);
            return "redirect:/board/list.hanwha";
        } else {
            attributes.addFlashAttribute("failMsg",
                    "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력하셨습니다. " +
                                "입력하신 내용을 다시 확인해주세요.");
            return "redirect:/index.hanwha";
        }
    }

    @GetMapping("/logout.hanwha")
    public String logout(HttpSession session) {
        System.out.println("debug UserController client path /user/logout.hanwha");
//        session.invalidate();
//        return "redirect:/";
        return null;
    }

}
