<%@page import="java.io.File"%>
<%@page import="free.FreeBoardDAO"%>
<%@page import="free.FreeBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터 가져와서 확인
FreeBoardDTO dto = new FreeBoardDTO();

int idx = Integer.parseInt(request.getParameter("idx"));
String pageNum = request.getParameter("pageNum");
String pass = request.getParameter("pass");

//업로드 된 파일 삭제를 위해 selectRealFile() 메서드 호출하여 실제 업로드 된 파일명 조회
//=> 주의! 레코드 삭제 전 미리 조회 필요(파라미터 : 글번호(idx), 리턴타입 : String)
FreeBoardDAO dao = new FreeBoardDAO();
String realFile = dao.selectRealFile(idx);

// FileBoardDAO 객체의 deleteFileBoard() 메서드를 호출하여 글 삭제 작업 수행
// => 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteFileCount)
int deleteFreeCount = dao.deleteFreeBoard(idx, pass);

// 삭제 결과 판별
// 성공 시 DB에 있는 파일명(=real_fileName)과 일치하는 업로드 경로 상의 실제 파일을 삭제 후  
// 글 목록 페이지(driver.jsp)로 이동하고 (=> 파라미터로 페이지번호 전달 필요)
// 아니면(실패 시) 자바스크립트로 "글 삭제 실패!" 출력 후 이전페이지로 돌아가기
if(deleteFreeCount > 0) {
	
	String uploadPath = File.separator + "upload";
	String realPath = request.getServletContext().getRealPath(uploadPath);
	
	// 파일이 존재하는지 파일경로 및 파일명 전달
	File f = new File(realPath, realFile);
	
	// 해당 파일 존재하는지 여부
	if (f.exists()) {
		boolean isFileDeleteSuccess = f.delete();
		System.out.println("파일 삭제 결과!");
	}
	// =============================================================	
	response.sendRedirect("free_board.jsp?pageNum=" + pageNum);
} else {
%>
	<script>
		alert("글 삭제 실패!");
		history.back();
	</script>
<% }%>