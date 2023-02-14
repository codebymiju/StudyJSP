package svc;

import java.sql.Connection;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CouponDAO;
import db.JdbcUtil;

public class SearchExistCouponService {

	public JSONArray selectMemberCoupon(String SId) {
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		
		JSONArray couponList = new JSONArray();
		
		couponList = dao.selectMemberCoupon(SId);
		
		
		JdbcUtil.close(con);
		return couponList;
	}
}
