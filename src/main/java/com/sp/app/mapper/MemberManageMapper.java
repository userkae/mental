package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.Member;

@Mapper
public interface MemberManageMapper {
	
	public int dataCount(Map<String, Object> map);			// 회원 총 인원수
	// 회원 리스트 가져오기
	public List<Member> memberManageList(Map<String, Object> map) throws SQLException;
	
	// 상태코드 리스트에 담기
	public List<Member> stateCodeList() throws SQLException;
	
	// 회원 상세
	public Member updateUserInfo(long memberIdx) throws SQLException;
	
	// 회원 권한 변경
	public void updateMember(Map<String, Object> map) throws SQLException;
	
	// 회원 상태 변경
	public void updatemManage(Map<String, Object> map) throws SQLException;
	
	// stateCode가 0일때
	public void updateEnabled1(long memberIdx) throws SQLException;
	
	// stateCode가 0이 아닐때
	public void updateEnabled0(long memberIdx) throws SQLException;
}
