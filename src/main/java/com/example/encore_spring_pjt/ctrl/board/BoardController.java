package com.example.encore_spring_pjt.ctrl.board;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    //    @Autowired
//    BoardServiceImpl boardServiceImpl;
    @Resource(name = "board")
    private BoardService service;

//    @RequestMapping("/list.hanwha")
//    @ResponseBody
//    public List<BoardResponse> list(){
//        System.out.println("debug BoardController client path /board/list.hanwha");
//
//        //BoardServiceImpl listBoard() 메서드 호출하여 결과를 반환 받고
//        //반환받은 결과를 Model(requestScope) 에 심고 이 데이터를
//        //forward 되는 페이지에서 출력
//        List<BoardResponse> lst = service.listBoard();
//        System.out.println(lst);
//        return lst;
//    }

    @RequestMapping("/list.hanwha") // http:// serverip : port / board / list.hanwha
    public String list(PageDTO params, Model model) {
        //BoardServiceImpl listBoard() 메서드 호출하여 결과를 반환 받고
        //반환받은 결과를 Model(requestScope) 에 심고 이 데이터를
        //forward 되는 페이지에서 출력
        List<BoardResponse> lst = service.listBoard(params);
        model.addAttribute("lst", lst);
        return "list";
    }

    // @GetMapping("/view.hanwha/{idx}")
    // public void view(@PathVariable("idx") Integer idx){
    // 조회수 중복방지 구현으로 커스터마이징
    // 쿠키를 이용한 WEB : request.getSession(), request.getCookie()
    // setMaxAge(60 * 60 * 24 * 30) 초단위. 60초 * 60분 * 24시간 * 30일 = 1달
    @GetMapping("/view.hanwha")
    public String view(BoardRequest params,
                       Model model,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        //public void view(BoardRequest params){
        System.out.println("debug BoardController client path /board/view.hanwha");
        System.out.println("params value , " + params.getIdx());
        /*
        view로부터 키(idx) 값을 전달받고 객체로 바인딩되서 service 에 전달
        response 객체를 반환받고
        해당 response 객체 Model에 심어서 viewPage로 전달 과정
         */

        // 쿠키가 있는지 판단하는 코드
        Cookie[] cookies = request.getCookies();
        System.out.println("debug >>> cookies length : " + cookies.length);
        int visited = 0;

        for (Cookie cookie : cookies) {
            System.out.println("debug >>> cookie name . " + cookie.getName());
            if (cookie.getName().equals("visit")) {
                visited = 1;
                System.out.println("debug >>> cookie exists visited");
                if (cookie.getValue().contains(params.getIdx().toString())) {
                    System.out.println("debug >>>> cookie value idx , " + params.getIdx());
                    Optional<BoardResponse> result = service.findBoardNoIncrement(params);
                    model.addAttribute("response", result.get());
                } else {
                    System.out.println("debug >>> visit cookie exits but params idx not exits");
                    cookie.setValue(params.getIdx().toString());
                    response.addCookie(cookie);
                    Optional<BoardResponse> result = service.findBoard(params);
                    model.addAttribute("response", result.get());
                }
            }
        }
        System.out.println("debug >>> after loop visited , " + visited);
        if (visited == 0) {
            Cookie c = new Cookie("visit", params.getIdx().toString());
            response.addCookie(c);
            Optional<BoardResponse> result = service.findBoard(params);
            model.addAttribute("response", result.get());
        }

        /*
        if (cookies != null) {
            // 쿠키정보가 있을 때
            service.findBoardNotCnt(params);
        } else {
            // 쿠키정보가 없을 때
            System.out.println("debug >>> cookie is not present");
            Cookie cookie = new Cookie("view_upcnt", params.getIdx().toString());
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            Optional<BoardResponse> responseEntity = service.findBoard(params);
            model.addAttribute("response", responseEntity.get());
        }
         */

        return "view";
    }

    @GetMapping("/write.hanwha")
    public String writeForm() {
        System.out.println("debug BoardController client path /board/write.hanwha");
        return "write";
    }

    @PostMapping("/write.hanwha")
    public String writeForm(BoardRequest params) {
        System.out.println(params);
        Integer idx = service.saveBoard(params);
        System.out.println("debug >>>>>>>>>>>> " + idx + " 번 글이 삭제되었습니다.");
        return "redirect:/board/list.hanwha";
    }

    @GetMapping("/delete.hanwha")
    public String deleteForm(BoardRequest params) {
        service.deleteBoard(params);
        return "redirect:/board/list.hanwha";
    }

    @GetMapping("/update.hanwha")
    public String editForm(BoardRequest params, Model model) {
        if (params.getIdx() != null) {
            Optional<BoardResponse> editParams = service.findBoard(params);
            model.addAttribute("response", editParams.get());
            System.out.println(editParams);
        }
        return "write";
    }

    @PostMapping("/update.hanwha")
    public String updateForm(BoardRequest params) {
        service.updateBoard(params);
        return "redirect:/board/view.hanwha?idx=" + params.getIdx();
    }

}
