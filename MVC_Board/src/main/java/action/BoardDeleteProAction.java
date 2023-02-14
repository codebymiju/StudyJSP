package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
//12/05 03-(1)
public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardDeleteProAction");
		
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_pass = request.getParameter("board_pass");
		
		try { // 12/05 03-(2)
			BoardDeleteProService service = new BoardDeleteProService();
			boolean isBoardWriter = service.isBoardWriter(board_num, board_pass);
			
			// 만약, 게시물 삭제 권한이 없는 경우(=패스워드 틀림) 자바스크립트 이용하여 메시지출력&이전페이지
			if(!isBoardWriter) {
				response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
				PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
				out.println("<script>");
				out.println("alert('삭제 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 권한 있음(=패스워드 일치) // 12/05 03-(3)
				
				// 12/07 추가 5. 삭제 성공시 저장되어 있던 파일 삭제
				BoardDetailService service2 = new BoardDetailService();
				BoardBean board = service2.getBoard(board_num, false);
				//---------------------------------------------------------------
				boolean isDeleteSucess = service.removeBoard(board_num);
				
				if(!isDeleteSucess) { // 삭제 실패
					response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
					PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
					out.println("<script>");
					out.println("alert('삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
				} else { // 삭제 성공시 "BoardList.bo" 로 이동 // 12/05 03-(3) 끝!
					
					// 12/07 추가 5. 삭제 성공시 저장되어 있던 파일 삭제------------------------
					String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스 관리)
					String realPath = request.getServletContext().getRealPath(uploadPath);
					
					// 업로드 된 실제 파일 삭제
					File f = new File(realPath, board.getBoard_real_file());
					
					// 해당 디렉토리 및 파일 존재 여부 판별
					if(f.exists()) { // 존재시 삭제
						f.delete(); 
					}
					//-----------------------12/07 5.----------------------
					forward = new ActionForward();
					forward.setPath("BoardList.bo?pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true); // redirect
				}
			}
		} catch (IOException e) {
			System.out.println("BoardDeleteProAction - execute()");
			e.printStackTrace();
		}
		
		return forward;
	}

}
