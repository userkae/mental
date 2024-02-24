package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.domain.Member;

public interface MemberManageService {
	
	public int dataCount(Map<String, Object> map);			// 회원 총 인원수
	public List<Member> memberManageList(Map<String, Object> map) throws Exception;
	public List<Member> stateCodeList() throws Exception;
	public Member updateUserInfo(long memberIdx) throws Exception;
	public void updateMember(Map<String, Object> map) throws Exception;
	public void updatemManage(Map<String, Object> map) throws Exception;
	public void updateEnabled1(long memberIdx) throws Exception;
	public void updateEnabled0(long memberIdx) throws Exception;
}
