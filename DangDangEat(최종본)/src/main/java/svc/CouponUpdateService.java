package svc;

import java.sql.Connection;

import org.json.JSONArray;

import dao.CouponDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponUpdateService {

	public int UpdateCoupon(CouponBean coupon , boolean isDelete ){
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		
		
		updateCount = dao.updateCoupon(coupon,isDelete);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			System.out.println("쿠폰 삭제됨 - "+ this.getClass());
		}else {
			JdbcUtil.rollback(con);
		}
		
		return updateCount;
	}
	
	public JSONArray getUpdatedCoupon(String cp_code) {
		JSONArray couponList = null;
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		
		
		couponList = dao.selectCouponFromCouponView(cp_code);
		

		JdbcUtil.close(con);
		return couponList;
		
	}
}

