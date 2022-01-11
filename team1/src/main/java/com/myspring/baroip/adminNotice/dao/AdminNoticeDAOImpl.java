// 2022.01.10 윤상현

package com.myspring.baroip.adminNotice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.notice.vo.NoticeVO;

@Repository("adminNoticeDAO")
public class AdminNoticeDAOImpl implements AdminNoticeDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 옵션에 따른 notice select DAO
	@Override
	public List<NoticeVO> noticeListToOption( Map<String, String> option) throws DataAccessException {
		List<NoticeVO> noticeList = sqlSession.selectList("mapper.adminNotice.noticeListToOption", option);

		return noticeList;
	}
	
	// 게시물 삭제
	@Override
	public int deleteNotice(String notice_id) throws DataAccessException {
		int result=sqlSession.delete("mapper.adminNotice.noticeDelete", notice_id);
		return result;
	}
}
