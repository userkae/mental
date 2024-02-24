package com.sp.app.domain;

import org.springframework.web.multipart.MultipartFile;

public class Diary {

	private long diaryNum;			// 일기 번호(기본키)
	private String dSubject;		// 일기 제목
	private String dContent;		// 일기 내용
	private String dRegDate; 		// 일기 등록일
	private String dUpdateDate;		// 일기 수정일
	private long memberIdx;			// 회원번호(외래키)
	
	private long dPhotoNum;			// 사진 번호
	private String fileName;		// 사진 이름
	
	private MultipartFile selectFile;
	private String originalFilename;
	private String[] listFilename;
	
	public long getDiaryNum() {
		return diaryNum;
	}
	public void setDiaryNum(long diaryNum) {
		this.diaryNum = diaryNum;
	}
	public String getdSubject() {
		return dSubject;
	}
	public void setdSubject(String dSubject) {
		this.dSubject = dSubject;
	}
	public String getdContent() {
		return dContent;
	}
	public void setdContent(String dContent) {
		this.dContent = dContent;
	}
	public String getdRegDate() {
		return dRegDate;
	}
	public void setdRegDate(String dRegDate) {
		this.dRegDate = dRegDate;
	}
	public String getdUpdateDate() {
		return dUpdateDate;
	}
	public void setdUpdateDate(String dUpdateDate) {
		this.dUpdateDate = dUpdateDate;
	}
	public long getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(long memberIdx) {
		this.memberIdx = memberIdx;
	}
	public long getdPhotoNum() {
		return dPhotoNum;
	}
	public void setdPhotoNum(long dPhotoNum) {
		this.dPhotoNum = dPhotoNum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getSelectFile() {
		return selectFile;
	}
	public void setSelectFile(MultipartFile selectFile) {
		this.selectFile = selectFile;
	}
	public String[] getListFilename() {
		return listFilename;
	}
	public void setListFilename(String[] listFilename) {
		this.listFilename = listFilename;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	
}
