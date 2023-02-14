package test2_forwarding;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatchServlet")
public class TestServlet_Dispatch extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestServlet_Dispatch - doProcess()");
		
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("이름이 " + name);
		System.out.println("나이가 " + age);
		
		// 11/25
		// 현재 서블릿 클래스에서 작업 완료 후 webapp/test6_dispatch_result.jsp 로 포워딩
		// => Dispatch 방식 포워딩 수행
		// 1. request 객체의 getRequestDispatcher() 메서드를 호출 
		//    => 파라미터 : 포워딩 할 페이지 전달, 리턴타입 : javax.servlet.RequestDispatcher
//		RequestDispatcher dispatcher = request.getRequestDispatcher("test6_dispatch_result.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("test2_forwarding/test_dispatch_result.jsp");
		// 2. RequestDispatcher 객체의 forward() 메서드를 호출하여 포워딩 작업 수행
		//    => 파라미터 : request, response 객체
		dispatcher.forward(request, response); // dispatch는 request랑 response가 유지되니까 같이 가지고 넘어감
		
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
    } 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
