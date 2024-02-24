package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.ComfortManage;

@Mapper
public interface ComfortManageMapper {
	
	public int dataCount(Map<String, Object> map);	// 위로 한스푼 리스트 개수
	public List<ComfortManage> comfortList(Map<String, Object> map);	// 위로 한스푼 리스트
	public void insertComfort(ComfortManage dto) throws SQLException;	// 위로 한스푼 등록
	public ComfortManage articleComfort(long comfortNum);				// 특정 위로 한스푼 정보
	public void updateComfort(ComfortManage dto) throws SQLException;	// 특정 위로 한스푼 수정
	public void deleteComfort(long comfortNum) throws SQLException;		// 특정 위로 한스푼 삭제
}
