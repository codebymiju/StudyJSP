package svc;

import java.sql.Connection;
import java.util.List;

import dao.OrderDAO;
import db.JdbcUtil;
import vo.AdminOrderListBean;

public class AdminOrderListService {

   // 관리자 > 주문관리 페이지를 위한 리스트 조회하는 메서드

   public List<AdminOrderListBean> getAdminOrderList() {
      List<AdminOrderListBean> adminOrderList = null;
      
      Connection con = JdbcUtil.getConnection();
      OrderDAO dao = OrderDAO.getInstance();
      dao.setConnection(con);
      
      adminOrderList = dao.getAdminOrderList();
      System.out.println("관리자 페이지를 위한 " + adminOrderList);
      
      return adminOrderList;
   }

}