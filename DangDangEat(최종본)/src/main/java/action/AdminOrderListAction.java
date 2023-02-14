package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminOrderListService;
import vo.ActionForward;
import vo.AdminOrderListBean;

public class AdminOrderListAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      System.out.println("주문 관리 페이지 AdminOrderListAction");
      
      AdminOrderListService service = new AdminOrderListService();
      List<AdminOrderListBean> adminOrderList = service.getAdminOrderList();
      request.setAttribute("adminOrderList", adminOrderList);
      System.out.println("여기까지 넘어왔니 AdminOrderListAction" + adminOrderList);
          
            forward = new ActionForward();
            forward.setPath("admin/admin_orderList.jsp");
            forward.setRedirect(false);
      
      return forward;
   }

}