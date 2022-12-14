package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Board {
	@Id@GeneratedValue
	private Long seq;
	private String title;
	@Column(updatable = false)
//	private String writer;
	private String content;
	@Column(insertable = false, updatable = false, columnDefinition = "date default current_timestamp")
	private Date createDate;
	@Column(insertable = false, updatable = true, columnDefinition = "number default 0")
	private Long cnt;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false)
	private Member member;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	

	public Board(Long seq, String title, String content, Date createDate, Long cnt, Member member) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.cnt = cnt;
		this.member = member;
	}

	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", cnt=" + cnt + ", member=" + member + "]";
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}

}
