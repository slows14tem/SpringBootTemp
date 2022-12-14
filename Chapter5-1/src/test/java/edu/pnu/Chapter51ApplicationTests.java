package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistance.BoardRepository;
import edu.pnu.persistance.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter51ApplicationTests {
	
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;

	@Test
	public void contextLoads() {
		Member m1 = new Member();
		m1.setId("member1");
		m1.setPassword("member111");
		m1.setName("둘리");
		m1.setRole("User");
		//memberRepo.save(m1);
		
		Member m2 = new Member();
		m2.setId("member2");
		m2.setPassword("member222");
		m2.setName("도우너");
		m2.setRole("Admin");
		//memberRepo.save(m2);
		
		for(int i=1;i<=3;i++) {
			Board b1 = new Board();
			b1.setMember(m1);
			b1.setTitle("둘리가 등록한 게시글 " + i);
			b1.setContent("둘리가 등록한 게시글 내용 " + i);
			b1.setCreateDate(new Date());
			b1.setCnt(0L);
			//boardRepo.save(b1);
		}
		memberRepo.save(m1);
		
		for(int i=1;i<=3;i++) {
			Board b2 =  new Board();
			b2.setMember(m2);
			b2.setTitle("도우너가 등록한 게시글 "+i);
			b2.setContent("도우너가 등록한 게시글 내용"+i);
			b2.setCreateDate(new Date());
			b2.setCnt(0L);
			//boardRepo.save(b2);
		}
		memberRepo.save(m2);
	}
	
	//@Test
	public void newTest() {	//입력된 board의 member 필드의 값은 입력된 이후에도 다음과 같은 과정으로 변경가능하다.
		Board board = boardRepo.findById(1L).get();	//seq 1번 게시물을 저장( member1이 입력) 
		Member member = memberRepo.findById("member2").get();	//member2 아이디를 가진 객체 저장
		board.setMember(member);	//board에 저장된 1번 게시물의 member를 set을 사용해서 변경
		boardRepo.save(board);
		
	}
	
	//@Test
	public void ManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("["+board.getSeq()+"번 게시글 정보]");
		System.out.println(board.toString());
	}
	
	//@Test
	public void TowWayMApping() {
		Member member = memberRepo.findById("member1").get();
		System.out.println("======================");
		System.out.println(member.getName()+"가(이) 저장한 게시글 목록");
		System.out.println("======================");
		List<Board> list = member.getBoardList();
		for(Board board : list) {
			System.out.println(board.toString());
		}
	}
	
	//@Test
	public void CascadeDelete() {
		memberRepo.deleteById("member2");
	}
}
