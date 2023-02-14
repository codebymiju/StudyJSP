package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDeleteProService;
import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {       		
    	System.out.println("NoticeDeleteProAction");        
    	ActionForward forward = null;

    	int notice_code = Integer.parseInt(request.getParameter("notice_code"));
		String pageNum = request.getParameter("pageNum");

		try {
			
		   NoticeDeleteProService service = new NoticeDeleteProService();
		   boolean isNoticeWriter = service.isNoticeWriter(notice_code);
		
		if (!isNoticeWriter) {			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			NoticeDetailService service2 = new NoticeDetailService();
			NoticeBean notice = service2.getNotice(notice_code, false);
			
			boolean isDeleteSuccess = service.removeNotice(notice_code);
			
		  if(!isDeleteSuccess) { // 삭제 실패 시 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 성공 시
							
			forward = new ActionForward();
			forward.setPath("NoticeList.bo?page=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}	
		
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;

       }
}