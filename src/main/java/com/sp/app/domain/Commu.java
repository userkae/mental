package com.sp.app.domain;

public class Commu {

	private long commuNum;				// 게시물 번호(기본키)
	private String commuSubject;		// 게시물 제목
	private String commuContent;		// 게시물 내용
	private int hitCount;				// 조회수
	private String commuRegDate;		// 게시물 등록일
	private String nickName;			// 닉네임
	private long memberIdx;				// 회원번호(외래키)
	
	public long getCommuNum() {
		return commuNum;
	}
	public void setCommuNum(long commuNum) {
		this.commuNum = commuNum;
	}
	public String getCommuSubject() {
		return commuSubject;
	}
	public void setCommuSubject(String commuSubject) {
		this.commuSubject = commuSubject;
	}
	public String getCommuContent() {
		return commuContent;
	}
	public void setCommuContent(String commuContent) {
		this.commuContent = commuContent;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getCommuRegDate() {
		return commuRegDate;
	}
	public void setCommuRegDate(String commuRegDate) {
		this.commuRegDate = commuRegDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(long memberIdx) {
		this.memberIdx = memberIdx;
	}
}
