package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReviewWriteProAction");
		
		ActionForward forward = null;
		
		try {
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
			// 업로드 실제 디렉토리(톰캣) 얻어오기
			String realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\Shared\JSP\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DangDang\ upload
			int fileSize = 1024 * 1024 * 10;
			
			// --------------------------------------------------------------------
			// 게시물 작성자(= 클라이언트)의 IP 주소를 얻어와야 할 경우
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
			
			// 전달받은 파라미터 데이터를 ReviewBean 클래스 인스턴스 생성 후 저장
			ReviewBean review = new ReviewBean();
			HttpSession session = request.getSession();
//			String sId = (String)session.getAttribute("sId");
			review.setMember_id((String)session.getAttribute("sId"));			
//			review.setReview_pass(multi.getParameter("review_pass"));
			review.setReview_subject(multi.getParameter("review_subject"));
			review.setReview_content(multi.getParameter("review_content"));
			review.setReview_file(multi.getOriginalFileName("review_file"));
			review.setReview_real_file(multi.getFilesystemName("review_file"));
			// -------------------------------------------------------------------------
			ReviewWriteProService service = new ReviewWriteProService();
			boolean isWriteSuccess = service.registReview(review);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isWriteSuccess) { // 실패 시
				// 업로드 된 실제 파일 삭제
				File f = new File(realPath, review.getReview_real_file());
				
				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('글쓰기 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("ReviewList.bo");
				forward.setRedirect(true);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward; // BoardFrontController 로 리턴
	}

}