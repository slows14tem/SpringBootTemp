package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

//기존의 DAO와 같은 개념
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	//인터페이스에서 별다른 구현 없이 CRUD 및 페이징 기능 등 이용가능(스프링이 자동으로 구현 객체 생성)
	//EntityManagerFactory, EntityManager, Transaction 등 필요없어짐
	
	
	//쿼리 메소드
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingAndContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	List<Board> findByTitleContaining(String searchKeyword, Pageable paging);	//pageable = 페이징 기능
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(Long cnt, Long cnt2);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	
	//@Query 어노테이션
	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b "
			+ "where b.title like %:searchKeyword% "
			+ "order by b.seq desc")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKey);
	
	@Query("select b.seq, b.title, b.writer, b.createDate "
			+ "from Board b "
			+ "where b.title like %?1% "
			+ "order by b.seq desc")
	List<Object[]> queryAnnotationTest3(@Param("searchKeyword") String searchKeyword);
	
	@Query(value = "select seq, title, writer, create_Date "
			+ "from Board "
			+ "where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery=true)
	//문자열 접합 연산자 ||, concat으로 %?1% 를 만들어도 상관없다
	//nativeQuery=true  쿼리가 JPQL이 아닌 네이티브 쿼리문임을 알려주는 속성
	//create_Date...네이티브쿼리라서 컬럼명을 데이터베이스와 똑같이 줘야한다.(생성될 때 규칙에 의해 변경되기 때문)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	
	@Query("select b from Board b")
	List<Board> queryAnnotationTest5(Pageable paging);
	
}
