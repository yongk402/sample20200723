package com.sample1.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample1.service.DeleteService1;

/**
 * Servlet implementation class DeleteServlet1
 */
@WebServlet("/delete1")
public class DeleteServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		int messageId = Integer.parseInt(request.getParameter("id"));
		String messageIdStr =request.getParameter("id");
		String password = request.getParameter("password");
		int messageId = Integer.valueOf(messageIdStr);
		
		// delete service 객체 얻어서
		DeleteService1 service = DeleteService1.getInstance();
				
		// delete 메소드 실행
		String info = service.deleteMessage(messageId, password);
		
		// 결과 string을
		
		// session "info" attribute로 셋팅
		session.setAttribute("info", info);
		
		// main 으로 redirection
		response.sendRedirect("main1");


	}

}
