package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardListAction");
		
		ActionForward forward = null;
		
		// 12/02
		// BoardListService 객체를 통해 게시물 목록 조회 후
		// 조회 결과(List 객체)를 request 객체를 통해 qna_board_list.jsp 페이지로 전달
		//----------------------------------------------------------------------------
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10;
		// 2. 현재 페이지 번호 설정(pageNum 파라미터 사용)
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		// 3. 현재 페이지에서 목록으로 표시할 첫 게시물의 행(레코드) 번호 계산
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산

		//----------------------------------------------------------------------------
		//파라미터로 전달받은 검색어(keyword) 가져와서 변수에 저장
		String keyword = request.getParameter("keyword");

		//만약, 전달받은 검색어가 null 이면 널스트링으로 변경
		if(keyword == null) {
			keyword = "";
		}
		//----------------------------------------------------------------------------
		// BoardListService 클래스 인스턴스 생성
		BoardListService service = new BoardListService();
		
		// 게시물 목록 조회
		List<BoardBean> boardList = service.getBoardList(keyword, startRow, listLimit);
		System.out.println("boardList");
		
		//----------------------------------------------------------------------------
		// 페이징 처리에 사용될 게시물 목록 조회
		// 1. 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
		int listCount = service.getBoardListCount(keyword);
		System.out.println("listCount : " + listCount);
		
		// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
		int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
		
		// 3. 전체 페이지 목록 수 계산
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 

		// 4. 시작 페이지 번호 계산
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		// 5. 끝 페이지 번호 계산
		int endPage = startPage + pageListLimit - 1;
		
		// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
		//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// pageInfo 객체 생성 후 위의 페이지 처리 정보 저장(한객체에 담아서 이동 BoardBean같은)
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		//----------------------------------------------------------------------------
		// 글목록(List 객체)과 페이징처리정보를 request 객체에 저장 - setAttribute()
		request.setAttribute("boardList", boardList); 
		request.setAttribute("pageInfo", pageInfo);
		
		// ActionForward 객체 생성 후 board/qna_board_list.jsp 페이지 포워딩 설정
		forward = new ActionForward();
		forward.setPath("board/qna_board_list.jsp");
		forward.setRedirect(false); // 기본값이라 생략가능
		
		return forward;
	}

}
