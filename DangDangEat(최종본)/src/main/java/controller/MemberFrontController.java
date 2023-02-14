package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MailCheckAction;
import action.MemberCheckProAction;
import action.MemberEmailAuthAction;
import action.MemberEmailCheckAction;
import action.MemberEmailUpdateAction;
import action.MemberFindIdProAction;
import action.MemberFindPassProAction;
import action.MemberIdCheckAction;
import action.MemberJoinAction;
import action.MemberLoginProAction;
import action.MemberLogoutAction;
import action.MemberModifyProAction;
import action.MemberSelectAction;
import action.MemberWithdrawProAction;
import action.MyPageAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	
	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//	System.out.println("MemberFrontController");
		
		// POST 방식 요청에 대한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
//		System.out.println("command : " + command);
		
		// 공통으로 사용할 변수 선언
		Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
		ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수
		
		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/MemberJoinForm.me")) { // 회원가입 폼
//			System.out.println("MemberJoinForm");
			
			forward = new ActionForward();
			forward.setPath("member/memberJoin.jsp");
			forward.setRedirect(false);
	    
		} else if(command.equals("/MemberJoin.me")) { // 회원가입 프로
//			System.out.println("MemberJoin");
			
			action = new MemberJoinAction();
			forward = action.execute(request, response);
	    
		} else if(command.equals("/MemberJoinResult.me")) { // 회원가입 결과
//			System.out.println("MemberJoinResult");
			
			forward = new ActionForward();
			forward.setPath("member/member_join_result.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/Main.me")) {
//			System.out.println("MemberIdCheck");
			
			forward = new ActionForward();
			forward.setPath("main/main.jsp");
			forward.setRedirect(false);
	    
		} else if(command.equals("/MemberIdCheck.me")) {
//			System.out.println("MemberIdCheck");
			
			action = new MemberIdCheckAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberLoginForm.me")) { // 로그인 페이지
				System.out.println("로그인 폼");
				forward = new ActionForward();
				forward.setPath("member/login.jsp");
				forward.setRedirect(false);
				
		} else if(command.equals("/MemberLoginPro.me")) {
			System.out.println("로그인 프로");
			
			action = new MemberLoginProAction();
			forward = action.execute(request, response);
			
//		} else if(command.equals("/MemberList.me")) { // 나중에 admin(MemberAdminForm.ad)로 매핑
//			System.out.println("회원 목록");
//			
//			action = new MemberListAction();
//			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberLogout.me")) {
			System.out.println("로그아웃");
			
			action = new MemberLogoutAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/CheckPassForm.me")) {
			System.out.println("비밀번호 확인 폼");
			forward = new ActionForward();
			forward.setPath("member/check_pass.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MemberCheckPro.me")) {
			System.out.println("비밀번호 확인 프로");
			action = new MemberCheckProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberInfo.me")) {
			System.out.println("회원 정보");
			action = new MemberSelectAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberModifyPro.me")) {
			System.out.println("회원 정보 수정");
			action = new MemberModifyProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberWithdrawForm.me")) {
			System.out.println("회원 탈퇴 폼");
			forward = new ActionForward();
			forward.setPath("member/withdraw.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MemberWithdrawPro.me")) {
			System.out.println("회원 탈퇴 프로");
			action = new MemberWithdrawProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberWithdrawResult.me")) {
			System.out.println("회원 탈퇴 결과");
			forward = new ActionForward();
			forward.setPath("member/withdraw_result.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MemberFindIdForm.me")) { // 아이디 찾기 폼
			System.out.println("아이디 찾기 폼");
			forward = new ActionForward();
			forward.setPath("member/find_id.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MemberFindIdPro.me")) { // 아이디 찾기 프로
			System.out.println("아이디 찾기 프로");
			
			action = new MemberFindIdProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberFindPassForm.me")) { // 비밀번호 찾기 폼
			System.out.println("비밀번호 찾기 폼");
			forward = new ActionForward();
			forward.setPath("member/find_pass.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MemberFindPassPro.me")) { // 비밀번호 찾기 프로
			System.out.println("비밀번호 찾기 프로");
			
			action = new MemberFindPassProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MailCheck.me")) {
			System.out.println("메일 인증 요청");
			action = new MailCheckAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/MemberEmailUpdate.me")) {
			System.out.println("회원 정보 페이지 회원 이메일 변경");
			
			action = new MemberEmailUpdateAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberEmailAuth.me")) {
			System.out.println("회원 정보 페이지 회원 이메일 인증");
			
			action = new MemberEmailAuthAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MyPage.me")) {
			System.out.println("마이페이지");
			
			action = new MyPageAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberEmailCheck.me")) {
//	         System.out.println("MemberIdCheck");
	         
	         action = new MemberEmailCheckAction();
	         forward = action.execute(request, response);
	      }
	
		// ----------------------------------------------------------------------
		// ActionForward 객체 내용에 따라 각각 다른 방식의 포워딩 작업 수행(공통)
		// 1. ActionForward 객체가 null 이 아닐 경우 판별
		if(forward != null) {
			// 2. ActionForward 객체에 저장된 포워딩 방식 판별
			if(forward.isRedirect()) { // Redirect 방식
				// Redirect 방식의 포워딩 작업 수행
				// => 포워딩 경로는 ActionForward 객체의 getPath() 메서드 활용
				response.sendRedirect(forward.getPath());
			} else { // Dispatch 방식
				// Dispatch 방식의 포워딩 작업 수행
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	
//		System.out.println("doProcess() 메서드 끝");
	} // doProcess() 메서드 끝(응답데이터 전송)

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
