package edu.pnu;


import java.util.List;

//import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;
	
//	@Autowired
//	EntityManager em;

	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "JAP 제목3";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		
		Pageable paging = PageRequest.of(0, 10);
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
		System.out.println("검색결과");
		for(Board board:boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
//	public void testDynamicQuery1() {
//		JPAQueryFactory query = new JPAQueryFactory(em);
//		QBoard b = QBoard.board;
//		
//		List<Board> list = query.selectFrom(b).where(b.cnt.lt(50)).orderBy(b.seq.asc()).fetch();
//		
//		for (Board bb:list) {
//			System.out.println("--->" + bb);
//		}
//	}

}
