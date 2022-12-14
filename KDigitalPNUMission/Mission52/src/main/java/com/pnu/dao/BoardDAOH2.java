package com.pnu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pnu.domain.BoardVO;

@Repository
public class BoardDAOH2 implements BoardDAO {
	//잘못 입력해서 데이터가 없는 예외의 경우 처리할 방법 생각
	
//	private Connection con = null;
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	
//	public BoardDAOH2() {
//		try {
//			Class.forName("org.h2.Driver");
//			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mvcboard", "sa", "");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BoardDAOH2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> getBoards() {
		String sql = "select * from board";
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		try {
			List<BoardVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
			map.put("data", list);
		} catch (Exception e) {
			map.put("data", null);
		}
		return map;
	}
	
	public Map<String, Object> getBoard(int seq) {
		String sql = String.format("select * from board where seq = %d", seq);
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		try {
			BoardVO bv = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
			map.put("data", bv);
		} catch (Exception e) {
			map.put("data", null);
		}
		return map;
	}
	
	public Map<String, Object> addBoard(BoardVO boardVO) {
		String sql = "insert into board(seq, title, writer, content, cnt) values(?,?,?,?,?)";
		Map<String, Object> map = new HashMap<>();
		int nextSeq;
		try {
			nextSeq = jdbcTemplate.queryForObject("select max(seq) from board", Integer.class)+1;
		} catch (Exception e) {
			map.put("data", null);
			map.put("sql", sql);
			return map;
		}
		
		sql = String.format("insert into board(seq, title, writer, content, cnt) values(%d,'%s','%s','%s',%d)",
				nextSeq, boardVO.getTitle(), boardVO.getWriter(), boardVO.getContent(), 0);
		map.put("sql", sql);
		try {
			if (jdbcTemplate.update(sql) != 0) {
				Map<String, Object> Gmap = getBoard(nextSeq);
				map.put("data", Gmap.get("data"));
			} else {
				map.put("data", null);
			}
			
			//postman 쿼리스트링에 seq를 주지 않아도 알아서 nextSeq값을 입력해줌
		} catch (Exception e) {
			map.put("data", null);
		}
		return map;
	}
	
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		String sql = String.format("update board set title = '%s', writer = '%s', content = '%s' where seq = %d", 
				boardVO.getTitle(), boardVO.getWriter(), boardVO.getContent(), boardVO.getSeq());
		map.put("sql", sql);
		try {
			if(jdbcTemplate.update(sql) != 0) {
				Map<String, Object> Gmap = getBoard(boardVO.getSeq());
				map.put("data", Gmap.get("data"));
			} else {
				map.put("data", null);
			}
			
		} catch (Exception e) {
			map.put("data", null);
		} 
		return map;
	}
	
	public Map<String, Object> deleteBoard(int seq) {
		Map<String, Object> map = new HashMap<>();
		String sql = String.format("delete from board where seq = %d", seq);
		map.put("sql", sql);
		try {
			Map<String, Object> gmap = getBoard(seq);
			if (gmap.get("data")!= null && jdbcTemplate.update(sql) != 0) {
				map.put("data", gmap.get("data"));
			} else {
				map.put("data", null);
			}
//			if (jdbcTemplate.update(sql) != 0) {
//				Map<String, Object> gmap = getBoard(seq);
//				//이렇게 하면 지워지고 난 다음 데이터를 getBoard함수로 찾는 꼴이된다.
//				map.put("data", gmap.get("data"));
//			}
		} catch (Exception e) {
			map.put("data", null);
		} 
		return map;
	}
}
