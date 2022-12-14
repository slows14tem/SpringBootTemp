package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//Model == 스프링의 interface
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i=1;i<=10;i++) {
//			Board board = new Board();
//			board.setSeq((long)i);
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardList.add(board);
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
//	
//	@RequestMapping("/getBoardList1")
//	public ModelAndView getBoardList1() {
//		//ModelAndView 모델과 뷰를 같이 저장할 수 있는 객체
//		ModelAndView mv = new ModelAndView();
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i=1;i<=10;i++) {
//			Board board = new Board();
//			board.setSeq((long)i);
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardList.add(board);
//		}
//		mv.addObject("boardList", boardList);
//		mv.setViewName("getBoardList");
//		return mv;
//	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		//게시글 목록 가져옴
		model.addAttribute("boardList", boardList);
		//검색결과 저장
		return "getBoardList";
		//문자열로 리턴해서 getBoardList.jsp로 이동
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	// redirect는 페이지 전환 주체가 클라이언트, forward는 서버

}
