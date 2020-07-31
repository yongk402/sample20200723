package com.sample1.service;

import java.sql.Connection;

import com.sample1.dao.MsgDao;
import com.sample1.jdbc.ConnectionProvider1;
import com.sample1.jdbc.JdbcUtil1;
import com.sample1.model.Msg;

public class DeleteService1 {
	//싱글 패턴 적용
	private static DeleteService1 instance = new DeleteService1();
	public static DeleteService1 getInstance() {
		return instance;
	}
	private DeleteService1() {
	}

	
	public String deleteMessage(int messageId, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider1.getConnection();
			conn.setAutoCommit(false);
			
			MsgDao messageDao = MsgDao.getInstance();
			Msg message = messageDao.select(conn, messageId);
			
			if(message == null) {
				return "메시지 없음";				
			}
			
			if(!message.matchPassword(password)) {
				return "패스워드 불일치";
			}
			
			messageDao.delete(conn, messageId);
			conn.commit();
			
			return "삭제 성공";
			
		} catch (Exception e) {
			JdbcUtil1.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil1.close(conn);
		}
		
		
		return "삭제 실패";			
	}
}
