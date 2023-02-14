package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.AuthBean;
import vo.MemberBean;

public class MemberDAO {

   private static MemberDAO instance = new MemberDAO(); // 인스턴스
   
   private MemberDAO() {}; // 기본생성자
   
   public static MemberDAO getInstance() { // 인스턴스 가져오는 메서드
      return instance;
   }
   
   private Connection con; // Connection 객체

   public void setConnection(Connection con) { // Connection 객체 받아오는 메서드
      this.con = con;
   }
   
// 회원가입
	public int insertMember(MemberBean member) {
		System.out.println("insertMember");
		int insertCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO member VALUES"
					+ " (?,?,?,?,?,?,?,?,now(),0,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pass());
			pstmt.setString(3, member.getMember_email());
			pstmt.setString(4, member.getMember_name());
			pstmt.setString(5, member.getMember_mobile());
			pstmt.setString(6, member.getMember_addr1());
			pstmt.setString(7, member.getMember_addr2());
			pstmt.setString(8, member.getMember_postcode());
			pstmt.setDate(9, member.getMember_birth());
			if(member.getMember_id().equals("admin")) {
				pstmt.setString(10, "Y");
			} else {
				pstmt.setString(10, "N");
			}
			pstmt.setString(11, "Y");
			pstmt.setString(12, "N");
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 : insertMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return insertCount;
	}

	// id 중복확인
		public boolean selectMemberId(MemberBean member) {
			      System.out.println("selectMemberId");
			boolean isCheck = false;
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null, rs2= null;

			try {
				if(member.getMember_id() != null) {
					String sql = "SELECT Member_id FROM member WHERE Member_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member.getMember_id());
					rs = pstmt.executeQuery();
					System.out.println("id확인" + member.getMember_id());
					if(rs.next()) {
						isCheck = true;
					}
					
				}
				
				if(member.getMember_email() != null) {
					String sql = "SELECT Member_email FROM member WHERE Member_email=?";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setString(1, member.getMember_email());
					rs2 = pstmt2.executeQuery();
					if(rs2.next()) {
						isCheck = true;
					}
					
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 : selectMemberId()");
				e.printStackTrace();
			} finally {
				if(member.getMember_id() != null) {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
				if(member.getMember_email() != null) {
					JdbcUtil.close(rs2);
					JdbcUtil.close(pstmt2);
				}

			}

			return isCheck;
		}
   
   // 로그인, 패스워드 확인, 비밀번호 찾기 회원 확인(isPass = false)
   public boolean selectMember(MemberBean member, boolean isPass) {
      System.out.println("MemberDAO - selectMember()");
      
      boolean isRightMember = false;
      
//      System.out.println(member);
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
    	 if(isPass) {
    		 String sql = "SELECT * "
    				 + "         FROM member"
    				 + "         WHERE member_id=? AND member_pass=?"
    				 + "				AND member_status=?";
    		 
    		 pstmt = con.prepareStatement(sql);
    		 pstmt.setString(1, member.getMember_id());
    		 pstmt.setString(2, member.getMember_pass());
    		 pstmt.setString(3, "Y"); // 회원 상태 "Y"
    		 
    	 } else {
    		 String sql = "SELECT * "
    				 + "         FROM member"
    				 + "         WHERE member_id=? AND member_email=? AND member_name=?"
		    		 + "				AND member_status=?";
    		 
    		 pstmt = con.prepareStatement(sql);
    		 pstmt.setString(1, member.getMember_id());
    		 pstmt.setString(2, member.getMember_email());
    		 pstmt.setString(3, member.getMember_name());
    		 pstmt.setString(4, "Y"); // 회원 상태 "Y"
    		 
    	 }
    	 
    	 rs = pstmt.executeQuery();
         
         if(rs.next()) {
        	 isRightMember = true;
         }
      } catch (SQLException e) {
         System.out.println("SQL 구문 오류! - selectMember()");
         e.printStackTrace();
      } finally {
         // DB 자원 반환
         JdbcUtil.close(pstmt);
         JdbcUtil.close(rs);
      }
      
      return isRightMember;
   }

   // 마이페이지 회원 정보 조회
   public MemberBean selectMember(String id) {
      System.out.println("MemberDAO - selectMember()");
      
      MemberBean member = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         String sql = "SELECT * FROM member WHERE member_id=?";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            member = new MemberBean();
            member.setMember_id(rs.getString("member_id"));
            member.setMember_pass(rs.getString("member_pass"));
            member.setMember_email(rs.getString("member_email"));
            member.setMember_name(rs.getString("member_name"));
            member.setMember_mobile(rs.getString("member_mobile"));
            member.setMember_addr1(rs.getString("member_addr1"));
            member.setMember_addr2(rs.getString("member_addr2"));
            member.setMember_postcode(rs.getString("member_postcode"));
            member.setMember_date(rs.getDate("member_date"));
            member.setMember_point(rs.getInt("member_point"));
            member.setMember_birth(rs.getDate("member_birth"));
            member.setMember_admin(rs.getString("member_admin"));
            member.setMember_status(rs.getString("member_status")); 
            member.setMember_authStatus(rs.getString("member_authStatus"));
            System.out.println(member);
         }
      } catch (SQLException e) {
         System.out.println("SQL 구문 오류! - selectMember()");
         e.printStackTrace();
      } finally {
         // DB 자원 반환
         JdbcUtil.close(pstmt);
         JdbcUtil.close(rs);
      }
      
      return member;
   }
   
  // 회원 정보 수정 (회원 마이페이지)
   public int modifyMember(MemberBean member) {
      System.out.println("MemberDAO - modifiMember()");
      
      int updateCount = 0;
      PreparedStatement pstmt = null;
      
      try {
         String sql = "UPDATE member "
               + "      SET member_pass=?, "
               + "         member_email=?, "
               + "         member_mobile=?, "
               + "         member_addr1=?, "
               + "         member_addr2=?, "
               + "         member_postcode=? "
               + "      WHERE "
               + "         member_id=?";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, member.getMember_pass());
         pstmt.setString(2, member.getMember_email());
         pstmt.setString(3, member.getMember_mobile());
         pstmt.setString(4, member.getMember_addr1());
         pstmt.setString(5, member.getMember_addr2());
         pstmt.setString(6, member.getMember_postcode());
         pstmt.setString(7, member.getMember_id());
         updateCount = pstmt.executeUpdate();
         
         System.out.println(member);
      } catch (SQLException e) {
         System.out.println("SQL 구문 오류! - modifiMember()");
         e.printStackTrace();
      } finally {
         // DB 자원 반환
         JdbcUtil.close(pstmt);
      }
      
      return updateCount; // MemberModifiProService
   }
   
   	// 회원 탈퇴 (회원 마이페이지)
	public int withdrawMember(MemberBean member) {
		System.out.println("MemberDAO - withdrawMember()");
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member "
					+ "		SET member_status=?"
					+ "		WHERE "
					+ "			member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_status());
			pstmt.setString(2, member.getMember_id());
			updateCount = pstmt.executeUpdate();
			
//			System.out.println(member);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - withdrawMember()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

	// 관리자 페이지 - 회원 목록 조회
	public List<MemberBean> selectMemberList() {
		System.out.println("MemberDAO - selectMemberList()");
		
		List<MemberBean> memberList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				MemberBean member = new MemberBean();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_name(rs.getString("member_name"));
				member.setMember_mobile(rs.getString("member_mobile"));
				member.setMember_addr1(rs.getString("member_addr1"));
				member.setMember_addr2(rs.getString("member_addr2"));
				member.setMember_postcode(rs.getString("member_postcode"));
				member.setMember_date(rs.getDate("member_date"));
				member.setMember_point(rs.getInt("member_point"));
				member.setMember_birth(rs.getDate("member_birth"));
				member.setMember_status(rs.getString("member_status"));
				member.setMember_authStatus(rs.getString("member_authStatus"));
				
				memberList.add(member);
			}
			
			System.out.println(memberList);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectMemberList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return memberList;
	}

	// 관리자 페이지 - 회원 삭제
	public int deleteMember(String id) {
		System.out.println("MemberDAO - deleteMember()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM member WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - deleteMember()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}

	// 아이디 찾기
	public MemberBean findMemberId(MemberBean unfindedMember) {
		System.out.println("MemberDAO - findMemberId()");
		
		MemberBean findedMember = null;

//		System.out.println("찾기 전 : " + unfindedMember);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			String sql = "SELECT * "
//					+ "         FROM member"
//					+ "         WHERE member_name=? AND member_email=?";
			
			// 아이디 마스킹 처리
			String sql = "SELECT"
						+ "    REGEXP_REPLACE(member_id, '(?<=.{3}).', '*') AS masking_member_id"
						+ "		, member_name, member_email "
						+ "FROM"
						+ "    member "
						+ "WHERE "
						+ "		member_name=? AND member_email=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, unfindedMember.getMember_name());
			pstmt.setString(2, unfindedMember.getMember_email());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				findedMember = new MemberBean();
				findedMember.setMember_id(rs.getString("masking_member_id"));
				findedMember.setMember_name(rs.getString("member_name"));
				findedMember.setMember_email(rs.getString("member_email"));
				
//				System.out.println("찾은 후 : " + findedMember);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - findMemberId()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return findedMember;
	}
	
	// 임시 비밀번호로 변경
	public int updatePass(MemberBean member) {
		System.out.println("MemberDAO - updatePass()");
	      
	      int updateCount = 0;
	      PreparedStatement pstmt = null;
	      
	      try {
	         String sql = "UPDATE member "
	               + "      SET member_pass=? "
	               + "      WHERE member_id=?";
	         
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, member.getMember_pass());
	         pstmt.setString(2, member.getMember_id());
	         updateCount = pstmt.executeUpdate();
	         
	         System.out.println(member);
	      } catch (SQLException e) {
	         System.out.println("SQL 구문 오류! - updatePass()");
	         e.printStackTrace();
	      } finally {
	         // DB 자원 반환
	         JdbcUtil.close(pstmt);
	      }
	      
	      return updateCount;
	}
   
   // 회원가입시 인증메일 값 추가 //
	public int insertAuth(AuthBean auth) {
		System.out.println("MemberDAO - insertAuth()");
		int insertCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO auth VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auth.getAuth_id());
			pstmt.setString(2, auth.getAuth_code());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertAuth()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}


		return insertCount;
	}
	// 이메일 인증시 비교 후 해당 데이터 삭제 //
	public int selectAuth(AuthBean auth) {
		System.out.println("MemberDAO - selectAuth()");
		int authCount = 0;
		PreparedStatement pstmt = null;

		try {
			// MySQL 에서는 같은 테이블에서 삭제,수정이 안되기 때문에
			// 해당 구문과 같이 FROM절에 서브쿼리를 작성하는 인라인뷰를 통한 구문 작성하였습니다
			String sql = "DELETE FROM auth"
					+ " WHERE auth_id ="
					+ " (SELECT auth_delete.id"
					+ " FROM (SELECT auth_id id"
					+ " FROM auth"
					+ " WHERE auth_id = ? AND auth_code = ?) auth_delete )";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auth.getAuth_id());
			pstmt.setString(2, auth.getAuth_code());
			authCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectAuth()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		return authCount;
	}
	// 멤버 이메일 인증 업데이트 //
	public int updateMemberAuth(MemberBean member) {
		System.out.println("MemberDAO - updateMemberAuth()");
		int updateCount = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE member SET member_authStatus = ?"
					+ " WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_authStatus());
			pstmt.setString(2, member.getMember_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateMemberAuth()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}


		return updateCount;
	}

	// 마이페이지 - 회원 이메일 정보 수정
	public int memberEmailUpdate(MemberBean member) {
		System.out.println("MemberDAO - memberEmailUpdate()");
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			// 기존 이메일과 비교
			String sql = "SELECT * FROM member WHERE member_id=? AND member_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_email());
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) { // 조회 결과가 없을 때(새로운 이메일일 때)
				sql = "UPDATE member "
						+ "		SET member_email=?"
						+ "		, member_authStatus=?"
						+ "		WHERE member_id=?";
				
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, member.getMember_email());
				pstmt2.setString(2, "N");
				pstmt2.setString(3, member.getMember_id());
				updateCount = pstmt2.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateMemberAuth()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		
		return updateCount;
	}

	public int selectMemberOrder(String id) {
		System.out.println("MemberDAO - selectMemberOrder()");
		int orderCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT count(*) FROM orders WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				orderCount = rs.getInt(1);
				System.out.println(orderCount);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectMemberOrder()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return orderCount;
	}

	public int selectMemberQna(String id) {
		System.out.println("MemberDAO - selectMemberQna()");
		
		int qnaCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT count(*) FROM qna WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaCount = rs.getInt(1);
				System.out.println(qnaCount);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectMemberQna()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return qnaCount;
	}

	public int selectMemberReview(String id) {
		System.out.println("MemberDAO - selectMemberReview()");
		
		int reviewCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT count(*) FROM review WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewCount = rs.getInt(1);
				System.out.println(reviewCount);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectMemberReview()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return reviewCount;
	}

	public int selectMemberCoupon(String id) {
		System.out.println("MemberDAO - selectMemberCoupon()");
		
		int couponCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = " SELECT count(*) "
					+ "		FROM mc_view   "
					+ "		WHERE  member_id = ?  "
					+ "			AND   cp_status = 1  "
					+ "			AND  mc_stat = 1 "
					+ "			AND mc_used = 'N' ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				couponCount = rs.getInt(1);
				System.out.println(couponCount);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectMemberCoupon()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return couponCount;
	}

	// 관리자 페이지 - 회원 정보 수정
	public int UpdateMember(MemberBean member) {
		System.out.println("MemberDAO - UpdateMember()");
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member "
						+ "		SET member_status=?"
						+ "		WHERE member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_status());
			pstmt.setString(2, member.getMember_id());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - UpdateMember()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}

   
   
}
