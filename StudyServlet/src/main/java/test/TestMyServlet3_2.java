package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//"http://localhost:8080/StudyServlet/myServlet3" 주소를 매핑하기 위한 
// @WebServlet 어노테이션 설정
//=> 단, "/myServlet3" 주소만 단일 매핑됨(= 즉, 다른 주소는 매핑되지 못한다!)
@WebServlet("*.test")
public class TestMyServlet3_2 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestMyServlet3_2 doGet()실행!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestMyServlet3_2 doPost()실행!");
	}
	
}
