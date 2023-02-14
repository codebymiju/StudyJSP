package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.json.JSONArray;

import svc.CouponUpdateService;
import vo.ActionForward;
import vo.CouponBean;

public class CouponUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		

		
	
			boolean isDelete = Boolean.valueOf(request.getParameter("isDelete"));//String을 Boolean으로 변환
			System.out.println(isDelete );

		
	
			CouponBean coupon = new CouponBean();
			coupon.setCp_code(request.getParameter("cp_code"));//쿠폰코드
			coupon.setCp_name(request.getParameter("cp_name"));//쿠폰명
	
			coupon.setCp_period(Integer.parseInt(request.getParameter("cp_period")));//기간
			coupon.setCp_min_price(Integer.parseInt(request.getParameter("cp_min_price")));//최소구매액
			coupon.setCp_max_discount(Integer.parseInt(request.getParameter("cp_max_discount")));//최대할인액
			System.out.println(this.getClass());
			System.out.println(coupon);
			
			CouponUpdateService service = new CouponUpdateService();
			int updateCount = service.UpdateCoupon(coupon, isDelete );
			System.out.println("수정 성공 updateCount - " + updateCount);
			if(updateCount > 0) {// 수정 성공시
				JSONArray couponArr =  service.getUpdatedCoupon(coupon.getCp_code());
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(couponArr);
					
				} catch (IOException e) {

					e.printStackTrace();
				}
			

		}else {// 수정 실패시
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(false);
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			}
			return null;//이걸로 이동안함
	}
}
