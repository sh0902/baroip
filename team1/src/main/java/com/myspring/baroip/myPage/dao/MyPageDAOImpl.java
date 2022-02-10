// 2022.01.24 ������

package com.myspring.baroip.myPage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.notice.vo.NoticeVO;
import com.myspring.baroip.product.vo.ProductVO;
import com.myspring.baroip.user.vo.UserVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO {
	
	@Autowired
	private SqlSession sqlSession;

	// user_id �� �ش��ϴ� cart�� ��ü ���� select DAO
	@Override
	public int myPageCartCount(UserVO userVO) throws DataAccessException {
		int cartCount = sqlSession.selectOne("mapper.myPage.myPageCartCount", userVO);
				
		return cartCount;
	}
	
	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select DAO
	@Override
	public int myPageOrderCount(UserVO userVO) throws DataAccessException {
		int orderCount = sqlSession.selectOne("mapper.myPage.myPageOrderCount", userVO);
				
		return orderCount;
	}
	
	@Override
	public int updateMyInfo(UserVO userVO) throws DataAccessException{
		
		int flag = sqlSession.update("mapper.myPage.updateMyInfo", userVO);
		
		return flag;
	}
	
	// ��ȸ ���ǿ� ���� �ֹ� ����Ʈ ��ȸ DAO
	@Override
	public List<Map<String, Object>> myOrder( Map<String, String> option) throws DataAccessException {
		
		List<Map<String, Object>> orderList = sqlSession.selectList("mapper.myPage.selectOrder", option);

		return orderList;
	}
	
	// �ֹ����� ���� DAO
		@Override
		public void updateOrder(Map<String, String> option) throws DataAccessException {
			sqlSession.update("mapper.myPage.updateOrder", option);
			
		}
		
	// ��ǰ ��� DAO 
		@Override
		public String askRefund(NoticeVO noticeVO) throws DataAccessException {
			
			sqlSession.insert("mapper.myPage.askRefund",noticeVO);
			sqlSession.update("mapper.myPage.askRefundUpdate", noticeVO);
			String notice_id = noticeVO.getNotice_id();
						
			return notice_id;
		}
		
		// �ֹ� ���� ȣ�� DAO
		@Override
		public List<Map<String, Object>> orderDetail(String order_id) throws DataAccessException {
			
			List<Map<String, Object>> orderList = sqlSession.selectList("mapper.myPage.orderDetail", order_id);

			return orderList;
		}
		
//		2022.02.08 �Ѱ���
		
		// ���� ����Ʈ
		@Override
		public List<NoticeVO> questionList(String user_id) throws DataAccessException {
			List<NoticeVO> result = sqlSession.selectList("mapper.myPage.questionList", user_id);
			return result;
		}
		
//		���� ���� ������
		@Override
		public List<NoticeVO> questionDetail(String notice_id) throws DataAccessException {
			List<NoticeVO> result = sqlSession.selectList("mapper.myPage.questionDetail", notice_id);
			return result;
		}
		
//		��ǰ ��
		@Override
		public String productQuestion(String product_id) throws DataAccessException {
			ProductVO productVO = sqlSession.selectOne("mapper.myPage.PQADetail", product_id);
			String product_title = productVO.getProduct_main_title();
			
			return product_title;
		}
		
//		���� ����
		@Override
		public int questionDelete(String notice_id) throws DataAccessException {
			int result = sqlSession.delete("mapper.myPage.questionDelete", notice_id);
			
			return result;
		}
		
}
