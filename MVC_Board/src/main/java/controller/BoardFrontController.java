package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardReplyFormAction;
import action.BoardReplyProAction;
import action.BoardWriteProAction;
import vo.ActionForward;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 11/30 
		// 공통으로 사용할 변수 선언
		Action action = null; // xxxAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수 선언
		ActionForward forward = null;  // 포워딩 정보 저장할 변수(멤버변수 아니니 기본값 넣어야함)
		
		//---------------------------------------------------------------------------------
		// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
		// 각 주소에 따른 액션(작업) 요청
		if(command.equals("/BoardWriteForm.bo")) {
//			System.out.println("글쓰기 폼");
			// 액션포워드 다녀오지 않음
			forward = new ActionForward();
			forward.setPath("board/qna_board_write.jsp");
			forward.setRedirect(false); // false가 기본값 = 생략가능
			
		} else if (command.equals("/BoardWritePro.bo")) {
			// 비즈니스 로직이라 액션포워드 다녀옴
			System.out.println("글쓰기 작업");
			
			action = new BoardWriteProAction(); // 각 액션마다 새로운 인스턴스 생성
			forward = action.execute(request, response); // 액션 작업 후 리턴된 값(포워딩 주소, 방식)
			
		} else if (command.equals("/BoardList.bo")) {
			System.out.println("글목록 작업");
			
			action = new BoardListAction(); 
			forward = action.execute(request, response);
		
		} else if (command.equals("/BoardDetail.bo")) { // 12/05 1&2
			System.out.println("글 상세정보 조회 비즈니스 작업 요청");
			
			action = new BoardDetailAction();
			forward = action.execute(request, response);
			
		} else if (command.equals("/BoardDeleteForm.bo")) { // 12/05 03
			System.out.println("글 삭제 작업 요청");
			
			forward = new ActionForward();
			forward.setPath("board/qna_board_delete.jsp");
			forward.setRedirect(false);
			
		} else if (command.equals("/BoardDeletePro.bo")) { // 12/05 03-(1)
			System.out.println("글 삭제 작업 실행");
			
			action = new BoardDeleteProAction();
			forward = action.execute(request, response);
		} else if (command.equals("/BoardModifyForm.bo")) { // 12/05 04
			System.out.println("글 수정 폼 비즈니스 작업 요청");
			
			action = new BoardModifyFormAction();
			forward = action.execute(request, response);
		} else if (command.equals("/BoardModifyPro.bo")) { // 12/07
			System.out.println("글 수정 작업 실행");
			
			action = new BoardModifyProAction();
			forward = action.execute(request, response);
		} else if (command.equals("/BoardReplyForm.bo")) { // 12/07 6
			System.out.println("게시판 답글 작성");
			
			action = new BoardReplyFormAction();
			forward = action.execute(request, response);
		} else if (command.equals("/BoardReplyPro.bo")) { // 12/07 6-(1)
			System.out.println("게시판 답글 작성 작업 수행");
			
			action = new BoardReplyProAction();
			forward = action.execute(request, response);
		} 
		//--------------------------------------------------------------------
		// ActionForward 객체 내용에 따라 각각 다른 방식의 포워딩 작업 실행 (11/30)
		// 위의 서블릿 주소 판별 후 시행되는 코드 (공통의 작업)
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
