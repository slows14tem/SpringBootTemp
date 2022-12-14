package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Members;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService ms;
	
	@GetMapping("/member")
	public List<Members> getMembers(){
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public Members getMember(@PathVariable String id) {
		return ms.getMember(id);
	}
	
	@PostMapping("/member")
	public Members addMember(Members member) {
		return ms.addMember(member);
	}
	
	@PutMapping("/member")
	public Members updateMember(Members member) {
		return ms.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public Members deleteMember(@PathVariable String id) {
		return ms.deleteMember(id);
	}
	

}
