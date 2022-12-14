package com.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pnu.dao.BoardDAO;
import com.pnu.domain.BoardVO;
import com.pnu.logdao.LogDAO;

@Service
public class BoardService {
	

	private BoardDAO dao;
	private LogDAO log;
	private LogDAO logF;
	
	@Autowired
	public BoardService(BoardDAO dao, 
			@Qualifier("LogDAOH2") LogDAO log, 
			@Qualifier("LogDAOFile") LogDAO logF) {
		this.dao = dao;
		this.log = log;
		this.logF = logF;
	}
	
	public void addlog(String method, String sql, boolean success) {
		log.addlog(method, sql, success);
		logF.addlog(method, sql, success);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoardVO> getBoards(){
		Map<String, Object> map = dao.getBoards();
		System.out.println(map);
		List<BoardVO> list = (List<BoardVO>) map.get("data");
		if (list != null) addlog("Get", (String) map.get("sql"), true);
		else 			  addlog("Get", (String) map.get("sql"), false);
		return list;
	}
	
	public BoardVO getBoard(int seq) {
		Map<String, Object> map = dao.getBoard(seq);
		BoardVO boardVO = (BoardVO) map.get("data");
		if (boardVO != null) addlog("Get", (String) map.get("sql"), true);
		else 			  	 addlog("Get", (String) map.get("sql"), false);
		return boardVO;	
	}
	
	public BoardVO addBoard(BoardVO boardVO) {
		Map<String, Object> map = dao.addBoard(boardVO);
		BoardVO addboard = (BoardVO) map.get("data");
		if (addboard != null) addlog("Post", (String) map.get("sql"), true);
		else 			  	  addlog("Post", (String) map.get("sql"), false);
		return addboard;
	}
	
	public BoardVO updateBoard(BoardVO boardVO) {
		Map<String, Object> map = dao.updateBoard(boardVO);
		BoardVO upboard = (BoardVO) map.get("data");
		if (upboard != null) addlog("Put", (String) map.get("sql"), true);
		else 			  	 addlog("Put", (String) map.get("sql"), false);
		return upboard;
	}
	
	public BoardVO deleteBoard(int seq) {
		Map<String, Object> map = dao.deleteBoard(seq);
		BoardVO delboard = (BoardVO) map.get("data");
		if (delboard != null) addlog("Delete", (String) map.get("sql"), true);
		else 			  	  addlog("Delete", (String) map.get("sql"), false);
		return delboard;
	}

}
