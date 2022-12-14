package com.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pnu.domain.BoardVO;
import com.pnu.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@GetMapping("/board")
	public List<BoardVO> getBoards(){
		return bs.getBoards();
		
	}
	
	@GetMapping("/board/{seq}")
	public BoardVO getBoard(@PathVariable Integer seq) {
		return bs.getBoard(seq);
		
	}
	
	@PostMapping("/board")
	public BoardVO addBoard(BoardVO boardVO) {
		return bs.addBoard(boardVO);
		
	}
	
	@PutMapping("/board")
	public BoardVO updateBoard(BoardVO boardVO) {
		return bs.updateBoard(boardVO);
		
	}
	
	@DeleteMapping("/board/{seq}")
	public BoardVO deleteBoard(@PathVariable Integer seq) {
		return bs.deleteBoard(seq);
		
	}
	

}
