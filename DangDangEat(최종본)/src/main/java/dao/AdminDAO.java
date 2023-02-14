package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.WishProductBean;

public class AdminDAO {
	
	private AdminDAO() {}
	
	private static AdminDAO instance = new AdminDAO();
	
	public static AdminDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// -------------------------------------------------------
	
	// Wish Top
	public List<WishProductBean> getWishProductList() {
		List<WishProductBean> wishlist = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT *, COUNT(pro_code) AS wish_count FROM wish_product_view WHERE cart_wishlist=? GROUP BY pro_code ORDER BY wish_count DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			wishlist = new ArrayList<WishProductBean>();
			
			while(rs.next()) {
				WishProductBean wish = new WishProductBean();
				wish.setPro_code(rs.getInt("pro_code"));
				wish.setPro_name(rs.getString("pro_name"));
				wish.setCate_code(rs.getInt("cate_code"));
				
				if(rs.getString("pro_brand").equals("1")) {
					wish.setPro_brand("-");
				} else if (rs.getString("pro_brand").equals("2")) {
					wish.setPro_brand("LILY'S KITCHEN");
				} else if (rs.getString("pro_brand").equals("3")) {
					wish.setPro_brand("PETSGREEN");
				} else if (rs.getString("pro_brand").equals("4")) {
					wish.setPro_brand("RICHZ BOX");
				} else {
					wish.setPro_brand("LORENZ");					
				}
				
				wish.setPro_price(rs.getInt("pro_price"));
				wish.setCart_wishlist(rs.getInt("cart_wishlist"));
				wish.setWish_count(rs.getInt("wish_count"));
				wishlist.add(wish);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - getWishProductList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return wishlist;
	}


	public int getMemberMonthlyJoin() {
		int joinCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(member_id) AS joincount FROM member WHERE DATE_FORMAT(member_date, '%m') = DATE_FORMAT(sysdate(), '%m')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				joinCount = rs.getInt("joincount");
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - getMemberMonthlyJoin()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		
		return joinCount;
	}


	public int getOrderMonthly() {
		int orderCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(pay_number) AS ordercount FROM payments "
					+ "WHERE order_code IN "
					+ "(SELECT order_code FROM orders "
					+ "WHERE DATE_FORMAT(order_date, '%m') = DATE_FORMAT(sysdate(), '%m'))";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderCount = rs.getInt("ordercount");
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - getOrderMonthly()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		return orderCount;
	}


	public int getSalesTotalMonthly() {
		int salesTotal = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT SUM(pay_amount) AS salestotal FROM payments "
					+ "WHERE order_code IN "
					+ "(SELECT order_code FROM orders "
					+ "WHERE DATE_FORMAT(order_date, '%m') = DATE_FORMAT(sysdate(), '%m'))";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				salesTotal = rs.getInt("salestotal");
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - getSalesTotalMonthly()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		
		return salesTotal;
	}


	public int getNotAnswerCount() {
		int notAnswerCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(qna_code) AS isnotanswer FROM qna WHERE qna_status = '답변대기'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notAnswerCount = rs.getInt("isnotanswer");
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - getNotAnswerCount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		
		return notAnswerCount;
	}
}
