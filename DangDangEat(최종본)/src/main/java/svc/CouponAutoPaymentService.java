package svc;

import java.sql.Connection;

import dao.CouponDAO;
import db.JdbcUtil;
import vo.Cp_target;

public class CouponAutoPaymentService {
	
	

	public int autoCouponInsert(String member_id) {

		 
		int insertCount = 0;
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
			
		insertCount = dao.autoCouponInsertForNew(member_id);
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
			
		JdbcUtil.close(con);
		return insertCount;
	
	}
}


