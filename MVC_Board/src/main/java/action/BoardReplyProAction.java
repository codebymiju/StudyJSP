package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardReplyProService;
import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardReplyProAction");
		
		ActionForward forward = null; // 만들고 return까지 필수!
		
		try {
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스 관리)
			String realPath = request.getServletContext().getRealPath(uploadPath); // 업로드 실제 디렉토리(톰캣)
			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			int fileSize = 1024 * 1024 * 10;
			
			//-------------------------------------------------------------
			// 게시물 작성자(= 클라이언트)의 IP 주소를 얻어와야 할 경우
			String writerIpAddr = request.getRemoteAddr();
			System.out.println("작성자 ip 주소 " + writerIpAddr);
			// 0:0:0:0:0:0:0:1
			//-------------------------------------------------------------
			// 파일 업로드 처리를 위해 MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath, 
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			// 전달받은 파라미터 데이터를 BoardBean 클래스(데이터 관리) 인스턴스 생성 후 저장
			BoardBean board = new BoardBean();
			board.setBoard_name(multi.getParameter("board_name"));
			board.setBoard_pass(multi.getParameter("board_pass"));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_file(multi.getOriginalFileName("board_file"));
			board.setBoard_real_file(multi.getFilesystemName("board_file"));
			// 12/09 추가
			board.setBoard_re_ref(Integer.parseInt(multi.getParameter("board_re_ref")));
			board.setBoard_re_lev(Integer.parseInt(multi.getParameter("board_re_lev")));
			board.setBoard_re_seq(Integer.parseInt(multi.getParameter("board_re_seq")));
			
			// 12/07 6.
			//만약, 파일명이 null 일 경우 널스트링으로 교체(답글은 파일 업로드가 선택사항)
			if(board.getBoard_file() == null) {
				board.setBoard_file("");
				board.setBoard_real_file("");
			}
			
			//=============================================================================
			// 12/07 6. 서비스명 변경
			BoardReplyProService service = new BoardReplyProService();
			boolean isWriteSuccess = service.registReplyBoard(board);
			
			// 글쓰기 요청 처리결과 판별
			if(!isWriteSuccess) { // 실패시
				
				// 12/07 추가 + 업로드 된 실제 파일 삭제
				File f = new File(realPath, board.getBoard_real_file());
				
				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) { // 존재시 삭제
					f.delete(); 
				}
				
				// 자바 클래스 내에서 출력스트림을 활용하여 HTML 태그 출력해야함
				// 응답 데이터 생성을 위해 response객체(응답객체) 활용
				// 아래 두줄 : 자바스크립트 만드는 코드!!! + out.println();
				response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
				PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
				out.println("<script>");
				out.println("alert('답글 쓰기 실패!')");
				out.println("history.back()");
				out.println("</script>");
				
			// forward가 없기에 null값 전달 > 출력스트림 통해 response객체에 담아
			// 컨트롤러로 이동 doProcess() 끝까지 이동 후 빠져나감(실행 안됨!)
			// 클라이언트에게 jsp 형식으로 전달됨! 
			// (Action 클래스에서 이동하는 일은 없다!! 무조건 controller 까지 간다!!!)
				
			} else { // 성공시
				// 06. pageNum도 같이 넘긴다
				forward = new ActionForward();
				forward.setPath("BoardList.bo?pageNum=" + multi.getParameter("pageNum"));
				forward.setRedirect(true);
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
