package com.sample1.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample1.model.Msg;
import com.sample1.service.WriteService1;

/**
 * Servlet implementation class WriteServlet1
 */
@WebServlet("/write1")
public class WriteServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet1() {
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
		Msg message = new Msg();
		
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String body = request.getParameter("message");

		
		if(name == null || pw == null || body == null 
				|| name.isEmpty() || pw.isEmpty() || body.isEmpty()) {
			session.setAttribute("info", "이름, 암호, 메시지를 꼭 입력하세요.");
		} else {
		
//			System.out.println("body :" + body);
			
			message.setGuestName(name);
			message.setPassword(pw);
			message.setMessage(body);
			
			
			WriteService1 service = WriteService1.getInstance();
			boolean success = service.write(message);

			
			if(success) {
				session.setAttribute("info", "메시지가 등록 되었습니다.");  //main1.jsp에서 ${info}형태로 화면출력
			} else {
				session.setAttribute("info", "메시지가 등록에 실패하였습니다..");
			}
		}
		response.sendRedirect("main1");


	}

}
