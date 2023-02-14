package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QnaReplyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
			// 업로드 실제 디렉토리(톰캣) 얻어오기
			String realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\Shared\JSP\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\댕댕잇\ upload
			int fileSize = 1024 * 1024 * 10;
			// --------------------------------------------------------------------
			// 파일 업로드 처리(enctype="mutlipart/form-data") 를 위해
			// MultipartRequest 객체 생성 => cos.jar 라이브러리 필요
			MultipartRequest multi = new MultipartRequest(
					request,  // 1) 실제 요청 정보(파라미터)가 포함된 request 객체
					realPath, // 2) 실제 업로드 경로
					fileSize, // 3) 업로드 파일 최대 사이즈
					"UTF-8",  // 4) 한글 파일명 처리 위한 인코딩 방식
					new DefaultFileRenamePolicy() // 5) 중복 파일명을 처리할 객체
			);
			
			// 전달받은 파라미터 데이터를 QnaBean 클래스 인스턴스 생성 후 저장
			QnaBean qna = new QnaBean();
			qna.setMember_id(multi.getParameter("member_id"));
			qna.setQna_pass(multi.getParameter("qna_pass"));
			qna.setQna_subject(multi.getParameter("qna_subject"));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_real_file(multi.getFilesystemName("qna_file"));
			// 입력받지 않고 hidden 속성으로 전달한 ref, lev, seq 값도 저장 필수! 
			qna.setQna_re_ref(Integer.parseInt(multi.getParameter("qna_re_ref")));
			qna.setQna_re_lev(Integer.parseInt(multi.getParameter("qna_re_lev")));
			qna.setQna_re_seq(Integer.parseInt(multi.getParameter("qna_re_seq")));
			qna.setQna_secret(multi.getParameter("qna_secret"));
//			System.out.println(qna);
			
			// 만약, 파일명이 null 일 경우 널스트링으로 교체(답글은 파일 업로드가 선택사항)
			if(qna.getQna_file() == null) {
			   qna.setQna_file("");
			   qna.setQna_real_file("");
			}
			
			// -------------------------------------------------------------------------
			QnaReplyProService service = new QnaReplyProService();
			boolean isWriteSuccess = service.registReplyQna(qna);
			
			// 답글 쓰기 요청 처리 결과 판별
			if(!isWriteSuccess) { // 실패 시
				// 업로드 된 실제 파일 삭제
				File f = new File(realPath, qna.getQna_real_file());
				
				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				
				// 자바스크립트 사용하여 "글쓰기 실패!" 출력 후 이전페이지로 돌아가기
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('답글 쓰기 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				// 포워딩 정보 저장을 위한 ActionForward 객체 생성
				// 포워딩 경로 : QnaList.bo, 포워딩 방식 : Redirect
				// => 페이지번호 전달
				forward = new ActionForward();
				forward.setPath("QnaList.bo?pageNum=" + multi.getParameter("pageNum"));
				forward.setRedirect(true);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward; // BoardFrontController 로 리턴
	}

}
