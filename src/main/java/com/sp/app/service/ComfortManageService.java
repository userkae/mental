package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.domain.ComfortManage;

public interface ComfortManageService {
	
	public int dataCount(Map<String, Object> map);
	public List<ComfortManage> comfortList(Map<String, Object> map);
	public void insertcomfort(ComfortManage dto, String pathname) throws Exception;
	public ComfortManage articleComfort(long comfortNum);
	public void updateComfort(ComfortManage dto, String pathname) throws Exception;
	public void deleteComfort(long comfortNum, String pathname) throws Exception;
}
