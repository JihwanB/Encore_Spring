package com.example.encore_spring_pjt.ctrl.board;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.domain.CommentEntity;
import com.example.encore_spring_pjt.service.BoardServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // http://serverip:port/board 하면 동작함
public class BoardController {

	private final BoardServiceImpl service;
	//UPPER: DI발생

	@RequestMapping("/list.hanwha")
	public String list(@ModelAttribute("params") PageDTO params, Model model) {

		PageResponse<BoardResponse> list = service.listBoard(params);
		model.addAttribute("lst", list);
		//boardServiceImpl - listBoard() 메서드를 호출하여 결과를 반환받고 반환받은 결과를 Model(request scope)에 심어(modelandview, model~)
		// -> 이 데이터를 forward되는 페이지에서 출력!
		return "listPage";
	}

	//주석처리 = pathvariable 방식, 주석처리 x = legacy 방식
	//@GetMapping("/view.hanwha/{idx}")

	// @GetMapping("/view.hanwha")
	// public String view(BoardRequest params, Model model){
	// //public String view(@PathVariable("idx") Integer idx){
	//     System.out.println("debug >>> boardcontroller client path/board/view.hanwha");
	//     System.out.println("param value : "+params.getIdx());

	//System.out.println("path value : "+idx);
	//Q2.
	//view로부터 키(idx)값을 전달받고 객체로 바인딩되서 service에 전달
	//response객체를 반환받고 해당 response 객체를 model에 심어서 view페이지로 전달하는 과정
	// Optional<BoardResponse> response = service.findBoard(params);
	// model.addAttribute("response",response.get());
	// return "view";

	//중복 방지 구현으로 커스터마이징..
	//쿠키를 이용한 web : request.getSession(), request.getcookies();
	//client에 저장되어야 하는 이유 : 자동 로그인기능이니까, 매번 로긴 되어야 해서 client pc 에 저장한 것임
	//setMaxAge(60*60*24*30)
	@GetMapping("/view.hanwha")
	public String view(BoardRequest params, Model model, HttpServletRequest request, HttpServletResponse response) {
		//public String view(@PathVariable("idx") Integer idx){
		System.out.println("debug >>> boardController client path/board/view.hanwha");
		System.out.println("param value : " + params.getIdx());
		//Optional<BoardResponse> responseEntity = service.findBoard(params);
		//model.addAttribute("response",responseEntity.get());

		//수정사항 발생 부분( after 자동로긴 기능 )

		Cookie[] cookies = request.getCookies();
		System.out.println("debug cookies length " + cookies.length);
		int visited = 0;

		for (Cookie cookie : cookies) {
			System.out.println("debug >>> cookie name , " + cookie.getName());

			if (cookie.getName().equals("visit")) {
				visited = 1;
				System.out.println("debug >>>> cookie exits visited");
				if (cookie.getValue().contains(params.getIdx() + "")) {
					System.out.println("debug >>> cookie value idx , " + params.getIdx());
					Optional<BoardResponse> result = service.findBoardNotView(params);
					///////////////////////////
					List<CommentEntity> lst = service.findBoardComment(params);
					System.out.println("debug >>>>>>>> comment size , " + lst.size());
					////////////////////////////////
					model.addAttribute("commentlist", lst);
					model.addAttribute("response", result.get());
				} else {
					//visit라는 이름으로 게시글에 들어있는 모든 값을 cookie값을 다루는 정책
					System.out.println("visit cookie exits but params idx not exits");
					cookie.setValue(params.getIdx() + "");
					response.addCookie(cookie);
					Optional<BoardResponse> result = service.findBoard(params);

					//////////////// comment add
					List<CommentEntity> lst = service.findBoardComment(params);
					System.out.println("debug >>> comment size , " + lst.size());
					////////////////
					model.addAttribute("commentlist", lst);
					model.addAttribute("response", result.get());
				}
			}
		}
		System.out.println("debug >>> after loop visited , " + visited);
		if (visited == 0) {
			Cookie c = new Cookie("visit", params.getIdx() + "");
			response.addCookie(c);
			Optional<BoardResponse> result = service.findBoard(params);
			///////////////////////////
			List<CommentEntity> lst = service.findBoardComment(params);
			System.out.println("debug >>>>>>>> comment size , " + lst.size());
			////////////////////////////////
			model.addAttribute("commentlist", lst);
			model.addAttribute("response", result.get());

		}
		return "view";
	}

	@GetMapping("/write.hanwha")
	public String writeForm() {
		System.out.println("debug boardController client path GET! /board/write.hanwha");
		return "write";
	}

	@PostMapping("/write.hanwha")
	public String write(BoardRequest params) {
		System.out.println("debug boardController client path POST! /board/write.hanwha");
		System.out.println("debug params : " + params);
		/*
		 * params로 넘겨받은 데이터를 service에게 전달하여 DB(Save)
		 * 입력된데이터의 기본키값을 반환받고 출력
		 * 화면이동 : list
		 */
		Integer idx = service.saveBoard(params);
		System.out.println("debug result : " + idx + "번 게시글 입력");
		return "redirect:/board/list.hanwha";
	}

	@GetMapping("/delete.hanwha")
	public String delete(BoardRequest params) {
		System.out.println("debug boardController client path GET! /board/delete.hanwha");
		System.out.println("debug params : " + params);
		Integer idx = service.deleteBoard(params);
		System.out.println("debug result : " + idx + "번 게시글 삭제하겠습니까?");
		return "redirect:/board/list.hanwha";
	}

	@PostMapping("/update.hanwha")
	public String update(BoardRequest params) {
		System.out.println("debug BoardController client path POST /board/update.hanwha");
		System.out.println("debug >>> params value " + params);
		Integer idx = service.updateBoard(params);
		System.out.println("debug result => " + idx + "번 게시글 수정");
		return "redirect:/list.hanwha";
	}

	//COMMENT save
	@GetMapping("/commentSave.hanwha")
	@ResponseBody
	public List<CommentEntity> commentSave(CommentEntity entity) {
		System.out.println("debug boardController client path GET /board/commentSave.hanwha");
		System.out.println("debug params , " + entity);
		List<CommentEntity> list = service.commentSave(entity);

		return list;
	}

	@PostMapping("/commentDel.hanwha")
	@ResponseBody
	public List<CommentEntity> commentDel(CommentEntity entity) {
		System.out.println("debug boardController client path GET /board/commentDel.hanwha");
		System.out.println("debug params , " + entity);
		List<CommentEntity> list = service.commentDel(entity);

		return list;
	}

}
