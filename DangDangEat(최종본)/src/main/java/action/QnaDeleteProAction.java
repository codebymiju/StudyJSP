package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDeleteProService;
import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// 글번호, 패스워드 파라미터 가져오기
		int qna_code = Integer.parseInt(request.getParameter("qna_code"));
//		String qna_pass = request.getParameter("qna_pass");
//		System.out.println(qna_code + ", " + qna_pass);
		System.out.println(request.getParameter("pageNum"));
		
		try {
			QnaDeleteProService service = new QnaDeleteProService();
			boolean isQnaWriter = service.isQnaWriter(qna_code);
			System.out.println("isQnaWriter : " + isQnaWriter);
						
			if(!isQnaWriter) { // 삭제 권한 없음
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 권한 있음				
				QnaDetailService service2 = new QnaDetailService();
				QnaBean qna = service2.getQna(qna_code);
				// => 주의! 레코드 삭제 전 정보 조회 먼저 수행해야한다!
				
				boolean isDeleteSuccess = service.removeQna(qna_code);
								
				if(!isDeleteSuccess) { // 삭제 실패 시 
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
				} else { // 삭제 성공 시
					String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
					String realPath = request.getServletContext().getRealPath(uploadPath);
					
					System.out.println("QnaBean : " + qna);
					System.out.println("realPath : " + realPath);
					
					// 업로드 된 실제 파일 삭제
//					File f = new File(realPath, qna.getQna_real_file());
					
					// 해당 디렉토리 및 파일 존재 여부 판별
//					if(f.exists()) { 
//						f.delete();
//					}
					
					forward = new ActionForward();
					forward.setPath("QnaList.bo?pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}