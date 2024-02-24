package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.domain.Member;
import com.sp.app.mapper.MemberManageMapper;

@Service
public class MemberManageServiceImpl implements MemberManageService{

	@Autowired
	private MemberManageMapper mapper;
	
	@Override
	public List<Member> memberManageList(Map<String, Object> map) throws Exception {
		List<Member> list = null;
		try {
			list = mapper.memberManageList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int dataCount = 0;
		
		try {
			dataCount = mapper.dataCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataCount;
	}

	@Override
	public List<Member> stateCodeList() throws Exception {
		List<Member> list = null;
		
		try {
			list = mapper.stateCodeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Member updateUserInfo(long memberIdx) throws Exception {
		Member dto = null;
		try {
			dto = mapper.updateUserInfo(memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateMember(Map<String, Object> map) throws Exception {
		try {
			mapper.updateMember(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatemManage(Map<String, Object> map) throws Exception {
		try {
			mapper.updatemManage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEnabled1(long memberIdx) throws Exception {
		try {
			mapper.updateEnabled1(memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void updateEnabled0(long memberIdx) throws Exception {
		try {
			mapper.updateEnabled0(memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
