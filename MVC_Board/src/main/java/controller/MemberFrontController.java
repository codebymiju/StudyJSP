package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardWriteProAction;
import action.MemberJoinProAction;
import action.MemberListAction;
import action.MemberLoginProAction;
import action.MemberLogoutAction;
import vo.ActionForward;
// 12/09 추가 01.
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 공통으로 사용할 변수 선언
		Action action = null; // xxxAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수 선언
		ActionForward forward = null;  // 포워딩 정보 저장할 변수(멤버변수 아니니 기본값 넣어야함)
		
		//---------------------------------------------------------------------------------
		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/MemberJoinForm.me")) { // 회원가입 페이지
			
			forward = new ActionForward();
			forward.setPath("member/member_join_form.jsp");
			forward.setRedirect(false); 
			
		} else if (command.equals("/MemberJoinResult.me")) { // 조인 성공시 결과 페이지
			
			forward = new ActionForward();
			forward.setPath("member/member_join_result.jsp");
			forward.setRedirect(false); 
			
		} else if (command.equals("/MemberLoginForm.me")) { // 로그인 페이지
			
			forward = new ActionForward();
			forward.setPath("member/member_login_form.jsp");
			forward.setRedirect(false); 
			
		} else if (command.equals("/MemberJoinPro.me")) { // 회원가입 DB 작업 하는 페이지
			
			action = new MemberJoinProAction();
			forward = action.execute(request, response);
			
		} else if (command.equals("/MemberLoginPro.me")) { // 로그인 DB 작업 페이지
			
			action = new MemberLoginProAction();
			forward = action.execute(request, response);
			
		}  else if (command.equals("/MemberLogout.me")) { // 로그아웃 작업 페이지
			
			action = new MemberLogoutAction();
			forward = action.execute(request, response);
			
		} else if (command.equals("/MemberList.me")) { // 회원 목록 조회 페이지
			
			action = new MemberListAction();
			forward = action.execute(request, response);
			
		}
		
		//---------------------------------------------------------------------------------
		
		if(forward != null) {
			if(forward.isRedirect()) { // redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // dispatch 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		System.out.println("doProcess() 끝");
	} // doProcess() 끝 (응답데이터 전송)
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
