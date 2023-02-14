package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponAutoPaymentService;
import svc.CouponRegisterProService;
import svc.CouponTargetInsertService;
import vo.ActionForward;
import vo.CouponBean;
import vo.Cp_target;

public class CouponRegisterProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		

		
		
		ActionForward forward = null;
		System.out.println(this.getClass());
		
		CouponBean coupon = new CouponBean();
		
		System.out.println(
				
				"쿠폰명:"+request.getParameter("coupon_name")+","+
						request.getParameter("coupon_type")+","+
						request.getParameter("coupon_code")+","+
						request.getParameter("discount_value")+","+
						request.getParameter("coupon_target")+","+
						request.getParameter("coupon_start")+","+
						request.getParameter("coupon_period")+","+
						request.getParameter("min_amount")+","+
						request.getParameter("max_discount")+","
							
				);
	

		//쿠폰타겟(Cp_target)에 따라서 DB에 저장되는 쿼리가 다름
		
		coupon.setCp_code(request.getParameter("coupon_code"));//쿠폰코드
		coupon.setCp_name(request.getParameter("coupon_name"));//쿠폰명
		coupon.setCp_discount_value(Integer.parseInt(request.getParameter("discount_value")));//할인율
		coupon.setCp_min_price(Integer.parseInt(request.getParameter("min_amount")));//최소구매액
		coupon.setCp_max_discount(Integer.parseInt(request.getParameter("max_discount")));//최대할인금액
		coupon.setCp_period(Integer.parseInt(request.getParameter("coupon_period")));//쿠폰 유효기간
		coupon.setCp_status(1);//쿠폰 상태
		
		if(request.getParameter("coupon_target") == null) {
			coupon.setCp_target(Cp_target.event);
		}else {
			coupon.setCp_target(Cp_target.valueOf(request.getParameter("coupon_target")));
		}
		
		if(request.getParameter("coupon_start") != null) {
				coupon.setCp_startdate(request.getParameter("coupon_start"));//쿠폰시작일
		}


		CouponRegisterProService service = new CouponRegisterProService();
		int insertCount =  service.inertCoupon(coupon);
		
		//자동발급쿠폰일 경우: 쿠폰시작일:null
				if(coupon.getCp_target() == Cp_target.new_member || coupon.getCp_target()  == Cp_target.birth  ) {
					
					CouponTargetInsertService service2 = new CouponTargetInsertService();
					
					int count = service2.CouponTargetInsert(coupon.getCp_code());
					
					System.out.println(count + "count");
					
					
				}
				
				
		System.out.println(this.getClass() + ": " + coupon);
		
		
		if(insertCount > 0 ) {//쿠폰등록 성공시
			forward = new ActionForward();
			forward.setPath("AdminCouponList.ad");
			forward.setRedirect(false);
		
		}else {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('쿠폰등록에 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		return forward;
		}
	}


