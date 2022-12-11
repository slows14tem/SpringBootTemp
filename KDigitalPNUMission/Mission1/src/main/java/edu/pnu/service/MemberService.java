package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {

	private List<MemberVO> list;
	
	//리스트 데이터 생성
	public MemberService() {
		list = new ArrayList<>();
		for (int i = 1 ; i <= 5 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	
	public List<MemberVO> getMembers() {
		return list;
	}

	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO member) {
		member.setId(list.size() + 1);
		//입력되지 않은 정보는 쿼리스트링으로 입력
		//8080/member?pass=~~&name=~~
		member.setRegidate(new Date());
		list.add(member);
		return member;
	}

	public MemberVO updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}

	public MemberVO deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return m;
			}
		}
		return null;
	}
}
