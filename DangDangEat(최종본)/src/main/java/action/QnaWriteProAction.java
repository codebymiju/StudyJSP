package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QnaWriteProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		try {
			String uploadPath = "upload"; 			
			String realPath = request.getServletContext().getRealPath(uploadPath);
			// D:\Shared\JSP\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DangDangEat\ upload
			int fileSize = 1024 * 1024 * 10;
			
			String writerIpAddr = request.getRemoteAddr();
			System.out.println("작성자 IP 주소 : " + writerIpAddr);
			// --------------------------------------------------------------------			
			MultipartRequest multi = new MultipartRequest(
					request,  
					realPath, 
					fileSize, 
					"UTF-8", 
					new DefaultFileRenamePolicy() 
			);
			
			QnaBean qna = new QnaBean();
			HttpSession session = request.getSession();
//			String sId = (String)session.getAttribute("sId");
			qna.setMember_id((String)session.getAttribute("sId"));
			qna.setQna_pass(multi.getParameter("qna_pass"));
			qna.setQna_subject(multi.getParameter("qna_subject"));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_secret(multi.getParameter("qna_secret"));
			
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_real_file(multi.getFilesystemName("qna_file"));
//			System.out.println(qna);			
			
			QnaWriteProService service = new QnaWriteProService();
			boolean isWriteSuccess = service.registQna(qna);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isWriteSuccess) { // 실패 시				
				File f = new File(realPath, qna.getQna_real_file());						
				if(f.exists()) { // 존재할 경우					
					f.delete();
				}			
			
				response.setContentType("text/html; charset=UTF-8");
			
				PrintWriter out = response.getWriter();
								
				out.println("<script>");
				out.println("alert('글쓰기 실패!')");
				out.println("history.back()");
				out.println("</script>");
				// ActionForward 객체 생성하지 않음!! => null 값 전달
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("QnaList.bo");
				forward.setRedirect(true);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward; // BoardFrontController 로 리턴
	}

}