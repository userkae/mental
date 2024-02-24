package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.common.FileManager;
import com.sp.app.domain.ComfortManage;
import com.sp.app.mapper.ComfortManageMapper;

@Service
public class ComfortManageServiceImpl implements ComfortManageService{

	@Autowired
	private ComfortManageMapper comfortMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public int dataCount(Map<String, Object> map) {
		int dataCount = 0;
		try {
			dataCount = comfortMapper.dataCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataCount;
	}

	@Override
	public List<ComfortManage> comfortList(Map<String, Object> map) {
		List<ComfortManage> list = null;
		try {
			list = comfortMapper.comfortList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertcomfort(ComfortManage dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getSelectFile(), pathname);
			if (saveFilename != null) {
				dto.setFileName(saveFilename);
			}
			comfortMapper.insertComfort(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public ComfortManage articleComfort(long comfortNum) {
		ComfortManage dto = null;
		try {
			dto = comfortMapper.articleComfort(comfortNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateComfort(ComfortManage dto, String pathname) throws Exception {
	    try {
	        ComfortManage prevDto = comfortMapper.articleComfort(dto.getComfortNum());
	        
	        // 파일이 선택되지 않은 경우
	        if (dto.getSelectFile() == null || dto.getSelectFile().isEmpty()) {
	            // 기존 파일명이 없는 경우에만 파일명을 null로 설정
	            if (prevDto.getFileName() == null || prevDto.getFileName().isEmpty()) {
	                dto.setFileName(null);
	            } else {
	                dto.setFileName(prevDto.getFileName());
	            }
	        } else {
	            // 파일이 선택된 경우
	            String saveFilename = fileManager.doFileUpload(dto.getSelectFile(), pathname);
	            if (saveFilename != null) {
	                // 이전 파일 삭제
	                if (prevDto.getFileName() != null && !prevDto.getFileName().isEmpty()) {
	                    fileManager.doFileDelete(prevDto.getFileName(), pathname);
	                }
	                dto.setFileName(saveFilename);
	            }
	        }
	        
	        comfortMapper.updateComfort(dto);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public void deleteComfort(long comfortNum, String pathname) throws Exception {
		try {
			if (pathname != null) {
				fileManager.doFileDelete(pathname);
			}
			
			comfortMapper.deleteComfort(comfortNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
