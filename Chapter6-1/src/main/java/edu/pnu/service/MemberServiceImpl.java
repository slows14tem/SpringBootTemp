package edu.pnu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) return findMember.get();
		else return null;
	}
	
	public Member join(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) return findMember.get();
		else {
			member.setRole("User");
			memberRepo.save(member);
			return null;
		}
	}
	
	public Member update(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		if(findMember != null) {
			findMember.setPassword(member.getPassword());
			findMember.setName(member.getName());
			memberRepo.save(findMember);
			return findMember;
		} else return null;
	}
	
	public void delete(Member member) {
		memberRepo.deleteById(member.getId());
		
	}

}
