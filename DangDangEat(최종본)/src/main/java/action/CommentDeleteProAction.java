package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommentDeleteProService;
import vo.ActionForward;

public class CommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
       		
    	System.out.println("CommentDeleteProAction");
        
    	ActionForward forward = null;
    	
    	
    	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    	int comment_code = Integer.parseInt(request.getParameter("comment_code"));
    	int review_code = Integer.parseInt(request.getParameter("review_code"));
    	try {	 
    		CommentDeleteProService service = new CommentDeleteProService();
    		boolean isDeleteSuccess = service.removeComment(comment_code);
    				   
			
		  if(!isDeleteSuccess) { // 삭제 실패 시 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 성공 시			
				 
				
				forward = new ActionForward();
				forward.setPath("ReviewDetail.bo?review_code=" + review_code + "&pageNum=" + pageNum);
				forward.setRedirect(true);
		  }
	
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;

       }
}