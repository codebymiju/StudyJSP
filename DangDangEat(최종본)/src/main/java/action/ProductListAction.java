package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductListAction");
		
		ActionForward forward = null;
		
		// ProductListService 객체를 통해 게시물 목록 조회 후
		// 조회 결과(List 객체)를 request 객체를 통해 list.jsp 페이지로 전달
		// ---------------------------------------------------------------------------
		// 페이징 처리를 위한 변수 선언
		int listLimit = 12; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		
		String category = request.getParameter("category");
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		// ---------------------------------------------------------------------------
		// TODO ** 키워드 작업 시 사용!!
		// 파라미터로 전달받은 검색어(keyword) 가져와서 변수에 저장
		String keyword = request.getParameter("keyword");

		// 만약, 전달받은 검색어가 null 이면 널스트링으로 변경(일반 목록일 경우 전체 검색 수행)
		if(keyword == null) {
			keyword = "";
		}
		
		if(category == null) {
			category = "";
		}
		// ---------------------------------------------------------------------------
		// ProductListService 클래스 인스턴스 생성
		ProductListService service = new ProductListService();
		// ProductListService 객체의 getProductList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 검색어, 시작행번호, 목록갯수   리턴타입 : List<ProductBean>(ProductList)
		List<ProductBean> productList = service.getProductList(keyword, category, startRow, listLimit);
		
		// ---------------------------------------------------------
		// 페이징 처리
		// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
		// 1. ProductListService - selectProductListCount() 메서드를 호출하여 전체 게시물 수 조회(페이지 목록 계산에 사용)
		// => 파라미터 : 검색어   리턴타입 : int(listCount)
		int listCount = service.getProductListCount(keyword,category);
//			System.out.println("총 게시물 수 : " + listCount);
		
		// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
		int pageListLimit = 12; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
		
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
		// PageInfo 객체 생성 후 페이징 처리 정보 저장
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		
		System.out.println(pageInfo);
		// ----------------------------------------------------------------------
		// 글목록(List 객체)과 페이징처리정보를 request 객체에 저장 - setAttribute()
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("productList", productList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("category", category);
		request.setAttribute("resultlistCount", productList.size()); //실제 출력되는 상품 갯수
		
		
		
		// ActionForward 객체 생성 후 product/list.jsp 페이지 포워딩 설정
		// => URL 및 request 객체 유지 : Dispatch 방식
		forward = new ActionForward();
		forward.setPath("/product/list.jsp");
		forward.setRedirect(false); // 생략 가능
		
		return forward;
	}

}
