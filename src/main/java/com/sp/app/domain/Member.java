package com.sp.app.domain;

public class Member {
	private long memberIdx;			// 회원번호(시퀀스, 기본키)
	private int membership;			// 회원권한
	
	private String userId;			// 회원 ID(기본키)
	private String nickName;		// 회원 닉네임	
	private String userPwd;			// 회원 패스워드
	private String email;			// 이메일
	private String email1;
	private String email2;
	private int enabled;			// 로그인 가능 여부
	private String regDate;			// 가입일자
	private String updateMDate;		// 회원 기본 정보 수정일
	
	private long mManageNum;		// 관리 번호
	private String stateDate;		// 상태 변경
	private long stateCode;			// 상태 코드
	private String memo;			// 상태내용
	
	public long getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(long memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
		this.membership = membership;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUpdateMDate() {
		return updateMDate;
	}
	public void setUpdateMDate(String updateMDate) {
		this.updateMDate = updateMDate;
	}
	public long getmManageNum() {
		return mManageNum;
	}
	public void setmManageNum(long mManageNum) {
		this.mManageNum = mManageNum;
	}
	public String getStateDate() {
		return stateDate;
	}
	public void setStateDate(String stateDate) {
		this.stateDate = stateDate;
	}
	public long getStateCode() {
		return stateCode;
	}
	public void setStateCode(long stateCode) {
		this.stateCode = stateCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
