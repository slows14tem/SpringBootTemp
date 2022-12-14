package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Member {
	
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
	//일대다(1:N)의 관계, 한명의 멤버가 여러개의 게시물을 작성할 수 있어서 onetomany/list 변수 사용
	//mappedby : 연관관계의 주인이 '아님'을 선언(외래키가 있는 곳이 주인으로 선정)
	//fetchType EAGER : 연관엔티티를 동시에 조회
	//			Lazy : 연관엔티티를 ㅅ실재로 사용할 때 조회
	//cascade : 영속성 전이, 엔티티를 영속, 삭제상태로 만들 때 연관 엔티티도 같이 처리할 경우 영속성 전이를 사용하면 관리가 편함 
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String password, String name, String role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
	

}
