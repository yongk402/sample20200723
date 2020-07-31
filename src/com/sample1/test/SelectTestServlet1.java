package com.sample1.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample1.dao.MsgDao;
import com.sample1.jdbc.ConnectionProvider1;
import com.sample1.jdbc.JdbcUtil1;
import com.sample1.model.Msg;

/**
 * Servlet implementation class SelectTestServlet1
 */
@WebServlet("/SelectTestServlet1")
public class SelectTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ConnectionProvider1.getConnection();
			int messageId = 2;
			
			MsgDao dao = MsgDao.getInstance();
			Msg message = dao.select(conn, messageId);
			
			System.out.println(message);
			
			
		} catch (SQLException e) {
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
