package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class QnaListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
             System.out.println("QnaListAction");
		
		ActionForward forward = null;
		boolean test = false;
		if(request.getParameter("pd") != null) {
			test = true;
		}
		// ---------------------------------------------------------------------------
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
//		System.out.println("startRow = " + startRow);
		// --------------------------------------------------------
		// 
		String keyword = request.getParameter("keyword");

		// 만약, 전달받은 검색어가 null 이면 널스트링으로 변경(일반 목록일 경우 전체 검색 수행)
		if(keyword == null) {
			keyword = "";
		}
				
		// QnaListService 클래스 인스턴스 생성
		QnaListService service = new QnaListService();
		
		// QnaListService 객체의 getQnaList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 시작행번호, 목록갯수   리턴타입 : List<QnaBean>(qnaList)
		List<QnaBean> qnaList = service.getQnaList(keyword, startRow, listLimit);
				
		// ---------------------------------------------------------
		// 페이징 처리
		int listCount = service.getQnaListCount(keyword);
//			System.out.println("총 게시물 수 : " + listCount);
				
		int pageListLimit = 10;		
		
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 
		
        int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// PageInfo 객체 생성 후 페이징 처리 정보 저장
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// ----------------------------------------------------------------------
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("pageInfo", pageInfo);
		
		// ActionForward 객체 생성 후 board/qna_list.jsp 페이지 포워딩 설정
		// => URL 및 request 객체 유지 : Dispatch 방식
		if(test) {
			forward = new ActionForward();
			forward.setPath("board/qna_list_pd.jsp");
			forward.setRedirect(false); // 생략 가능
		} else {
			forward = new ActionForward();
			forward.setPath("board/qna_list.jsp");
			forward.setRedirect(false); // 생략 가능
		}
		
		return forward;
	}

}