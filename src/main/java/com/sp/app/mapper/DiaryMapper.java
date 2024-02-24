package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.Diary;

@Mapper
public interface DiaryMapper {

	public void insertDiary(Diary dto) throws SQLException;
	public long selectDiaryNum() throws SQLException;				// 직전의 diaryNum 가져오기
	
	public int dataCount();
	
	public List<Diary> listDiary(Map<String, Object> map);			// 일기장 list
	
	public Diary articleDiary(long diaryNum);						// 일기 보기(article)
	public void updateDiary(Diary dto) throws SQLException;			// 일기 수정
	public void deleteDiary(long diaryNum) throws SQLException;
}
