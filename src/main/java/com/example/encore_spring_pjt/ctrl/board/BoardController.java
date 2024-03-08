package com.example.encore_spring_pjt.ctrl.board;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board") // => http:// serverIP:port/board
public class BoardController {

    @Resource(name = "board")
    private BoardServiceImpl service;

    @RequestMapping("/list.hanwha") // http:// serverIP:port/board/list.hanwha
    public String list() {
        System.out.println("debug BoardController client path /board/list.hanwha");

        /*
         BoardServiceImpl listBoard() 메서드 호출하여 결과를 반환 받고
         반환받은 결과를 Model(request scope) 에 심고
         이 데이터를 forward 되는 페이지에서 출력
         */
        System.out.println("======= debug BoardController list() result ======");

        List<BoardResponse> list = service.listBoard();
        for (BoardResponse response : list) {
            System.out.println(response);
        }

        return "list";
    }

    // @RequestMapping("/view.hanwha/{idx}")
    // public String view(@PathVariable("idx") Integer idx) {
    @RequestMapping("/view.hanwha")
    public String view(BoardRequest params, Model model) {
        System.out.println("debug BoardController client path /board/view.hanwha");
        System.out.println("params value , " + params.getIdx());
        //System.out.println("path value , " + idx);
        /*
         view 로부터 키(idx) 값을 전달받고 객체로 바인딩 돼서 service 에 전달
         response 객체를 반환받고, 해당 response 객체를 model 에 심어서 뷰 페이지로 전달 과정
         */
//        params.setIdx(params.getIdx());
        BoardResponse response = service.findBoard(params);
        model.addAttribute("response", response);

        return "view";
    }

    @RequestMapping("/write.hanwha")
    public String writeForm() {
        System.out.println("debug BoardController client path /board/write.hanwha");

        return "write";
    }
}
