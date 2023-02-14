package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDetailService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductDetailAction");
		
		ActionForward forward = null;
		
		//상세정보에 필요한 상품코드(pro_code) 가져오기
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		System.out.println(pro_code);
		
		//ProductDetailService 클래스의 인스턴스 생성 및 getBoard() 메서드를 호출하여
		// 글 상세정보 조회 작업 요청
		// => 주의! ProductModifyFormService 클래스는 생성하지 않는다! (중복이므로 불필요)
		// => 파라미터 : 글번호  리턴타입 : ProductBean(product)
		ProductDetailService service = new ProductDetailService();
		ProductBean product = service.getBoard(pro_code);
//		System.out.println(board);
		
		// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("product", product);
		
		// ActionForward 객체를 통해 product_modifyForm.jsp 페이지 포워딩 설정
		// => URL 유지 및 request 객체 유지를 위해 Dispatch 방식 포워딩
		forward = new ActionForward();
		forward.setPath("product/list_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
