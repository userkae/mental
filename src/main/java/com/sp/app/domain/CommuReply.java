package com.sp.app.domain;

public class CommuReply {
	private long replyNum;				// 댓글 번호
	private String reContent;			// 댓글 내용
	private String reRegDate;			// 댓글 등록일
	private long commuNum;				// 커뮤니티 게시물 번호
	private long memberIdx;				// 댓글 작성한 회원 번호
	private String nickName;			// 댓글 닉네임
	private String profile;				
	
	public long getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(long replyNum) {
		this.replyNum = replyNum;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReRegDate() {
		return reRegDate;
	}
	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}
	public long getCommuNum() {
		return commuNum;
	}
	public void setCommuNum(long commuNum) {
		this.commuNum = commuNum;
	}
	public long getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(long memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
