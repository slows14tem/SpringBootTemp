package edu.pnu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientRollback {
	
	public static void test(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			for (int i=0; i<10; i++) {
				Board board = new Board();
				board.setTitle("JAP 제목"+i);
				board.setWriter("관리자"+i);
				board.setContent("JPA 글 등록 잘 되네요."+i);
				board.setCreateDate(new Date());
				board.setCnt(0L);
				em.persist(board);
			}

			//일부러 에러 발생시킴(동일한 Seq 입력)
			//롤백이 실행되서 10번까지만 입력됨
			Board board = new Board();
			board.setSeq(1L);
			board.setTitle("JAP 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);
			
			
			//transaction commit
			tx.commit();
		} catch (Exception e) {
			System.out.println("RollBack");
			tx.rollback();
			//Seq가 20번까지 입력된 상황으로 롤백이 아니라
			//커밋 전에 오류가 나서 예외처리로 넘어와서 메인의 10번까지만 입력된 상황으로 돌아감
		} finally {
			em.close();

		}

	}

	public static void main(String[] args) {
		//EntityManager 생성. 괄호 안에 persistence-unit name 입력해서 영속성을 구분
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		//팩토리는 어플리케이션에서 하나만 만듬
		EntityManager em = emf.createEntityManager();
		//하나의 비지니스 프로세스 마다?? 하나씩 만들고 다쓰면 닫음(트랜젝션당 메니져 한개씩)
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
				//글 등록
				em.persist(board);
			}
			//transaction commit == transaction 종료?
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

		//롤백 테스트
		test(emf);
		emf.close();
		
	}

}
