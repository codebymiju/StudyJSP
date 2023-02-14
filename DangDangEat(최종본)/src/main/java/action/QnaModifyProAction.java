package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QnaModifyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String realPath = "";
		String deleteFileName = "";
		
		try {
			String uploadPath = "upload"; 
			realPath = request.getServletContext().getRealPath(uploadPath);

			File f = new File(realPath);
			
			if(!f.exists()) { 
				f.mkdir();
			}
			
			int fileSize = 1024 * 1024 * 10;
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
			qna.setQna_code(Integer.parseInt(multi.getParameter("qna_code")));
			qna.setMember_id((String)session.getAttribute("sId"));
			qna.setQna_pass(multi.getParameter("qna_pass"));
			qna.setQna_subject(multi.getParameter("qna_subject"));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_real_file(multi.getFilesystemName("qna_file"));
		
			QnaModifyProService service = new QnaModifyProService();
			boolean isQnaWriter = service.isQnaWriter(qna);
			
			if(!isQnaWriter) { // 수정 권한 없음
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
				
			
				deleteFileName = qna.getQna_real_file();
			} else { 
				boolean isModifySuccess = service.modifyQna(qna);				
			
				if(!isModifySuccess) { // 수정 실패 시 
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
					// 삭제할 파일명을 새 파일의 실제 파일명으로 지정
					deleteFileName = qna.getQna_real_file();
				} else { // 수정 성공 시
					forward = new ActionForward();
					forward.setPath("QnaDetail.bo?qna_code=" + qna.getQna_code() + "&pageNum=" + multi.getParameter("pageNum"));
					forward.setRedirect(true);
					
					
					if(qna.getQna_file() != null) {
						deleteFileName = multi.getParameter("qna_real_file");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			File f = new File(realPath, deleteFileName);
			
			
			if(f.exists()) { // 존재할 경우
				
				f.delete();
			}
		}
		
		return forward;
	}

}