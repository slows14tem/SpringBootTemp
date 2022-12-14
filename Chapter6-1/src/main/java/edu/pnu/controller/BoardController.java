package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
//세션 상태정보 저장
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	//@SessionAttributes("member") 어노테이션이 없을때 각 메소드에서 받아가야할 @ModelAttribute("member") Member member가
	//없기때문에 새로 만들어 주는 것.
	//@SessionAttributes("member") 이 없으면 LoginController에서 만들어진 session과 관련없는 새로운 세션이 만들어 지는 것.
	//그래서 로그인은 되지만 세션은 null값으로 저장되어 있음
	
//	@RequestMapping("/getBoardList")
//	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
//		if(member.getId()==null) {
//			
//			return "redirect:login";
//		}
//		List<Board> boardList = boardService.getBoardList(board);
//
//		model.addAttribute("boardList", boardList);
//		//request에 boardList를 저장?
//		return "getBoardList";
//	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		Member member = (Member)model.asMap().get("member");
		//세션 "member"
		if(member == null||member.getId()==null) {
			return "redirect:login";
		}
		model.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView(Member members) {
		//세션 "member"는 logincontroller에서 Member의 형식으로 생성되어서
		//members라고 변수이름을 정해도 세션에서 같은 Member형식으로 판단하고 세션정보를 입력해주는듯
		//@ModelAttribute("member") 어노테이션이 없어도 세션이 사용 가능
		//더 코드가 깔끔한 방법은 그냥 member변수를 없애고 다음을 사용하는 것
		//Member member = (Member)model.asMap().get("member");
		if(members.getId()==null) {	//현재 로그인이 되어있는지 확인
			return "redirect:login";	
		}
		return "insertBoard";
	}
	
	@PostMapping("insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) {	//현재 로그인이 되어있는지 확인
			return "redirect:login";	
		}
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard( Board board, Model model) {
		Member member = (Member)model.asMap().get("member");
		if(member == null || member.getId()==null) { //현재 로그인이 되어있는지 확인
			return "redirect:login";	
		}
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) {	//현재 로그인이 되어있는지 확인
			return "redirect:login";	
		}
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@DeleteMapping("deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) { //현재 로그인이 되어있는지 확인
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}

}
