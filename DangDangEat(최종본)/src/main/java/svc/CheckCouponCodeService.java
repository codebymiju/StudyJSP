package svc;

import java.sql.Connection;

import dao.CouponDAO;
import db.JdbcUtil;

public class CheckCouponCodeService {

	public boolean isExistCode(String coupon_code) {
		boolean  isExist = false;
		
		CouponDAO dao = CouponDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		dao.setConnection(con);
		
		isExist = dao.selectCouponCode(coupon_code);
		
		JdbcUtil.close(con);
		return isExist;
	}

}
