package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import svc.CouponCountService;
import svc.CouponListService;
import vo.ActionForward;

public class CouponListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CouponListService service = new CouponListService();
		
		System.out.println(this.getClass() + "- 쿠폰 리스트 조회");
		
		//admin_couponList.jsp에서 보여질 쿠폰 리스트 JSONArray형태로 request객체에 저장
		JSONArray CouponList =  service.SelectCouponList();
//		System.out.println(CouponList +" - "+this.getClass());
		request.setAttribute("CouponList", CouponList);
		
		//쿠폰 개수 리턴받음
		CouponCountService service2 = new  CouponCountService();
		int couponCount = service2.SelectCouponCount();
		request.setAttribute("couponCount", couponCount);
		
		forward = new ActionForward();
		forward.setPath("admin/admin_couponList.jsp");//
		forward.setRedirect(false);
		return forward;
	}

}
