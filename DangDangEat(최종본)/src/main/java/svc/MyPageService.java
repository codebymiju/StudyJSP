package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MyPageService {

	public int selectMemberOrder(String id) {
		System.out.println("MyPageService - selectMemberOrder()");
		
		int orderCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMemberOrder() 메서드
		orderCount = dao.selectMemberOrder(id);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return orderCount;
	}

	public int selectMemberQna(String id) {
		System.out.println("MyPageService - selectMemberQna()");
		
		int qnaCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMemberOrder() 메서드
		qnaCount = dao.selectMemberQna(id);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return qnaCount;
	}

	public int selectMemberReview(String id) {
		System.out.println("MyPageService - selectMemberReview()");
		
		int reviewCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMemberOrder() 메서드
		reviewCount = dao.selectMemberReview(id);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return reviewCount;
	}

	public int selectMemberCoupon(String id) {
		System.out.println("MyPageService - selectMemberReview()");
		
		int couponCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMemberOrder() 메서드
		couponCount = dao.selectMemberCoupon(id);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return couponCount;
	}

}
