package svc;

import java.sql.Connection;
import java.util.List;

import dao.OrderDAO;
import db.JdbcUtil;
import vo.MemberBean;
import vo.cart_productBean;

public class OrderListService {

	// 주문서에 회원의 정보를 가져오는 메서드
	public List<MemberBean> selectMemberList(String id) {
		List<MemberBean> memberList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		memberList = dao.selectMemberList(id);
		
		JdbcUtil.close(con);
		
		return memberList;
	}
	
	// 장바구니에 있는 상품 정보를 가져오는 메서드 (cart_product view table)
	public List<cart_productBean> getViewCartList(String id, int proCode, int cartCode) {
		List<cart_productBean> viewCartList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		viewCartList = dao.getViewCartList(id, proCode, cartCode);
		
		JdbcUtil.close(con);
		
		return viewCartList;
	}
}
