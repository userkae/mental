package com.sp.app.service;

import com.sp.app.domain.Member;

public interface MemberService {
	public Member findById(String userId);
	public int findByMembership(long memberIdx);
	
	public void insertMember(Member dto) throws Exception;
	public Member findByInfo(long memberIdx) throws Exception;
	public void updateInfo(Member dto) throws Exception;
}
