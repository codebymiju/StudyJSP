<%@page import="board.FileBoardDTO"%>
<%@page import="java.io.File"%>
<%@page import="board.FileBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터 가져와서 확인
FileBoardDTO dto = new FileBoardDTO();

int idx = Integer.parseInt(request.getParameter("idx")); 
String pageNum = request.getParameter("pageNum");
String pass = request.getParameter("pass");


FileBoardDAO dao = new FileBoardDAO();
//업로드 된 파일 삭제를 위해 selectRealFile() 메서드 호출하여 실제 업로드 된 파일명 조회
//=> 주의! 레코드 삭제 전 미리 조회 필요(파라미터 : 글번호(idx), 리턴타입 : String)
String realFile = dao.selectRealFile(idx);


// FileBoardDAO 객체의 deleteFileBoard() 메서드를 호출하여 글 삭제 작업 수행
// => 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteFileCount)
int deleteFileCount = dao.deleteFileBoard(idx, pass); 

// 삭제 결과 판별
// 성공 시 DB에 있는 파일명(=real_fileName)과 일치하는 업로드 경로 상의 실제 파일을 삭제 후  
// 글 목록 페이지(driver.jsp)로 이동하고 (=> 파라미터로 페이지번호 전달 필요)
// 아니면(실패 시) 자바스크립트로 "글 삭제 실패!" 출력 후 이전페이지로 돌아가기
if(deleteFileCount > 0) {
	// =======================파일 삭제 ============================
	// 0. 업로드 된 파일 삭제를 위해 selectRealFile() 메서드 호출하여 실제 업로드 된 파일명 조회
	// => 주의! 레코드 삭제 전 미리 조회 필요
	// 1. 이클립스 상의 가상의 업로드 경로를 변수에 저장
	// => (추가 : 경로 구분자를 직접 지정하는 대신 java.io.File 클래스의 separator 상수 활용)
	String uploadPath = File.separator + "upload";
// 	System.out.println("uploadPath : " + uploadPath);
	
	// 2. ServletContext 객체의 getRealPath() 메서드를 호출하여 가상 경로에 대한 실제 경로 얻기
	String realPath = request.getServletContext().getRealPath(uploadPath);
// 	System.out.println(realPath);

	// java.io.File 클래스를 사용하여 파일이 존재할 경우 해당 파일 삭제
	// 3. File 객체 생성(생성자 파라미터로 파일 경로 및 파일명 전달)
	// <<<<<<<<<<<<<<<<<<<<<  => 11/08 에 이어 계속 작업  >>>>>>>>>>>>>>>>>>>>>>
	File f = new File(realPath, realFile);
	
	// 4. 해당 파일이 존재하는지 여부 판별
	if(f.exists()) { // 해당 파일이 존재할 경우
		// 5. 해당 파일 삭제
		boolean isFileDelteSuccess = f.delete();
		System.out.println("파일 삭제 결과!");
	}
	
	// =============================================================	
	response.sendRedirect("driver.jsp?pageNum=" + pageNum);
} else {
%>
	<script>
		alert("글 삭제 실패!");
		history.back();
	</script>
<% }%>