package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BoardListAction;
import action.BoardWriteProAction;
import vo.ActionForward;

//@WebServlet("*.bo")
public class BoardFrontController_Backup extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		// 0. 참고 ) 요청 주소 (전체 URL) 가져오기
		String requestURL = request.getRequestURL().toString();
		System.out.println("requestURL : " +requestURL );
		// requestURL : http://localhost:8080/MVC_Board/mynameis.bo
		
		// 1. 서버 정보를 제외한 어플리케이션 식별 정보만 추출
		//    -> 요청 주소 중 URI 부분 (/프로젝트명/서블릿주소) 추출
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : " +requestURI );
		// requestURI : /MVC_Board/mynameis.bo
		
		// 2. 요청 주소 중 context 경로(/프로젝트명) 추출
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		// contextPath : /MVC_Board
		
		// 3. 요청 주소 중 servlet 주소 부분 추출
		// 3-(1). contextPath에 해당하는 부분 ""(널스트링)으로 치환
//		String command = requestURI.replace(contextPath, "");
		
		// 3-(2). "/서블릿 주소" 에 해당하는 부분 문자열 추출
//		String command = requestURI.substring(contextPath.length());
//		System.out.println("command : " + command);
		
		//--------------------------------------------------------------
		// 위의 과정을 하나로 압축된 getServletPath()
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/BoardWriteForm.bo")) {
//			System.out.println("글쓰기 폼");
			// 글쓰기 폼을 출력하는 뷰페이지(board/qna_board_write.jsp) 로 이동
			// => 비즈니스 로직(= DB 작업) 불필요하므로 뷰페이지로 바로 이동
			// => Dispatch 방식 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher("board/qna_board_write.jsp");
			dispatcher.forward(request, response);
		} else if (command.equals("/BoardWritePro.bo")) {
			System.out.println("글쓰기 작업");
			
			// 글쓰기 폼에서 입력받은 제목 받아 출력
//			String subject = request.getParameter("board_subject");
//			System.out.println("제목 : " +subject);
			
			// BoardWriteProAction 클래스 인스턴스 생성 후 execute()
			BoardWriteProAction action = new BoardWriteProAction();
			ActionForward forward = action.execute(request, response); // return 타입 = 데이터 타입
		
			// ActionForward 객체의 포워딩 방식 판별
			if(forward.isRedirect()) { // redirect
				response.sendRedirect(forward.getPath());
			} else { // dispatch
				RequestDispatcher dispatch = request.getRequestDispatcher(forward.getPath());
				dispatch.forward(request, response);
			}
			
		} else if (command.equals("/BoardList.bo")) {
			System.out.println("글목록 작업");
			
			BoardListAction action = new BoardListAction();
			ActionForward forward = action.execute(request, response);
			
			if(forward.isRedirect()) { // redirect
				response.sendRedirect(forward.getPath());
			} else { // dispatch
				RequestDispatcher dispatch = request.getRequestDispatcher(forward.getPath());
				dispatch.forward(request, response);
			}
			
		}// if
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
