package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Logs;
import edu.pnu.domain.Members;
import edu.pnu.persistence.LogRepository;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	
	
	private MemberRepository memRepo;
	private LogRepository logRepo;
	
	@Autowired
	public MemberService(MemberRepository memRepo, LogRepository logRepo) {
		this.memRepo = memRepo;
		this.logRepo = logRepo;
	}

	public List<Members> getMembers(){
		try {
			List<Members> list = memRepo.findAll();
			logRepo.save(new Logs("Get", "getMembers()", true));
			return list;
		} catch(Exception e) {
			logRepo.save(new Logs("Get", "getMembers()", false));
			System.out.println("false");
			return null;
		}
		
	}

	public Members getMember(String id) {

		Members m = memRepo.findById(id).get();
		if (m != null) logRepo.save(new Logs("Get", String.format("getMember(%s)", m), true));
		else logRepo.save(new Logs("Get", String.format("getMember(%s)", id), false));
		return m;
	
		
	}

	public Members addMember(Members member) {
		try {
			member.setRegidate(new Date());
			Members m = memRepo.save(member);
//			Members m = memRepo.save(new Members(
//					member.getId(),
//					member.getPass(),
//					member.getName(),
//					new Date()
//					));	//m이 이미 Members임으로 다시 getter로 받아와서 생성자를 쓰지 않아도 된다??
			logRepo.save(new Logs("Post", String.format("addMember(%s)", member), true));
			return m;
		} catch(Exception e) {
			logRepo.save(new Logs("Post", "addMember()", false));
			System.out.println("false");
			return null;
		}
		

	}

	public Members updateMember(Members member) {
		try {
			
//			Members m = memRepo.findById(member.getId()).get();
//			m.setPass(member.getPass());
//			m.setName(member.getName());
			member.setRegidate(new Date());
			Members m = memRepo.save(member);
			//save는 select를 먼저 해보고 없으면 insert, 있으면 update하는 메소드. 
			//그래서 위 주석 3줄이 필요 없는 과정이다.
			logRepo.save(new Logs("Put", String.format("updateMember(%s)", member), true));
			return m;
		} catch(Exception e) {
			logRepo.save(new Logs("Put", "updateMember()", false));
			System.out.println("false");
			return null;
		}
		
	}

	public Members deleteMember(String id) {
		try {
			Members m = memRepo.findById(id).get();
			memRepo.deleteById(id);
			//
			logRepo.save(new Logs("Delete", String.format("deleteMember(%s)", m), true));
			return m;
		} catch(Exception e) {
			logRepo.save(new Logs("Delete", "deleteMember()", false));
			System.out.println("false");
			return null;
		}
		
	}

}
