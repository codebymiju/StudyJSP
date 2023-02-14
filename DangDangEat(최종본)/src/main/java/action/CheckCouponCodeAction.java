package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CheckCouponCodeService;
import vo.ActionForward;

public class CheckCouponCodeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String coupon_code = request.getParameter("coupon_code");
			System.out.println("coupon_code:"+coupon_code);
			CheckCouponCodeService service = new CheckCouponCodeService();
			boolean isExist = service.isExistCode(coupon_code);
					
				System.out.println("isExist : " +  isExist);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(isExist);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
