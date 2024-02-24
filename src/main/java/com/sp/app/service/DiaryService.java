package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.domain.Diary;

public interface DiaryService {

	public void insertDiary(Diary dto, String pathname) throws Exception;
	
	public int dataCount(Map<String, Object> map);
	public List<Diary> listDiary(Map<String, Object> map);		// 일기장 list
	
	public Diary articleDiary(long diaryNum);		// 일기 보기(article)
	public void updateDiary(Diary dto, String pathname) throws Exception;
	public void deleteDiary(long diaryNum, String pathname) throws Exception;
}
