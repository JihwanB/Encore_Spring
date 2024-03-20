package com.example.encore_spring_pjt.ctrl.user;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user") // http://serverIP:port/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login.hanwha") // 강사님 기준 가장 이상적이라고 생각하는 방법
    public String login(UserRequest params, HttpSession session, RedirectAttributes attr) {
        System.out.println("debug UserController client path /user/login.hanwha");
        System.out.println("param value >>> id : " + params.getId() + " pwd : " + params.getPwd());
        String encodePwd = userService.getPwd(params); // 서버의 인코딩 된 암호 불러오기
        if (passwordEncoder.matches(params.getPwd(), encodePwd)) {// 입력된 값과 인코딩 되어 저장되어있던 값 비교
            params.setPwd(encodePwd); // 입력이 유효하다면 params 의 값을 갱신하고 서비스로 보냄
        }
        UserResponse response = userService.loginService(params);
        if (response != null) {
            session.setAttribute("loginUser", response);
            return "redirect:/board/list.hanwha";
        } else {
            attr.addFlashAttribute("failMsg", "아이디 또는 비밀번호를 잘못 입력하셨습니다");
        }
        return "redirect:/";
    }

//    @PostMapping("/login.hanwha") // 나머지 두 방식, RequestParam 이용 or 이름을 맞춰주고 바로 사용하는 법
//    public String login(@RequestParam(name = "id") String id,
//                        @RequestParam(name= "pwd") String pwd) { //String id, String pwd
//        System.out.println("debug UserController client path /user/login.hanwha");
//        System.out.println("param value >>> id : " + id + " pwd : " + pwd);
//        return null;
//    }

    @GetMapping("/logout.hanwha")
    public String logout(HttpSession session) {
        System.out.println("debug UserController client path /user/logout.hanwha");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/join.hanwha")
    public String joinForm() {
        System.out.println("debug UserController client path /user/join.hanwha");
        return "join";
    }

    @PostMapping("/join.hanwha")
    public String join(@Valid UserRequest params,
                       BindingResult bindingResult,
                       Model model /*,
                       RedirectAttributes attr*/) {
        System.out.println("debug UserController client POST path /user/join.hanwha");
        /*
        실습 구현
        step 01) 유효성 체크 검증을 통과하지 못하면 에러메시지를 join.jsp 보내서 출력
        step 02) 유효성 체크 후 검증을 통과하면 serice registr();
        step 03) UserMapper - insertRow() - UserMapper.xml<insert id="">
        step 04) 화면분기는 /
         */

        if (bindingResult.hasErrors()) {
            //attr.addFlashAttribute("errorMsg", "유효성 체크 검증 실패");
            List<ObjectError> list = bindingResult.getAllErrors();
            // Map<String, String> map = new HashMap<>();
            for (ObjectError ob : list) {
                FieldError field = (FieldError) ob;
                String msg = ob.getDefaultMessage();
                System.out.println(">>>>>>>>>>>>>");
                System.out.println(field + " :::: " + msg);
                System.out.println(">>>>>>>>>>>>>");
                model.addAttribute(field.getField(), msg);
            }
            return "join";
        }
        System.out.println("debug >>>>> 유효성 검증 통과");

        // 비밀번호 암호화
        System.out.println("debug >>>> passwordEncoder , " + passwordEncoder);
        String encodePwd = passwordEncoder.encode(params.getPwd());
        System.out.println("encodePwd " + encodePwd);
        params.setPwd(encodePwd);
        userService.registerService(params);
        return "redirect:/";
    }

    // 인증 이후 카카오 쪽에서 호출하는 메서드
    @GetMapping("/kakao_login.hanwha")
    public String kakaoLogin(@RequestParam(value = "code") String code, Model model, HttpSession session) {
        System.out.println("debug >>> oauth kakao code, " + code);
        String accessToken = userService.getAccessToken(code);

        Map<String, Object> users = userService.getUserInfo(accessToken);
        System.out.println("debug >>> ctrl result , " + users.get("name"));

        UserResponse response = new UserResponse();
        response.setName((String) (users.get("name")));
        session.setAttribute("loginUser", response);
        session.setAttribute("access_token", accessToken);
        return "redirect:/board/list.hanwha";
    }

    @GetMapping("/kakao_logout.hanwha")
    public String kakaoLogout(HttpSession session) {
        System.out.println("debug >>> ctrl kakaoLogout");
        // 1. session 으로부터 access token 을 얻어와서 카카오서버에 전달 삭제
        String token = (String) session.getAttribute("access_token");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        String result = userService.logout(map);
        System.out.println("debug >>> logout result , " + result);
        session.invalidate();
        return "redirect:/";
    }

}