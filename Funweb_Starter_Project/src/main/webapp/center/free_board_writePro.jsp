<%@page import="java.io.File"%>
<%@page import="free.FreeBoardDAO"%>
<%@page import="free.FreeBoardDTO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// request.setCharacterEncoding("UTF-8"); - multi에 인코딩 방식 적었기에 생략가능!
// out.println(request.getParameter("name"));

//주의! 기존 일반 폼 파라미터를 가져오는 request.getParameter() 메서드 사용 불가! 
//=> null 값 전달됨
//out.println(request.getParameter("name")); // null 출력됨

//----------------------------파일 업로드 관련 처리---------------------------------
// 1. 업로드 할 파일이 저장될 프로젝트 상의 경로(= 가상 디렉토리)를 문자열로 지정
//    => webapp/upload 디렉토리 생성(webapp 이 뷰페이지의 최상위 디렉토리이므로 "/upload" 지정)
String uploadPath = "/upload";


// 2. 현재 프로젝트(서블릿)를 처리하는 객체를 서블릿 컨텍스트(Servlet Context) 라고 하며,
// 이 서블릿 컨텍스트를 객체 형태로 가져오기(request 객체의 getServletContext() 메서드 사용)
ServletContext context = request.getServletContext();

// 3. 프로젝트 상의 가상 업로드 디렉토리에 대한 실제 업로드 디렉토리 알아내기
//    => ServletContext 객체의 getRealPath() 메서드 사용
String realPath = context.getRealPath(uploadPath);
// D:\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Funweb_Starter_Project\ upload

// 4. 업로드 할 파일 최대 크기를 정수형으로 지정
// byte(기본 = 1) -> KB(1024 Byte) -> MB(1024 KB) -> 10MB 단위로 변환
int fileSize = 10 * 1024 * 1024; // MB 단위 (10mb)

// 5. MultipartRequest 객체 생성
// 파일 업로드가 포함된 게시물 작성 시 전달받은 파라미터는 request 객체를 통해 전달되지만
// request.getParameter() 메서드를 통해 가져올 수는 없다!
// 대신, MultipartRequest 객체의 getParameter() 메서드를 호출하여 파라미터를 가져올 수 있다!

// => 주의! 파일 업로드 시 form 태그에 enctype="multipart/form-data" 속성 설정 및
// 파라미터 처리에 사용되는 객체를 제공하는 cos.jar 라이브러리 추가 필수! 
// (com.oreilly.servlet 패키지)

MultipartRequest multi = new MultipartRequest(
		request,  // 1) 실제 요청 정보가 포함된 request 객체
		realPath, // 2) 실제 업로드 되는 폴더 경로(서버마다 달라질 수 있음 - 탐색 필요)
		fileSize, // 3) 업로드 파일 최대 크기(기본 단위 : Byte, 작은 단위부터 계산하여 저장)
		"UTF-8",  // 4) 한글 파일명 처리를 위한 인코딩 방식
		new DefaultFileRenamePolicy()
);
// => MultipartRequest 객체 생성 시점에 파일 업로드가 수행됨

// 6. FileBoardDTO 객체 생성하여 업로드 파라미터 데이터 저장
FreeBoardDTO freeBoard = new FreeBoardDTO();

freeBoard.setName(multi.getParameter("name"));
freeBoard.setPass(multi.getParameter("pass"));
freeBoard.setSubject(multi.getParameter("subject"));
freeBoard.setContent(multi.getParameter("content"));

// 단, 파일명을 가져올 때는 단순히 getParameter() 메서드로 처리 불가
// fileBoard.setOriginal_file(multi.getParameter("original_file")); // null 값 저장됨
// 1) 파일명을 관리하는 객체에 접근하여 파일명 목록 중 첫번째 파일명에 대한 속성명 가져오기
// => 단, 파일이 한 개일 경우 생략하고 직접 지정도 가능(original_file)
String fileElement = multi.getFileNames().nextElement().toString();
// out.println(fileElement); // original_file 출력됨
// 2) 1번 과정에서 가져온 속성명을 활용하여 원본 파일명과 실제 업로드 된 파일명 가져오기
// => 2-1) 원본 파일명 : getOriginalFileName() 메서드 활용
//    2-2) 실제 파일명 : getFilesystemName() 메서드 활용
freeBoard.setOriginal_file(multi.getOriginalFileName(fileElement));
freeBoard.setReal_file(multi.getFilesystemName(fileElement));

FreeBoardDAO dao = new FreeBoardDAO();
int insertCount = dao.insertFreeBoard(freeBoard);

if(insertCount > 0) {
	response.sendRedirect("free_board.jsp");
} else {
	File f = new File(realPath, freeBoard.getReal_file());
	
	if(f.exists()) { // 해당 파일이 존재할 경우
	    f.delete();
	}
	%>
	<script>
		alert("글쓰기 실패!");
		history.back();
	</script>
	<%
}
%>