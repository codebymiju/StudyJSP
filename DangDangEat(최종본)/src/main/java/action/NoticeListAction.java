package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeListService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("NoticeListAction");
		
		ActionForward forward = null;
		
		int listLimit = 10; 
		int pageNum = 1; 
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
//		System.out.println("startRow = " + startRow);
		// ---------------------------------------------------------
		// 파라미터로 전달받은 검색어(keyword) 가져와서 변수에 저장
		String keyword = request.getParameter("keyword");

		// 만약, 전달받은 검색어가 null 이면 널스트링으로 변경(일반 목록일 경우 전체 검색 수행)
		if(keyword == null) {
			keyword = "";
		}
		// ---------------------------------------------------------
		// NoticeListService 클래스 인스턴스 생성
		NoticeListService service = new NoticeListService();
		List<NoticeBean> noticeList = service.getNoticeList(keyword, startRow, listLimit);
		
		// ---------------------------------------------------------
		int listCount = service.getNoticeListCount(keyword);
//			System.out.println("총 게시물 수 : " + listCount);
		
		int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
		
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// ----------------------------------------------------------------------
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageInfo", pageInfo);
		
		// ActionForward 객체 생성 후 notice_list.jsp 페이지 포워딩 설정
		// => URL 및 request 객체 유지 : Dispatch 방식
		forward = new ActionForward();
		forward.setPath("board/notice_list.jsp");
		forward.setRedirect(false); // 생략 가능
		
		return forward;
	}

}