package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeModifyProService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("NoticeWriteProAction");
		
		ActionForward forward = null;
		
		try {			
			
			// 전달받은 파라미터 데이터를 NoticeBean 클래스 인스턴스 생성 후 저장
			NoticeBean notice = new NoticeBean();
			notice.setNotice_subject(request.getParameter("notice_subject"));
			notice.setNotice_content(request.getParameter("notice_content"));
			notice.setNotice_code(Integer.parseInt(request.getParameter("notice_code")));
			
			NoticeModifyProService service = new NoticeModifyProService();
			boolean isModifySuccess = service.modifyNotice(notice);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isModifySuccess) { // 실패 시
				
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("NoticeList.bo");
				forward.setRedirect(true);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward; // BoardFrontController 로 리턴
	}

}