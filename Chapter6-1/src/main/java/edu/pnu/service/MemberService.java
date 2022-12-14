package edu.pnu.service;

import edu.pnu.domain.Member;

public interface MemberService {

	Member getMember(Member member);
	
	Member join(Member member);
	
	Member update(Member member);
	
	void delete(Member member);

}