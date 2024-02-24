package com.sp.app.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.Member;

@Mapper
public interface MemberMapper {
	// 아이디를 입력했을때 가져올 정보들
	public Member findById(String userId);
	
	// LoginCheckInterceptor에서 관리자가 아닌 경우, 접근 불가
	public int findByMembership(long memberIdx);
	
	// 회원가입
	public void insertMember(Member dto) throws SQLException;
	
	// 최근 memberIdx 가져오기
	public long maxMemberIdx() throws SQLException;
	
	// 회원관리 insert
	public void insertmManage(Member dto) throws SQLException;
	
	// 회원정보 수정 관련 데이터 가져오기
	public Member findByInfo(long memberIdx) throws SQLException;
	
	// 회원 정보 수정
	public void updateInfo(Member dto) throws SQLException;
	
}

