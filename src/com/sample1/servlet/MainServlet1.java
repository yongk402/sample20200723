package com.sample1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample1.service.GetListService1;
import com.sample1.service.MessageListView1;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main1")
public class MainServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pageStr = request.getParameter("page");
		int page = 1;
		
		if (pageStr != null) {
			page = Integer.valueOf(pageStr);
		}
		
		GetListService1 service = GetListService1.getInstance();
		MessageListView1 list = service.getMessageList(page);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/main1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
