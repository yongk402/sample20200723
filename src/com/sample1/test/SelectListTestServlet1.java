package com.sample1.test;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
 * Servlet implementation class SelectListTestServlet1
 */
@WebServlet("/SelectListTestServlet1")
public class SelectListTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListTestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		int firstRow = 1;
		int endRow = 10;
		
		try {
			conn = ConnectionProvider1.getConnection();
			MsgDao dao = MsgDao.getInstance();
			
			List<Msg> list = dao.selectList(conn, firstRow, endRow);
			
			for (int i = 0; i<list.size(); i++) {
				System.out.println("----" + (i+1) + "----");
				System.out.println(list.get(i));
			}
			
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
