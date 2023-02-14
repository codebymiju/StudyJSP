package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BoardWriteProAction_Backup {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String subject = request.getParameter("board_subject");
		String content = request.getParameter("board_content");
		System.out.println("제목 : " + subject);
		System.out.println("내용 : " + content);
		
		// 글쓰기 비즈니스 로직 수행 완 가정
		boolean isWriteSuccess = true;
		
		// 글쓰기 작업 결과 판별
		if(!isWriteSuccess) { // 실패
			
		} else { // 성공
			forward = new ActionForward();
			forward.setPath("BoardList.bo");
			forward.setRedirect(true);
		}
		return forward;
		
	} // main()
} // class
