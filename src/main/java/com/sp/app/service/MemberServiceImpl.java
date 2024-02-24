package com.sp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.domain.Member;
import com.sp.app.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	public MemberMapper dao;
	
	@Override
	public Member findById(String userId) {
		Member dto = null;
		try {
			dto = dao.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int findByMembership(long memberIdx) {
		int membership = 0;
		try {
			membership = dao.findByMembership(memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membership;
	}

	@Override
	public void insertMember(Member dto) throws Exception {
		try {
			if (dto.getEmail1().length() != 0 && dto.getEmail2().length() != 0) {
				dto.setEmail(dto.getEmail1() + "@" + dto.getEmail2());
			}
			
			// 회원정보 저장
			dao.insertMember(dto);
			dto.setMemberIdx(dao.maxMemberIdx());
			dao.insertmManage(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}

	@Override
	public Member findByInfo(long memberIdx) throws Exception {
		Member dto = null;
		try {
			dto = dao.findByInfo(memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateInfo(Member dto) throws Exception {
		try {
			if (dto.getEmail1().length() != 0 && dto.getEmail2().length() != 0) {
				dto.setEmail(dto.getEmail1() + "@" + dto.getEmail2());
			}
			
			dao.updateInfo(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
