package com.sp.app.domain;

import org.springframework.web.multipart.MultipartFile;

public class ComfortManage {

	private long comfortNum;		// 게시물 번호
	private String cContent;		// 게시물 내용
	private String cRegDate;		// 등록일
	private String fileName;		// 관리자가 올린 사진
	private long memberIdx;			// 회원번호
	
	private MultipartFile selectFile;
	private String originalFilename;
	private String[] listFilename;
	
	public long getComfortNum() {
		return comfortNum;
	}
	public void setComfortNum(long comfortNum) {
		this.comfortNum = comfortNum;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcRegDate() {
		return cRegDate;
	}
	public void setcRegDate(String cRegDate) {
		this.cRegDate = cRegDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(long memberIdx) {
		this.memberIdx = memberIdx;
	}
	public MultipartFile getSelectFile() {
		return selectFile;
	}
	public void setSelectFile(MultipartFile selectFile) {
		this.selectFile = selectFile;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String[] getListFilename() {
		return listFilename;
	}
	public void setListFilename(String[] listFilename) {
		this.listFilename = listFilename;
	}
	
}
