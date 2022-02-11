// 2022.02.10 ������

package com.myspring.baroip.adminCS.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.baroip.adminCS.dao.AdminCSDAO;
import com.myspring.baroip.notice.vo.NoticeVO;

@Service("adminCSService")
public class AdminCSServiceImpl implements AdminCSService {
	@Autowired
	private AdminCSDAO adminCSDAO;
		
	// ��ȸ ���ǿ� ���� CS ����Ʈ ��ȸ ����
		@Override
		public List<NoticeVO> CSListToOption(Map<String, String> option) throws Exception {

			// option�� productCreDate�� ���, value�� ���޵� yyyy-mm-dd,yyyy-mm-dd�� begin, end�� �����Ͽ� �ٽ� �����Ѵ�.
			if(option.get("search_option") != null && option.get("search_option").equals("notice_cre_date")) {
				String[] date = option.get("search_value").split(",");
			
				option.remove("search_value");
				option.put("begin", date[0]);
				option.put("end", date[1]);

			}
			
			List<NoticeVO> CSList = adminCSDAO.CSListToOption(option);
			

			return CSList;
		}
		
	//	CS ���� Service
	@Override
	public String deleteCS(String notice_id) throws Exception { 
		int result=adminCSDAO.deleteCS(notice_id);
		String message = "baroip : ������ �Խù��� �������� �ʽ��ϴ�.";
		if (result > 0) {
			message = "baroip : "+notice_id+" �Խñ��� �����Ǿ����ϴ�.";
		}
		return message; 
	}
	
	// CS ������ Service
	@Override
	public String CSDetail(Map<String, String> option) throws Exception {
		int result = adminCSDAO.CSDetail(option);
		String message = "baroip : �Խñ� ��Ͽ� ������ �߻��Ͽ����ϴ�.";
		
		if(result > 0) {
			message = "baroip : �Խñ��� ��ϵǾ����ϴ�.";
		}
		
		return message;
	}
	
	// cs ��� ��� Service
	@Override
	public String addCS(NoticeVO noticeVO) throws Exception {
		int result = adminCSDAO.addCS(noticeVO);
		String notice_title = noticeVO.getNotice_title();
		String message = "baroip : �Խñ� ��Ͽ� ������ �߻��Ͽ����ϴ�.";
		
		if(result > 0) {
			message = "baroip : "+notice_title+"�Խñ��� �����Ǿ����ϴ�.";
		}
		
		return message;
	}

}
