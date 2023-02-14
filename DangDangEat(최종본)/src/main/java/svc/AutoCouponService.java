package svc;

import java.sql.Connection;
import dao.CouponDAO;
import db.JdbcUtil;

public class AutoCouponService {
	public void createAutoCoupon() {
		
		//자동 쿠폰 
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		
		dao.setConnection(con);
		
		
		
		
		
		
		JdbcUtil.close(con);
		
	}
	
	

}
