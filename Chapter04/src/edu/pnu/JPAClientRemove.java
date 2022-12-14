package edu.pnu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientRemove {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		//=====입력=====
		try {
			tx.begin();
			//삭제할 게시글 조회
			//영속성 컨텍스트에 삭제할 엔티티가 없다면 예외가 발생 -> 삭제할 엔티티를 검색해서 영속성 컨텍스트에 등록해야함
			Board board1 = em.find(Board.class, 3L);
			//find메소드로 board1에 이미 seq 3L이 저장되어있는데 다시 setSeq로 3L로 바꿔줄 필요없음
			//게시글 삭제
			em.remove(board1);
			//transaction commit
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
