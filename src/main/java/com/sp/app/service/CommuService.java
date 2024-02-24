package com.sp.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sp.app.domain.Commu;
import com.sp.app.domain.CommuReply;

public interface CommuService {

	public List<Commu> commuList(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Commu articleCommu(long commuNum);
	public void insertCommu(Commu dto) throws SQLException;
	public void updateCommu(Commu dto) throws SQLException;
	public void deleteCommu(long commuNum) throws SQLException;
	public void updateHitCount(long commuNum) throws SQLException;
	
	public void insertReply(CommuReply dto) throws Exception;
	public List<CommuReply> commuReplyList(long commuNum);
	public void deleteReply(long replyNum) throws Exception;
	public CommuReply selectReply(long replyNum);
	public long maxReplyNum() throws Exception;
}
