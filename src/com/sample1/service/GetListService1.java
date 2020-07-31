package com.sample1.service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import com.sample1.dao.MsgDao;
import com.sample1.jdbc.ConnectionProvider1;
import com.sample1.jdbc.JdbcUtil1;
import com.sample1.model.Msg;

public class GetListService1 { //p457
	//싱글톤 패턴 적용
	private static GetListService1 instance = new GetListService1();
	public static GetListService1 getInstance() {
		return instance;
	}
	private GetListService1() {
		
	}
	
	//한페이지에 보여줄 메시지 개수 3개로 지정
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	
	public MessageListView1 getMessageList(int pageNumber) {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		
		try {
			conn = ConnectionProvider1.getConnection();
			MsgDao messageDao = MsgDao.getInstance();
			
	//전체 메시지 개수 구하기		
			int messageTotalCount = messageDao.selectCount(conn);
			
			List<Msg> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if(messageTotalCount>0) {
				firstRow = (pageNumber-1)*MESSAGE_COUNT_PER_PAGE+1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE -1;
				//messageDao.selectList이용해서 ㅣ작행과 끝행 속하는 메시지 목록 구한다.
				messageList = messageDao.selectList(conn, firstRow, endRow);
				
			} else {
				// 메시지 개수 0인경우 빈 리스트 messageList에 할당
				currentPageNumber = 0;
				messageList = Collections.emptyList();
				
			}
			//새로운 MessageListView1 객체 리턴
			return new MessageListView1(messageList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			JdbcUtil1.close(conn);
		}

		return null;
	}
}
