<%@page import="java.io.File"%>
<%@page import="free.FreeBoardDAO"%>
<%@page import="free.FreeBoardDTO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//----------------------------파일 업로드 관련 처리---------------------------------
// 1. 업로드 할 파일이 저장될 프로젝트 상의 경로(= 가상 디렉토리)를 문자열로 지정
String uploadPath = "/upload"; 

// 2. 현재 프로젝트(서블릿)를 처리하는 객체를 서블릿 컨텍스트(Servlet Context) 라고 하며,
// 3. 프로젝트 상의 가상 업로드 디렉토리에 대한 실제 업로드 디렉토리 알아내기
//    => ServletContext 객체의 getRealPath() 메서드 사용
String realPath = request.getServletContext().getRealPath(uploadPath);

// 4. 업로드 할 파일 최대 크기를 정수형으로 지정
int fileSize = 10 * 1024 * 1024;

// 5. MultipartRequest 객체 생성
// 대신, MultipartRequest 객체의 getParameter() 메서드를 호출하여 파라미터를 가져올 수 있다!
MultipartRequest multi = new MultipartRequest(
		request,
		realPath,
		fileSize,
		"UTF-8",
		new DefaultFileRenamePolicy()
);

// 6. FreeBoardDTO 객체 생성하여 업로드 파라미터 데이터 저장
FreeBoardDTO freeBoard = new FreeBoardDTO();

freeBoard.setName(multi.getParameter("name"));
freeBoard.setPass(multi.getParameter("pass"));
freeBoard.setSubject(multi.getParameter("subject"));
freeBoard.setContent(multi.getParameter("content"));

String fileElement = multi.getFileNames().nextElement().toString();
// 2) 1번 과정에서 가져온 속성명을 활용하여 원본 파일명과 실제 업로드 된 파일명 가져오기
// => 2-1) 원본 파일명 : getOriginalFileName() 메서드 활용
//    2-2) 실제 파일명 : getFilesystemName() 메서드 활용
freeBoard.setOriginal_file(multi.getOriginalFileName(fileElement));
freeBoard.setReal_file(multi.getFilesystemName(fileElement));

//글번호, 페이지번호 파라미터도 가져와서 변수에 저장(request 객체 사용 아님!)
int idx = Integer.parseInt(multi.getParameter("idx"));
freeBoard.setIdx(idx);
String pageNum = multi.getParameter("pageNum");

// 수정 업로드 할 파일을 선택하지 않았을 경우
boolean isNewFile = false;  // (= 파일이 null 일 경우) 판별

if(freeBoard.getOriginal_file() == null) { // 변경X
	// 기존 업로드 파일명을 FileBoardDTO 객체에 저장(덮어쓰기)
	freeBoard.setOriginal_file(multi.getParameter("old_original_file"));
	freeBoard.setReal_file(multi.getParameter("old_real_file"));
} else { // 변경O
	isNewFile = true;
}
//FreeBoardDAO 객체의 updateFileBoard() 메서드를 호출하여 글수정 작업 수행
//=> 파라미터 : FreeBoardDTO 객체    리턴타입 : int(updateCount)
FreeBoardDAO dao = new FreeBoardDAO();
int updateCount = dao.updateFreeBoard(freeBoard);

if(updateCount > 0) { // 수정 성공시
	if(isNewFile){
		// 파일 객체에서 파일 위치(String parent, String child)
		File f = new File(realPath, multi.getParameter("old_real_file"));
		if(f.exists()) { // 위의 파일이 존재한다면 삭제
			f.delete();
		}
	}
	// 수정 성공하면 글 상세보기 페이지로 이동
	response.sendRedirect("free_board_content.jsp?idx=" + idx + "&pageNum=" + pageNum );
} else { // 수정 실패시
	if(isNewFile) {
		// 업로드 실패한 게시물에 대한 등록된 파일 삭제(게시물 업로드 실패해도 파일은 올라감!)
		File f = new File(realPath, freeBoard.getReal_file());
	 
		if(f.exists()) {
			f.delete();
		}
	} 
	%>
	<script>
		alert("글수정 실패!");
		history.back();
	</script>
<%}%>
