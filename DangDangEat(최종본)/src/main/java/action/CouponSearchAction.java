package action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import svc.SearchCouponService;
import svc.SearchExistCouponService;
import vo.ActionForward;

public class CouponSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
	
		String cp_code = request.getParameter("cp_code");
		HttpSession session = request.getSession(false);
		String sId =  (String)session.getAttribute("sId");
		
		System.out.println(" sId : "+ sId+"    -" +this.getClass());
	
		
		SearchCouponService service = new SearchCouponService();
		boolean canUse = service.selectCouponCode(sId, cp_code);
		System.out.println("canUse : " + canUse);
		try {
			if(canUse) {//사용 가능한 쿠폰인경우
				//1) insert
				int insertCount = service.insertCouponToMemCp(sId, cp_code);
				
				if(insertCount > 0) {
					//1) member_coupon테이블에서 조회 후 결과 ajax로 넘겨줌
					SearchExistCouponService service2 = new SearchExistCouponService();
					JSONArray couponList = service2.selectMemberCoupon(sId);
						
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(couponList);
						
				}else {//이미 등록된 쿠폰
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(true);
				}
				
			}else {//사용불가능한 쿠폰인 경우
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
			
				out.println(canUse); //false 리턴
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return forward;
	}

}
