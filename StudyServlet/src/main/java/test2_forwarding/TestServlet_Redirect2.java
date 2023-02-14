package test2_forwarding;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 11/25
// 
@WebServlet("/redirectServlet2")
public class TestServlet_Redirect2 extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestServlet_Redirect2 - doProcess()");
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("이름이 " + name);
		System.out.println("나이가 " + age);
		
		//----------------------------------------------------------
		// 11/25
		// 현재 서블릿 클래스에서 작업 완료 후 test_redirect_result.jsp 로 포워딩
		// => Redirect 방식 포워딩 수행		
		// http://localhost:8080/StudyServlet/여기까지 webapp
		// test2_forwarding/test_redirect_result.jsp 주소창에 어디까지 나와있는지 확인!
		response.sendRedirect("test6_redirect_result.jsp");
		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
