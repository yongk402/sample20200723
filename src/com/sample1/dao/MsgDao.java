package com.sample1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;

import com.mysql.cj.protocol.Message;
import com.sample1.jdbc.JdbcUtil1;
import com.sample1.model.Msg;

public class MsgDao {
	private static MsgDao messageDao = new MsgDao();
	public static MsgDao getInstance() {
		return messageDao;
	}
	
	private MsgDao() {
	}

	public int insert(Connection conn, Msg message) throws SQLException {
		// 1. 클래스 로딩 : listener에서 이미 로딩됨
		// 2. 연결 생성 : 파라미터로 받음
		// 3. statement 생성 : 메소드 내
		// 4. 쿼리 실행 : 메소드 내
		// 5. 결과 처리 : 호출한 곳에서
		// 6. 자원 닫기
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO sample1_message "
					+ "(guest_name,password, msg) VALUES(?,?,?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil1.close(pstmt);
		}
		
	}
	
	
	
	
	
	public Msg select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement( 
					"SELECT * FROM sample1_message WHERE msg_id=?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return makeMessageFromResultSet(rs);
			} else {
				return null;
			}
		} finally {
			JdbcUtil1.close(rs);
			JdbcUtil1.close(pstmt);
		}
	} 
	
	
	private Msg makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Msg message = new Msg();
		message.setId(rs.getInt("msg_id"));			//(rs.getInt(1));
		message.setGuestName(rs.getString("guest_name"));	//(rs.getString(2));
		message.setPassword(rs.getString("password"));	//(rs.getString(3));
		message.setMessage(rs.getString("msg"));		//(rs.getString(4));
		return message;
	}
	
	
	
	
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM sample1_message");
			rs.next();
			return rs.getInt(1); 
			//return rs.getInt("msg_id");이거도 되나?
						
		} finally {
			JdbcUtil1.close(rs);
			JdbcUtil1.close(stmt);
		}
	}

	
	
	
	
	public List<Msg> selectList(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement( 
					"SELECT * FROM sample1_message "
					+"ORDER BY msg_id DESC LIMIT ?,?");
			pstmt.setInt(1, firstRow-1);
			pstmt.setInt(2, endRow-firstRow+1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<Msg> messageList = new ArrayList<Msg>();
				do {
					messageList.add(makeMessageFromResultSet(rs));
				}while (rs.next());
				return messageList;
			
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil1.close(rs);
			JdbcUtil1.close(pstmt);
		}
	}
	
	
	

		
	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement( 
					"DELETE FROM sample1_message WHERE msg_id=?");
			pstmt.setInt(1, messageId);
			return pstmt.executeUpdate();
			
		} finally {
			JdbcUtil1.close(pstmt);
		}
	}

}
