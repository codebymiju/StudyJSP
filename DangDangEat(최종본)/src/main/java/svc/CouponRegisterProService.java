package svc;

import java.sql.Connection;

import dao.CouponDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponRegisterProService {
	public int inertCoupon(CouponBean coupon) {
		int insertCount = 0;
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		
		//쿠폰 INSERT작업 - 관리자
		insertCount = dao.insertCoupon(coupon);
		
		
		
		if(insertCount > 0 ) {
			JdbcUtil.commit(con);//커밋
		}else {
			
			JdbcUtil.rollback(con);//롤백
		}
		
		JdbcUtil.close(con);
		return insertCount;
		
	}

	
	
}