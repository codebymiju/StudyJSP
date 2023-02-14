package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ProductAdminListAction;
import action.ProductDeleteProAction;
import action.ProductDetailAction;
import action.ProductInsertProAction;
import action.ProductListAction;
import action.ProductModifyFormAction;
import action.ProductModifyProAction;
import vo.ActionForward;

@WebServlet("*.pd")
public class ProductFrontController extends HttpServlet {

	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ProductFrontController");

		// POST 방식 요청에 대한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");

		// 서블릿 주소 추출
		String command = request.getServletPath();

		// 공통으로 사용할 변수 선언
		Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
		ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수

		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청

		if(command.equals("/ProductInsertForm.pd")) {
			//상품등록 폼 요청
			forward = new ActionForward();
			forward.setPath("/product/product_insertForm.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/ProductInsertPro.pd")) {
			//상품 등록 비즈니스 로직 요청
			action = new ProductInsertProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ProductModifyForm.pd")) {
			// 상품 수정 폼 비즈니스 작업 요청
			action = new ProductModifyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ProductModifyPro.pd")) {
			// 상품 수정 작업 요청
			action = new ProductModifyProAction();
			forward = action.execute(request, response);
		} else if (command.equals("/ProductDeletePro.pd")) {
			// 상품 삭제 작업 요청
			action = new ProductDeleteProAction();
			forward = action.execute(request, response);			
		}  else if (command.equals("/ProductList.pd")) {
			// 상품 리스트 출력 작업 요청
			action = new ProductListAction();
			forward = action.execute(request, response);
		}  else if (command.equals("/ProductDetail.pd")) {
			// 상품 상세정보 출력 작업 요청
			action = new ProductDetailAction();
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
		//			System.out.println("doProcess() 메서드 끝");
	} // doProcess() 메서드 끝(응답데이터 전송)

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
