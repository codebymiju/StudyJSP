package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import svc.CouponListService;
import svc.CouponUpdateService;
import vo.ActionForward;
import vo.CouponBean;

public class CouponDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CouponUpdateService service = new CouponUpdateService();
		CouponBean coupon = new CouponBean();
		
		boolean isDelete = Boolean.valueOf(request.getParameter("isDelete"));
		coupon.setCp_code(request.getParameter("cp_code"));
		int	deleteCount = service.UpdateCoupon(coupon, isDelete);

		System.out.println("isDelete : " + isDelete);
		
		
		if(deleteCount > 0) {
			forward = new ActionForward();
			forward.setPath("AdminCouponList.ad");
			forward.setRedirect(false);
			System.out.println("쿠폰 삭제 성공");
			
		}else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('쿠폰 삭제에 실패했습니다')");
				out.println("history.back()");
				out.println("</script>");
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
	
		
		return forward;
	}

}
