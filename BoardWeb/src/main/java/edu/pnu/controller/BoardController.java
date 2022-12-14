package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.pnu.config.SecurityUser;
import edu.pnu.domain.Board;
import edu.pnu.domain.Search;
import edu.pnu.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(Board board,  @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Search search, @RequestParam(required=false, defaultValue = "0", value = "page") int page) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");

		Page<Board> boardList = boardService.getBoardList(search, page);
		int totalPage = boardList.getTotalPages();
		model.addAttribute("search", search);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalPage", totalPage);
		return "board/getBoardList";
	}
	
//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model, Board board) {
//		List<Board> boardList = boardService.getBoardList(board);
//		model.addAttribute("boardList", boardList);
//		return "board/getBoardList";
//	}

	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		
		return "board/getBoard";
	}
	
	
}
