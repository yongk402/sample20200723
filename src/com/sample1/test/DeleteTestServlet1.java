package com.sample1.test;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample1.dao.MsgDao;
import com.sample1.jdbc.ConnectionProvider1;
import com.sample1.jdbc.JdbcUtil1;

/**
 * Servlet implementation class DeleteTestServlet1
 */
@WebServlet("/DeleteTestServlet1")
public class DeleteTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		int messageId = 2;
		
		try {
			conn = ConnectionProvider1.getConnection();
			MsgDao dao = MsgDao.getInstance();
			
			int cnt = dao.delete(conn, messageId);
			System.out.println(cnt + "개 삭제됨");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil1.close(conn);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
