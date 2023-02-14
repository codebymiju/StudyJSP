package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import db.JdbcUtil;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyProAction implements Action {
	// 12/07 1. 글 수정 작업
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		System.out.println("BoardModifyProAction");
		
		String realPath = null; // 업로드 실제 디렉토리
		
		// 5. 수정 작업 결과에 따라 파일 삭제하기
		// 수정 작업 결과에 따라 삭제할 파일이 달라지므로 파일명 저장할 변수 선언
		String deleteFileName = "";
		
		try {
			// 3-(1) 1-(2)전까지 파일도 삭제 가능하도록 multipart 타입으로 파일 관리하도록 업데이트
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스 관리)
			realPath = request.getServletContext().getRealPath(uploadPath); // 업로드 실제 디렉토리(톰캣)
//			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			
			// 4. 파일 경로 추가 
			// 만약, 해당 디렉토리가 존재하지 않을 경우 디렉토리 생성
			// -> java.io.File 클래스 인스턴스 생성
			File f = new File(realPath);
			if(!f.exists()) { // 대상 존재 여부 판별 (존재하지 않으면 경로 생성)
				f.mkdir();
			}
			
			int fileSize = 1024 * 1024 * 10;
			
			// 파일 업로드 처리를 위해 MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath, 
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			// 전달받은 파라미터 데이터를 BoardBean 클래스 인스턴스 생성 후 저장
			// 12/07 !!!!만약 수정할 파일 선택하지 않을 경우 파일명은 null값이 저장됨!!!!!
			BoardBean board = new BoardBean();
			board.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
			board.setBoard_name(multi.getParameter("board_name"));
			board.setBoard_pass(multi.getParameter("board_pass"));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_file(multi.getOriginalFileName("board_file"));
			board.setBoard_real_file(multi.getFilesystemName("board_file")); // 꾸깃22.jpg

			//----------------------------------------------------------------------------------3-(1)
			// 1-(2) 패스워드 일치 여부 확인! (글번호와 패스워드만 넘기면 됨)
			BoardModifyProService service = new BoardModifyProService();
			boolean isBoardWriter = service.isBoardWriter(board);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isBoardWriter) { // 1-(2).1 수정 권한이 없는 경우(=패스워드 틀림) 실패시
				response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
				PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
				
				// 5-(1). 삭제할 파일명을 새 파일명의 실제 파일명으로 지정 (1. 패스워드 오류시)
				deleteFileName = board.getBoard_real_file(); // 중복시 꾸깃.22jpg로 나오는 그 파일!! 이거 지워라
				
			} else { // 1-(2).2 수정 권한 있을 경우
				boolean isModifySuccess = service.modifyBoard(board);
				 if(!isModifySuccess) { // 1-(2).2-1 수정 실패시
					 response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
					 PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
					 out.println("<script>");
					 out.println("alert('글 수정 실패!')");
					 out.println("history.back()");
					 out.println("</script>");
					 
					// 5-(1). 삭제할 파일명을 새 파일명의 실제 파일명으로 지정 (2. 수정실패시)
					deleteFileName = board.getBoard_real_file(); // 중복시 꾸깃.22jpg로 나오는 그 파일!! 이거 지워라
					 
				 } else { // 1-(2).2-2 수정 성공시 redirect방식으로 파라미터로 보드넘과 페이지넘 붙여서 보냄
					 forward = new ActionForward();
					 forward.setPath("BoardDetail.bo?board_num=" + board.getBoard_num() + "&pageNum=" + multi.getParameter("pageNum"));
					 forward.setRedirect(true);
					 
					// 5-(2). 삭제할 파일명을 기존 파일의 실제 파일명으로 지정
					// qna_board_modify에서 hidden 타입으로 넘긴 "board_real_file"(기존파일명) 가져오기!!
					// **** 단, 수정할 새 파일을 선택했을 경우에만 파일명 지정 
					if(board.getBoard_file() != null) {
						deleteFileName = multi.getParameter("board_real_file");
					}
				 }
			}
		} catch (IOException e) {
			System.out.println("BoardModifyProAction - execute()");
			e.printStackTrace();
		} finally { // 5-(3). 예외가 발생하더라도 파일 삭제는 무조건 수행하도록 finally{} 작성
			// 파일 객체 생성 + 파라미터(디렉토리명, 파일명 전달)
			File f = new File(realPath, deleteFileName);
			
			// 해당 디렉토리 및 파일 존재 여부 판별
			if(f.exists()) { // 존재시 삭제
				f.delete(); 
			}
		}
	
		return forward;
	}

}
