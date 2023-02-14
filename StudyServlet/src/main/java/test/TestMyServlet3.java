package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//"http://localhost:8080/StudyServlet/myServlet3" 주소를 매핑하기 위한 
// @WebServlet 어노테이션 설정
@WebServlet("/myServlet3")
public class TestMyServlet3 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestMyServlet3 doGet()실행!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestMyServlet3 doPost()실행!");
	}
	
}
