package test4_board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardWritePro")
public class BoardWriteProServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("BoardWriteProServlet - doPost()");
		response.sendRedirect("BoardList"); 
		// 나는 루트에 있고 서블릿 주소만 바꿀것이기 때문에 BoardList 만 적어주면됨
		// ../ / 등 필요없음 주소창 확인하기!
	}

}
