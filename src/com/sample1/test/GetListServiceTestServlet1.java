package com.sample1.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample1.model.Msg;
import com.sample1.service.GetListService1;
import com.sample1.service.MessageListView1;

/**
 * Servlet implementation class GetListServiceTestServlet1
 */
@WebServlet("/GetListServiceTestServlet1")
public class GetListServiceTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetListServiceTestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetListService1 service = GetListService1.getInstance();
		MessageListView1 list = service.getMessageList(3);
		//3페이지 불러오기
		
		System.out.println(list);
		List<Msg> l = list.getMessageList();
		
		for (Msg me : l) {
			System.out.println(me);
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
