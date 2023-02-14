package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CommentDeleteProAction;
import action.CommentWriteProAction;
import action.NoticeDeleteProAction;
import action.NoticeDetailAction;
import action.NoticeListAction;
import action.NoticeModifyFormAction;
import action.NoticeModifyProAction;
import action.NoticeWriteProAction;
import action.QnaDeleteProAction;
import action.QnaDetailAction;
import action.QnaListAction;
import action.QnaModifyFormAction;
import action.QnaModifyProAction;
import action.QnaReplyFormAction;
import action.QnaReplyProAction;
import action.QnaWriteProAction;
import action.ReviewDeleteProAction;
import action.ReviewDetailAction;
import action.ReviewListAction;
import action.ReviewModifyFormAction;
import action.ReviewModifyProAction;
import action.ReviewReplyFormAction;
import action.ReviewReplyProAction;
import action.ReviewWriteProAction;
import vo.ActionForward;

@WebServlet("*.bo") // xxx.bo 로 끝나는 모든 주소 매핑
public class BoardFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 공통으로 사용할 변수 선언
		Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
		ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수
		
		
		// 공지
		if(command.equals("/NoticeWriteForm.bo")) {			
			forward = new ActionForward();
			forward.setPath("board/notice_write.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/NoticeWritePro.bo")) {
			action = new NoticeWriteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/NoticeList.bo")) {
			action = new NoticeListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/NoticeDetail.bo")) {
			action = new NoticeDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/NoticeDeletePro.bo")) {
			action = new NoticeDeleteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/NoticeModifyForm.bo")) {
			action = new NoticeModifyFormAction();
			forward = action.execute(request, response);	
		} else if(command.equals("/NoticeModifyPro.bo")) {
			action = new NoticeModifyProAction();
			forward = action.execute(request, response);	
		
		// Q&A
		} else if(command.equals("/QnaList.bo")) {
			action = new QnaListAction();
			forward = action.execute(request, response);	
		} else if(command.equals("/QnaWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("board/qna_write.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/QnaWritePro.bo")) {
			action = new QnaWriteProAction();
			forward = action.execute(request, response);		
		} else if(command.equals("/QnaDetail.bo")) {
			action = new QnaDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaDeleteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("board/qna_delete.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/QnaDeletePro.bo")) {
			action = new QnaDeleteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaModifyForm.bo")) {
			action = new QnaModifyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaModifyPro.bo")) {
			action = new QnaModifyProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaReplyForm.bo")) {
			action = new QnaReplyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/QnaReplyPro.bo")) {
			action = new QnaReplyProAction();
			forward = action.execute(request, response);
			
		// 리뷰	
		} else if(command.equals("/ReviewWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("board/review_write.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/ReviewWritePro.bo")) {
			action = new ReviewWriteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewList.bo")) {
			action = new ReviewListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewDetail.bo")) {
			action = new ReviewDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewDeleteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("board/review_delete.jsp");
			forward.setRedirect(false); // 생략도 가능
		} else if(command.equals("/ReviewDeletePro.bo")) {
			action = new ReviewDeleteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewModifyForm.bo")) {
			action = new ReviewModifyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewModifyPro.bo")) {
			action = new ReviewModifyProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewReplyForm.bo")) {
			action = new ReviewReplyFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewReplyPro.bo")) {
			action = new ReviewReplyProAction();
			forward = action.execute(request, response);
		}  else if(command.equals("/CommentWritePro.bo")) {
			action = new CommentWriteProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommentDelete.bo")) {
			action = new CommentDeleteProAction();
			forward = action.execute(request, response);
		}
		
		// ----------------------------------------------------------------------
		if(forward != null) {
			if(forward.isRedirect()) { // Redirect 방식
				
				response.sendRedirect(forward.getPath());
			} else { 
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