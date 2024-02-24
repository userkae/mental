package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.domain.Commu;
import com.sp.app.domain.CommuReply;

@Mapper
public interface CommuMapper {

	public List<Commu> commuList(Map<String, Object> map);		// 리스트 출력
	public int dataCount(Map<String, Object> map);				// 데이터 개수 출력
	
	public Commu articleCommu(long commuNum);					// 해당 커뮤니티 보여주기
	public void insertCommu(Commu dto) throws Exception;		// 커뮤니티 등록
	public void updateCommu(Commu dto) throws Exception;		// 커뮤니티 수정
	public void deleteCommu(long commuNum) throws Exception;	// 커뮤니티 삭제
	public void updateHitCount(long commuNum) throws Exception;	// 조회수 수정
	public long maxReplyNum() throws SQLException;				// 직전의 replyNum 가져오기
	
	// 댓글 등록
	public void insertReply(CommuReply dto) throws Exception;
	// 댓글 리스트
	public List<CommuReply> commuReplyList(long commuNum);
	// 댓글 삭제
	public void deleteReply(long replyNum) throws SQLException;
	// 새로 단 댓글 찾기
	public CommuReply selectReply(long replyNum);
}
