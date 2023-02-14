package svc;

import java.sql.Connection;

import org.json.JSONArray;

import dao.CouponDAO;
import db.JdbcUtil;

public class CouponListService {

	public JSONArray SelectCouponList() {
		JSONArray couponList = null;
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		
		
		couponList = dao.selectCouponFromCouponView();
		
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		return couponList;
		
	}
}
