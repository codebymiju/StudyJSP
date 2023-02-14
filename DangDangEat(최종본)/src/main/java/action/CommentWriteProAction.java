package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CommentWriteProService;
import vo.ActionForward;
import vo.CommentBean;

public class CommentWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CommentWriteProAction");
		
		ActionForward forward = null;		

	try {
		    CommentBean comment = new CommentBean();
		    HttpSession session = request.getSession();
		    int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		    comment.setReview_code(Integer.parseInt(request.getParameter("review_code")));
		    int review_code = comment.getReview_code();
//			String sId = (String)session.getAttribute("sId");
			comment.setMember_id((String)session.getAttribute("sId"));
		    comment.setComment_content(request.getParameter("comment_content"));
	

	        CommentWriteProService service = new CommentWriteProService();
	        boolean isWriteSuccess = service.registComment(comment);
			
	
	        request.setAttribute("comment", comment);
	

	    if(!isWriteSuccess) {		
		
        response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('글쓰기 실패!')");
		out.println("history.back()");
		out.println("</script>");
	     } else {
	    	 forward = new ActionForward();
	    	 forward.setPath("ReviewDetail.bo?review_code=" + review_code + "&pageNum=" + pageNum);
	    	 forward.setRedirect(true);
//	    response.sendRedirect("ReviewDetail.bo?Review_code=" + review_code + "&pageNum=" + pageNum);
		
	}
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		return forward; 
	}

}