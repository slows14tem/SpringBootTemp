package edu.pnu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		//EntityManager 생성. 괄호 안에 persistence-unit name 입력해서 영속성을 구분
		//Persistence.createEntityManagerFactory(<persistence-unit name="Chapter04">)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		//팩토리는 어플리케이션에서 하나만 만듬
		EntityManager em = emf.createEntityManager();
		//데이터베이스 연동을 처리
		//하나의 비지니스 프로세스 마다 하나씩 만들고 다쓰면 닫음(트랜젝션당 메니져 한개씩)
		
		//Transaction 생성
		//JPA가 테이블에 등록,수정,삭제작업을 처리하기 위해서는 해당작업이 반드시 트랜잭션 안에서 수행되어야함
		EntityTransaction tx = em.getTransaction();
		//=====입력=====
		try {
			//transaction 시작
			tx.begin();
			for (int i=0; i<10; i++) {
				Board board = new Board();
				board.setTitle("JAP 제목"+i);
				board.setWriter("관리자"+i);
				board.setContent("JPA 글 등록 잘 되네요."+i);
				board.setCreateDate(new Date());
				board.setCnt(0L);
				//글 등록 - 엔티티를 영속상태로 만드는 것
				em.persist(board);
			}
			//transaction commit == transaction 종료
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
