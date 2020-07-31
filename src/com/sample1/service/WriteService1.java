package com.sample1.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.sample1.dao.MsgDao;
import com.sample1.jdbc.ConnectionProvider1;
import com.sample1.jdbc.JdbcUtil1;
import com.sample1.model.Msg;

public class WriteService1 {
	//싱글톤 패턴 적용
	private static WriteService1 instance = new WriteService1();
	public static WriteService1 getInstance() {
		return instance;
	}
	private WriteService1() {
	}
	
	
	public boolean write(Msg message) {
		Connection conn = null;
		try {
			conn = ConnectionProvider1.getConnection();
			MsgDao messageDao = MsgDao.getInstance();
			//messageDao.insert메서드로 메시지 테이블에 추가
			messageDao.insert(conn, message);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil1.close(conn);
		}
		
		return true;
	}
}
