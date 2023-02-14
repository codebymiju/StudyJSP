package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CartDeleteAction;
import action.CartInsertAction;
import action.CartListAction;
import action.CartUpdateAction;
import action.WishlistDeleteAction;
import action.WishlistDetailAction;
import action.WishlistInsertAction;
import vo.ActionForward;

@WebServlet("*.ct")
public class CartFrontController extends HttpServlet {
	
	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("CartFrontController");
		
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
		if(command.equals("/CartInsert.ct")) {
			action = new CartInsertAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CartList.ct")) {
			action = new CartListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CartDelete.ct")) {
			action = new CartDeleteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/WishlistInsert.ct")) {
			action = new WishlistInsertAction();
			forward = action.execute(request, response);
		} else if(command.equals("/WishlistDetail.ct")) {
			action = new WishlistDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/WishlistDelete.ct")) {
			action = new WishlistDeleteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CartUpdate.ct")) {
			action = new CartUpdateAction();
			forward = action.execute(request, response);
		}
		
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
