package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.CartBean;
import vo.WishProductBean;
import vo.cart_wish_proBean;

public class CartDAO {
	private CartDAO() {}
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// -------------------------------------------------------
	
	// 장바구니 추가(위시리스트 없는 경우)
	public int insertCart(CartBean cart) {
		int insertCartCount = 0;
		
		PreparedStatement pstmt = null;
		try {
			
			String sql = "INSERT INTO CART VALUES (null,?,?,?,?,0)";
			
			pstmt = con.prepareStatement(sql);
			
//			System.out.println(cart);
			pstmt.setString(1, cart.getMember_id());
			pstmt.setInt(2, cart.getPro_code());
			pstmt.setInt(3, cart.getCart_amount());
			pstmt.setInt(4, 1);
			
			insertCartCount = pstmt.executeUpdate();
			System.out.println(pstmt + "피에스");
			System.out.println("장바구니 담기 성공!");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertCart()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
		return insertCartCount;
	}

	// 장바구니, 위시리스트 목록 전체조회
	public List<CartBean> selectCartList(String id, boolean isCartExist) {
		List<CartBean> cartList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM cart WHERE member_id=? AND ";
			if(isCartExist == true) { // 장바구니 조회
				sql += "cart_ischecked = ?";
			} else { // 위시리스트 조회
				sql += "cart_wishlist = ?";
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, 1);
			
			rs = pstmt.executeQuery();
			cartList = new ArrayList<CartBean>();
			
			while(rs.next()) {
				CartBean cart = new CartBean();
				cart.setCart_code(rs.getInt("cart_code"));
				cart.setMember_id(rs.getString("member_id"));
				cart.setPro_code(rs.getInt("pro_code"));
				cart.setCart_amount(rs.getInt("cart_amount"));
				cart.setCart_ischecked(rs.getInt("cart_ischecked"));
				cart.setCart_wishlist(rs.getInt("cart_wishlist"));
				cartList.add(cart);
			
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectCartList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return cartList;
	}

	// 제품 유무 장바구니 or 위시리스트 조회
	public boolean isExist(CartBean cart, boolean isCart) {
		boolean isExist = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cart WHERE member_id=? AND pro_code=? ";
		if(isCart) { // 장바구니 조회
			sql += "AND cart_ischecked=?";
		} else { // 위시리스트 조회
			sql += "AND cart_wishlist=?";
		}
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getMember_id());
			pstmt.setInt(2, cart.getPro_code());
			pstmt.setInt(3, 1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - isExist()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return isExist;
	}

	// 장바구니에서 삭제
	public int deleteCart(CartBean cart) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM cart WHERE pro_code=? AND member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getPro_code());
			pstmt.setString(2, cart.getMember_id());
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - deleteCart()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return deleteCount;
	}
	
	
	// update(ischecked나 wishlist 1로 만들기)
	public int updateCart(CartBean cart, boolean isCart) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {// 장바구니 업데이트
			String sql = "UPDATE cart SET cart_ischecked=1, cart_amount = ? WHERE pro_code=? AND member_id=?";
			
			if(!isCart) { // 위시리스트 업데이트
				sql = "UPDATE cart SET cart_wishlist=1 WHERE pro_code=? AND member_id=?";
			}
			pstmt = con.prepareStatement(sql);
			if(isCart) {
				pstmt.setInt(1, cart.getCart_amount());
				pstmt.setInt(2, cart.getPro_code());
				pstmt.setString(3, cart.getMember_id());
			} else {
				pstmt.setInt(1, cart.getPro_code());
				pstmt.setString(2, cart.getMember_id());
			}
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateCart()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
		return updateCount;
	}
	
	
	// update(ischecked나 wishlist 0으로 만들기)
	public int resetCart(CartBean cart, boolean isCart) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE cart SET cart_ischecked=0, cart_amount=? WHERE pro_code=? AND member_id=?";
			
			if(!isCart) { // wishlist = 0 으로 만들기 
				sql = "UPDATE cart SET cart_wishlist=0 WHERE pro_code=? AND member_id=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, cart.getPro_code());
			pstmt.setString(3, cart.getMember_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateCart()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
		return updateCount;
	}

	// 위시리스트 추가(장바구니 없는 경우)
	public int insertWishlist(CartBean cart) {
		int insertWishlistCount = 0;
		
		PreparedStatement pstmt = null;
		try {
			
			String sql = "INSERT INTO CART VALUES (null,?,?,?,0,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cart.getMember_id());
			pstmt.setInt(2, cart.getPro_code());
			pstmt.setInt(3, cart.getCart_amount());
			pstmt.setInt(4, 1);
			
			insertWishlistCount = pstmt.executeUpdate();
			System.out.println("위시리스트 담기 성공!");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertWishlist()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
		return insertWishlistCount;
	}
	
	// view 페이지 통하여 장바구니 상품정보 가져오는 메서드 (단일상품 주문하기)
	   public List<cart_wish_proBean> getViewCartList(String id, boolean isCart) {
	      
	      List<cart_wish_proBean> viewCartList = null;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	    	  String sql = "SELECT * FROM cart_wish_pro_view WHERE member_id = ? ";
	    	  if(isCart) { // 장바구니 조회
	    		  sql += "AND cart_ischecked = ?";
	    	  } else {
	    		  sql += "AND cart_wishlist = ?";
	    	  }
	            
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, id);
	         pstmt.setInt(2, 1);
	         rs = pstmt.executeQuery();
	         
	         viewCartList = new ArrayList<cart_wish_proBean>();
	         while(rs.next()) {
	        	 cart_wish_proBean cart = new cart_wish_proBean();
	             cart.setCart_code(rs.getInt("cart_code"));
	             cart.setMember_id(rs.getString("member_id"));
	             cart.setPro_code(rs.getInt("pro_code"));
	             cart.setCart_amount(rs.getInt("cart_amount"));
	             cart.setCart_ischecked(rs.getInt("cart_ischecked"));
	             cart.setCart_wishlist(rs.getInt("cart_wishlist"));
	             cart.setPro_name(rs.getString("pro_name"));
	             cart.setPro_brand(rs.getString("pro_brand"));
	             cart.setPro_price(rs.getInt("pro_price"));
	             cart.setPro_real_thumb(rs.getString("pro_real_thumb"));
	             viewCartList.add(cart);
	         }
	      } catch (SQLException e) {
	         System.out.println("SQL 구문 오류! - getViewCartList()");
	         e.printStackTrace();
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	      return viewCartList;
	   }// getViewCartList


	public int updateCartNumber(CartBean cart) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE cart SET cart_amount=? WHERE member_id=? AND pro_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getCart_amount());
			pstmt.setString(2, cart.getMember_id());
			pstmt.setInt(3, cart.getPro_code());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateCartNumber()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}



	
}
