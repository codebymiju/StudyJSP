package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminBoardListAction;
import action.AdminOrderListAction;
import action.AdminTotalMainAction;
import action.CouponCodeCheckAction;
import action.CouponDeleteAction;
import action.CouponListAction;
import action.CouponRegisterProAction;

import action.CouponUpdateAction;
import action.MemberDeleteAction;
import action.MemberListAction;
import action.MemberUpdateAction;
import action.ProductAdminListAction;
import vo.ActionForward;

@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {

	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		// POST 방식 요청에 대한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");

		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);

		// 공통으로 사용할 변수 선언
		Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
		ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수

		System.out.println("AdminFrontController");


		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/AdminMain.ad")) { // 관리자 페이지 메인
			System.out.println("관리자 페이지");

			action = new AdminTotalMainAction();
			forward = action.execute(request, response);
		} else if(command.equals("/AdminMemberList.ad")) { // 회원 관리 페이지

			System.out.println("회원 목록");

			action = new MemberListAction();
			forward = action.execute(request, response);

		} else if(command.equals("/AdminMemberDelete.ad")) { // 관리자 회원 삭제
			System.out.println("관리자 회원 삭제");

			action = new MemberDeleteAction();
			forward = action.execute(request, response);

		} else if(command.equals("/AdminMemberUpdate.ad")) { // 관리자 회원 수정
			System.out.println("관리자 회원 수정");

			action = new MemberUpdateAction();
			forward = action.execute(request, response);

		} else if(command.equals("/CouponRegister.ad")) {
			//관리자 쿠폰등록 view페이지
			forward = new ActionForward();
			forward.setPath("admin/admin_coupon.jsp");
			forward.setRedirect(false);

		} else if(command.equals("/CouponRegisterPro.ad")) {
			//관리자 쿠폰등록 비지니스로직
			action = new CouponRegisterProAction();
			forward = action.execute(request, response);


		} else if(command.equals("/CheckExistCouponCode.ad")) {
			//관리자 쿠폰등록페이지_쿠폰코드 중복인지 확인
			action = new CouponCodeCheckAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/AdminCouponList.ad")) {
			//관리자 쿠폰 리스트 view페이지
			action = new CouponListAction();
			forward = action.execute(request, response);	

		} else if(command.equals("/CouponDelete.ad")) {
			//관리자 쿠폰 삭제
			action = new CouponDeleteAction();
			forward = action.execute(request, response);	
			
		} else if (command.equals("/CouponUpdate.ad")) {
			//관리자 쿠폰 수정
			action = new CouponUpdateAction();
			forward = action.execute(request, response);

	
	//--------23/01/06 수정된 AdminOrderList 시작----------
		} else if (command.equals("/AdminOrderList.ad")) {
		      // 관리자 페이지 주문 관리 페이지
		      action = new AdminOrderListAction();
		      forward = action.execute(request, response);
	
	//--------23/01/06 수정된 AdminOrderList 시작----------

		} else if (command.equals("/AdminProductList.ad")) { // 주문 관리
			// 관리자 상품 리스트 출력 작업 요청
			action = new ProductAdminListAction();
			forward = action.execute(request, response);

		} else if (command.equals("/AdminBoardList.ad")) { // 게시판 관리
			action = new AdminBoardListAction();
			forward = action.execute(request, response);
			
		}  else if (command.equals("/AdminCouponList.ad")) { // 쿠폰 관리 새로 매핑해주세요!!

		}  else if (command.equals("/About.ad")) { //
			// top에 있는 about 페이지로 이동 
			forward = new ActionForward();
			forward.setPath("main/about.jsp");
			forward.setRedirect(false);

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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

