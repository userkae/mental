package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.common.FileManager;
import com.sp.app.domain.Diary;
import com.sp.app.mapper.DiaryMapper;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryMapper mapper;

	@Autowired
	private FileManager fileManager;

	@Override
	public void insertDiary(Diary dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getSelectFile(), pathname);
			if (saveFilename != null) {
				dto.setFileName(saveFilename);
			}
			mapper.insertDiary(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int dataCount = 0;

		try {
			dataCount = mapper.dataCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataCount;
	}

	@Override
	public List<Diary> listDiary(Map<String, Object> map) {
		List<Diary> list = null;
		try {
			list = mapper.listDiary(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Diary articleDiary(long diaryNum) {
		Diary dto = null;

		try {
			dto = mapper.articleDiary(diaryNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateDiary(Diary dto, String pathname) throws Exception {
	    try {
	    	mapper.articleDiary(dto.getDiaryNum());
	        
			String saveFilename = fileManager.doFileUpload(dto.getSelectFile(), pathname);

			if (saveFilename != null) {
				// 이전 파일 지우기
				if (dto.getFileName().length() != 0) {
					fileManager.doFileDelete(dto.getFileName(), pathname);
				}

				dto.setFileName(saveFilename);
			}
			mapper.updateDiary(dto);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

	@Override
	public void deleteDiary(long diaryNum, String pathname) throws Exception {

		try {
			if (pathname != null) {
				fileManager.doFileDelete(pathname);
			}

			// 게시물지우기
			 mapper.deleteDiary(diaryNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
