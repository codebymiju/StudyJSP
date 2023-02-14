package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import db.JdbcUtil;
import vo.CouponBean;
import vo.Cp_target;

public class CouponDAO {
	
	private CouponDAO () {};
	private static CouponDAO instance = new CouponDAO();
	
	public static CouponDAO getInstance() {
		return instance;
	}
	
	private Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//----------------------------------
	public int insertCoupon(CouponBean coupon) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
			try {
					
		
					String sql="INSERT INTO coupon values("
								+ " ?, ?, ?, ?, ?, ?, ?, ?, ? "
							+ ")";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, coupon.getCp_code());//쿠폰코드
					pstmt.setString(2, coupon.getCp_name());//쿠폰명
					pstmt.setString(3, coupon.getCp_target().name());//쿠폰타겟
					
					pstmt.setInt(4, coupon.getCp_discount_value());//할인율
					pstmt.setInt(6, coupon.getCp_period());//기간
					pstmt.setInt(7, coupon.getCp_min_price());//최소구매액
					pstmt.setInt(8, coupon.getCp_max_discount());//최대할인액
					pstmt.setInt(9, 1);//쿠폰상태(default 1, / 삭제시 0)
				
				
				if(coupon.getCp_target() == Cp_target.new_member || coupon.getCp_target() ==Cp_target.birth ){
					pstmt.setString(5, "");//시작일 - 널스트링으로 비워둠
					
				}else {
					pstmt.setString(5, coupon.getCp_startdate());//시작일
					
				}
				
				insertCount = pstmt.executeUpdate();
				
				
				
			}catch (Exception e) {
				System.out.println("SLQ구문 오류 - insertCoupon(CouponBean coupon)");
				System.out.println(pstmt);
			}
			return insertCount;

			
			
		}// end of insertCoupon()
	
	
	public boolean selectCouponCodebyUser(String coupon_code) {
		//1) 쿠폰조회
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean canUse = false;
		try {
		
			String sql =  " SELECT cp_code  "
					+ " FROM coupon_view "
					+ " WHERE cp_current_st = ? "//만료되지않고
					+ " AND cp_code = ? "
					+ " AND cp_target = 'event'"//관리자등록쿠폰이며
					+ "	AND cp_status = 1"; //삭제되지않은 쿠폰
			pstmt =  con.prepareStatement(sql);
			
			pstmt.setInt(1, 1);//쿠폰상태가 1이고 
			pstmt.setString(2, coupon_code);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				canUse = true;
			}
			
			
			
		}catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectCouponCodebyUser(String coupon_code)");
			System.out.println(pstmt);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return canUse;
	}
	
	public int InsertCouponCodeToMemCp(String sId , String coupon_code) {
		//2) 쿠폰 INSERT
		PreparedStatement pstmt = null;
		PreparedStatement  pstmt2 = null;
		int insertCount = 0;
		ResultSet rs = null;
		boolean isExist = false; //회원이 가지지 않은 쿠폰
	
		try {//이미 가지고 있는 쿠폰인지 조회
			String sql1=" SELECT  cp_code "
						+ "  FROM mc_view "
					    +" WHERE member_id =  ? AND cp_code = ? ";
			
			pstmt =  con.prepareStatement(sql1);
			pstmt.setString(1, sId);
			pstmt.setString(2, coupon_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
			
			System.out.println("isExist" + isExist);
			System.out.println(sId +"/"+coupon_code);
			String sql2 =  "INSERT INTO member_coupon VALUES ("+"'"+ sId+"'"+", " +"'"+coupon_code+"'"+", 'N') ";
			 pstmt2 =  con.prepareStatement(sql2);
//			pstmt2.setString(1,sId);// member_id
//			pstmt2.setString(2, coupon_code);//cp_code
//			pstmt2.setString(3, "N");//cp_code
			
				if(!isExist) {//이미 가지고 있는 쿠폰이 아니라면 INSERT 
					
					pstmt2 =  con.prepareStatement(sql2);
					insertCount = pstmt2.executeUpdate();
					
				 }
			
			}catch (SQLException e) {
				
				System.out.println("SQL 구문 오류 - InsertCouponCodeToMemCp(String sId , String coupon_code)");
				System.out.println(pstmt);
				
			}catch (Exception e) {
				e.getStackTrace();
			}finally {
				
					JdbcUtil.close(pstmt2); 
					JdbcUtil.close(pstmt); 
			}
		return insertCount;
	}
	
	

	public JSONArray selectMemberCoupon(String sId) {
		// mc_view 에서 존재하는 쿠폰 있는지 검색(자동발급이나 등록 후 사용하지않은 쿠폰)
		
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		String sql = "SELECT * FROM mc_view  "
		+" WHERE  member_id = ?  AND   cp_status = 1  AND  mc_stat = 1 AND mc_used = 'N' ";
	
		
		
		JSONArray odj = new JSONArray();

		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
		
			System.out.println(this.getClass() + "      "+pstmt);
		
			while(rs.next()) {
				
				JSONObject couponOdj = new JSONObject();
				
				couponOdj.put("member_id", rs.getString("member_id")); 				
				couponOdj.put("cp_code", rs.getString("cp_code"));   				
				couponOdj.put("cp_name", rs.getString("cp_name")); 				
				couponOdj.put("cp_discount_value", rs.getInt("cp_discount_value")); 				
				couponOdj.put("cp_period",rs.getInt("cp_period"));      				
				couponOdj.put("cp_min_price",rs.getInt("cp_min_price"));      				
				couponOdj.put("cp_max_discount", rs.getInt("cp_max_discount"));          				
				couponOdj.put("target_sd", rs.getString("target_sd"));				
				couponOdj.put("target_ed", rs.getString("target_ed"));
			
		
				odj.put(couponOdj);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - selectMemberCoupon(String sId)");
			System.out.println(pstmt);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		
		
		return odj;
	}



	public boolean selectCouponCode(String coupon_code) {
		// 관리자 쿠폰등록페이지에서 중복된 쿠폰코드인지 확인
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean isExist = false; //중복 아닐경우
		
		try {
			String sql = "SELECT cp_code FROM coupon "
					+ " WHERE cp_code = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, coupon_code);
			 rs = pstmt.executeQuery();
			if(rs.next()) {
				isExist = true; //중복일 경우
			}
			
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - selectCouponCode(String coupon_code)");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return isExist;
	}
	

	
	
//
	public JSONArray selectCouponFromCouponView() {
		// coupon_view 에서 삭제되지않은 쿠폰 조회
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =  " SELECT *  "
				+ " FROM coupon_view WHERE cp_status = 1 ORDER BY cp_enddate DESC";
			
		
		JSONArray couponList = new JSONArray();

		try {
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				JSONObject couponOdj = new JSONObject();
				couponOdj.put("cp_code", rs.getString("cp_code")); 				
				couponOdj.put("cp_name", rs.getString("cp_name"));   				
				couponOdj.put("cp_target", rs.getString("cp_target"));          				
				couponOdj.put("cp_discount_value", rs.getInt("cp_discount_value")); 				
				couponOdj.put("cp_min_price",rs.getInt("cp_min_price"));      				
				couponOdj.put("cp_max_discount",rs.getInt("cp_max_discount"));      				
				couponOdj.put("cp_startdate", rs.getString("cp_startdate"));				
				if(rs.getString("cp_enddate") == null) {
					couponOdj.put("cp_enddate", "");
				}else {
					couponOdj.put("cp_enddate", rs.getString("cp_enddate"));
				}
				couponOdj.put("cp_period", rs.getInt("cp_period"));
				couponOdj.put("cp_current_st", rs.getInt("cp_current_st"));
			
		
				couponList.put(couponOdj);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 -  selectCouponFromCouponView()");
			System.out.println(pstmt);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return couponList;
	}
	
	public JSONArray selectCouponFromCouponView(String cp_code) { //매서드 오버로딩
		// coupon_view 에서 삭제되지않은 쿠폰 중 cp_code로 조회
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =  " SELECT *  "
				+ " FROM coupon_view "
				+ " WHERE cp_status = 1"
				+ " AND cp_code = ?";
			
		
		JSONArray couponList = new JSONArray();

		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cp_code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				JSONObject couponOdj = new JSONObject();
				couponOdj.put("cp_code", rs.getString("cp_code")); 				
				couponOdj.put("cp_name", rs.getString("cp_name"));   				
				couponOdj.put("cp_target", rs.getString("cp_target"));          				
				couponOdj.put("cp_discount_value", rs.getInt("cp_discount_value")); 				
				couponOdj.put("cp_min_price",rs.getInt("cp_min_price"));      				
				couponOdj.put("cp_max_discount",rs.getInt("cp_max_discount"));      				
				couponOdj.put("cp_startdate", rs.getString("cp_startdate"));				
				if(rs.getString("cp_enddate") == null) {
					couponOdj.put("cp_enddate", "");
				}else {
					couponOdj.put("cp_enddate", rs.getString("cp_enddate"));
				}
				couponOdj.put("cp_period", rs.getInt("cp_period"));
				couponOdj.put("cp_current_st", rs.getInt("cp_current_st"));
			
		
				couponList.put(couponOdj);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 -   selectCouponFromCouponView(String cp_code)");
			System.out.println(pstmt);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		
		
		return couponList;
	}
	

	public int updateCoupon(CouponBean coupon, boolean isDelete) {
		// TODO 
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			if(isDelete) {//삭제시
					String sql =  " UPDATE coupon "
							+ " SET cp_status = ? "
							+ " WHERE cp_code = ?";
					pstmt =  con.prepareStatement(sql);
					pstmt.setInt(1, 0);// 0 = 삭제처리
					pstmt.setString(2, coupon.getCp_code());//cp_code
				

					System.out.println(pstmt+"    - "+this.getClass());
			}else {//수정시
				
				String sql =  " UPDATE coupon "
						+ " SET cp_name = ? "
							+ " ,cp_period = ? "
							+ " ,cp_min_price = ? "
							+ " ,cp_max_discount = ?"
						+ " WHERE cp_code = ? ";
				
				pstmt =  con.prepareStatement(sql);
		
				pstmt.setString(1, coupon.getCp_name());//쿠폰명
				pstmt.setInt(2, coupon.getCp_period());//기간
				pstmt.setInt(3, coupon.getCp_min_price());//최소구매액
				pstmt.setInt(4, coupon.getCp_max_discount());//최대할인액
				pstmt.setString(5, coupon.getCp_code());//쿠폰코드
				
				System.out.println(pstmt+"    - "+this.getClass());
			}
			
			updateCount = pstmt.executeUpdate();
			System.out.println(updateCount +"   -"+this.getClass());
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 -   updateCoupon(CouponBean coupon, boolean isDelete)");
			System.out.println(pstmt);
		}finally {
		
			JdbcUtil.close(pstmt);
			
		}
				
		return updateCount;
	}

	public int insetCouponAll(String cp_code) {
		PreparedStatement pstmt = null;

		int insertCount = 0;
		try {
			
			String sql = "INSERT INTO member_coupon (member_id , cp_code  , mc_used ) "
							+ " SELECT member_id, ? as 'cp_code',  ?  as 'mc_used' "
							+ " FROM member "  
							+" WHERE member_id NOT IN (Select member_id FROM member_coupon WHERE cp_code = ? )";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cp_code);
			pstmt.setString(2, "N");
			pstmt.setString(3, cp_code);
			
			insertCount  = pstmt.executeUpdate();
		
			

	} catch (SQLException e) {
		
		e.printStackTrace();
		System.out.println("SQL 구문 오류 -  insetCouponAll(String cp_code)");
		System.out.println(pstmt);
		
	}finally {
	
		JdbcUtil.close(pstmt);
		
	}
		return insertCount;
	}

	public int autoCouponInsertForNew(String member_id) {
		// 자동발급쿠폰이 있는 경우 회원가입시 자동 발급
		PreparedStatement pstmt = null;

		int insertCount = 0;
		try {
			
			String sql = "INSERT INTO member_coupon (member_id , cp_code  , mc_used ) "
							+ " SELECT  ?  as'member_id' ,  cp_code ,  'N'  as 'mc_used' "
							+ "	FROM coupon "
							+" WHERE cp_status = 1 and (cp_target <> 'event' )";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
	
			
			insertCount  = pstmt.executeUpdate();
		
			

	} catch (SQLException e) {
		
		e.printStackTrace();
		System.out.println("SQL 구문 오류 -  autoCouponInsertForNew(String member_id)");
		System.out.println(pstmt);
		
	}finally {
	
		JdbcUtil.close(pstmt);
		
	}
		return insertCount;
	}

	public int selectCouponCount() {
		// 관리자페이지 쿠폰 개수 조회
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		try {
			
			String sql = "SELECT count(cp_code) FROM coupon_view WHERE cp_status = 1 "
					   + " AND ((cp_current_st = 1 AND cp_target ='event') OR (cp_target IN('new_member','birth')));";
			pstmt = con.prepareStatement(sql);
			rs  = pstmt.executeQuery();
		
			if(rs.next()) {
				insertCount = rs.getInt("count(cp_code)");
			}
			
	} catch (SQLException e) {
		
		e.printStackTrace();
		System.out.println("SQL 구문 오류 -  selectCouponCount()");
		System.out.println(pstmt);
		
	}finally {
	
		JdbcUtil.close(pstmt);
		
	}
		return insertCount;
	}
	

	
}
