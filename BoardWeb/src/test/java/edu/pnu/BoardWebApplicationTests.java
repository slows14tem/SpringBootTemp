package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class BoardWebApplicationTests {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Test
	void contextLoads() {
		Member member1 = new Member();
		member1.setId("member");
		member1.setPassword(encoder.encode("member123"));
		member1.setName("둘리");
		member1.setRole(Role.ROLE_MEMBER);	
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("admin");
		member2.setPassword(encoder.encode("admin123"));
		member2.setName("도우너");
		member2.setRole(Role.ROLE_ADMIN);	
		member2.setEnabled(true);
		memberRepo.save(member2);

				
		for (int i = 1; i <= 13; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 게시글 " + i);
			board.setContent(member1.getName() + "가 등록한 게시글 " + i);
			boardRepo.save(board);
		}
				
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 게시글 " + i);
			board.setContent(member2.getName() + "가 등록한 게시글 " + i);
			boardRepo.save(board);
		}
		
		
	}

}
