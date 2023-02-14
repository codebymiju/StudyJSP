package svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.CouponDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class SearchCouponService {
	
	public boolean selectCouponCode(String sId, String coupon_code) {

		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);		
		boolean canUse = dao.selectCouponCodebyUser(coupon_code);
		
		JdbcUtil.close(con);
		
		return canUse;
	}
	
	public int insertCouponToMemCp(String sId, String coupon_code) {

		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);		
		int insertCount = dao.InsertCouponCodeToMemCp( sId ,  coupon_code);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return insertCount;
	}

}
