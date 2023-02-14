package test3_student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test3_student/List")
public class ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ListServlet - doGet()");
		
		// WritePro -> List 서블릿 주소 요청 시 Redirect 방식에 의해 주소가 변경됨
		// 또한, request 객체도 유지되지 않음
//		request.setCharacterEncoding("UTF-8");
//		System.out.println("이름 : " + request.getParameter("name"));
//		System.out.println("나이 : " + Integer.parseInt(request.getParameter("idx")));
		// => 따라서, 이전 request 객체 데이터 접근 불가능하다!
		
		// 글목록에 필요한 데이터를 조회했다고 가정하고
		// List<StudentDTO> 객체(studentList) 생성하여 StudentDTO 객체 저장하기
		List<StudentDTO> studentList = new ArrayList<StudentDTO>();
		studentList.add(new StudentDTO(1, "홍길동"));
		studentList.add(new StudentDTO(2, "이순신"));
		studentList.add(new StudentDTO(3, "강감찬"));
		
		request.setAttribute("studentList", studentList); // List 객체 저장하기
		
		// test3_student/list.jsp 페이지로 포워딩하기
		// => 기존 서블릿 주소("List")를 유지하고, request 객체를 유지하기 위해
		//    Dispatch 방식으로 포워딩
		RequestDispatcher dispatch = request.getRequestDispatcher("list.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ListServlet - doPost()");
	}

}
