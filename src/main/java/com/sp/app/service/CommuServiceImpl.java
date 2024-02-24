package com.sp.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.domain.Commu;
import com.sp.app.domain.CommuReply;
import com.sp.app.mapper.CommuMapper;

@Service
public class CommuServiceImpl implements CommuService{

	@Autowired
	private CommuMapper mapper;
	
	@Override
	public List<Commu> commuList(Map<String, Object> map) {
		List<Commu> list = null;
		try {
			list =  mapper.commuList(map);
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
	public Commu articleCommu(long commuNum) {
		Commu dto = null;
		try {
			dto = mapper.articleCommu(commuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void insertCommu(Commu dto) {
		try {
			mapper.insertCommu(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCommu(Commu dto) {
		try {
			mapper.updateCommu(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCommu(long commuNum) throws SQLException {
		try {
			mapper.deleteCommu(commuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHitCount(long commuNum) throws SQLException {
		try {
			mapper.updateHitCount(commuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertReply(CommuReply dto) throws Exception {

		try {
			mapper.insertReply(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommuReply> commuReplyList(long commuNum) {
		List<CommuReply> list = null;
		
		try {
			list = mapper.commuReplyList(commuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteReply(long replyNum) throws Exception {
		try {
			mapper.deleteReply(replyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CommuReply selectReply(long replyNum) {
		CommuReply dto = null;
		try {
			dto = mapper.selectReply(replyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public long maxReplyNum() throws Exception {
		long replyNum = 0;
		try {
			replyNum = mapper.maxReplyNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyNum;
	}



}
