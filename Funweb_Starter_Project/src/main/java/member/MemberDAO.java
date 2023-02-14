package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;

public class MemberDAO {

	//MemberDAO 객체의 insertMember() 메서드를 호출하여 회원가입 작업 수행
	//=> 파라미터 : MemberDTO 객체(member)   리턴타입 : int(insertCount)
	public int insertMember(MemberDTO member) {
		int insertCount = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		con = JdbcUtil.getConnection();
		
			try {
				String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,member.getId());
				pstmt.setString(2,member.getPass());
				pstmt.setString(3,member.getName());
				pstmt.setString(4,member.getEmail());
				pstmt.setString(5,member.getMobile());
				pstmt.setString(6,member.getPost_code());
				pstmt.setString(7,member.getAddress1());
				pstmt.setString(8,member.getAddress2());
				pstmt.setString(9,member.getPhone());
				
				insertCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("DB연결 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		
		return insertCount;
	}// insert
	
	// 로그인 판별 작업 수행 loginMember()
	// => 파라미터 : MemberDTO 객체(member)   리턴타입 : boolean(isLoginSuccess)
	public boolean isRightUser(MemberDTO member) {
		boolean isRightUser = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = JdbcUtil.getConnection();
		// -----------------------------------여기까지 형태는 고정!
		try {
			String sql = "SELECT id FROM member WHERE id=? AND pass=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) { // true가 나오기만 하면 됨
				isRightUser = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류발생! - isRightUser()");
			e.printStackTrace();
		} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
		}
		return isRightUser;
	} // isRightUser()

	public MemberDTO selectMember(String id) {
		MemberDTO member = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = JdbcUtil.getConnection();

		try {
			String sql = "SELECT * FROM member WHERE id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery(); 
			
			// 조회 결과 레코드가 존재할 경우 MemberDTO 객체에 저장
			if(rs.next()) { 
				// MemberDTO 객체 생성 (여기서 하는이유 조회결과 없으면 그대로 
				// null값 return 하기 위하여
				member = new MemberDTO();
				
				// 조회 결과 각 컬럼 데이터에 저장
				
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPost_code(rs.getString("post_code"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setPhone(rs.getString("phone"));
				member.setDate(rs.getDate("date"));
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류발생! - loginMember()");
			e.printStackTrace();
		} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
		}
		
		return member;
	
	}// selectMember()
	
	public int updateMember(MemberDTO member, boolean isChangePass) {
		int updateCount = 0;
		

		Connection con = null;
		PreparedStatement pstmt = null; 
		con = JdbcUtil.getConnection();
		
			try {
				// 패스워드 변경 여부에 따른 각각의 SQL 구문 작성
				String sql = "";
					if(isChangePass) { // 패스워드 변경
						sql = "UPDATE member "
								+ "SET "
									+ "pass=?,"
									+ "name=?,"
									+ "email=?,"
									+ "mobile=?,"
									+ "post_code=?,"
									+ "address1=?,"
									+ "address2=?,"
									+ "phone=? "
								+ "WHERE id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, member.getPass());
						pstmt.setString(2, member.getName());
						pstmt.setString(3, member.getEmail());
						pstmt.setString(4, member.getMobile());
						pstmt.setString(5, member.getPost_code());
						pstmt.setString(6, member.getAddress1());
						pstmt.setString(7, member.getAddress2());
						pstmt.setString(8, member.getPhone());
						pstmt.setString(9, member.getId());
					} else { // 패스워드 미변경
						sql = "UPDATE member "
								+ "SET "
									+ "name=?,"
									+ "email=?,"
									+ "mobile=?,"
									+ "post_code=?,"
									+ "address1=?,"
									+ "address2=?,"
									+ "phone=? "
								+ "WHERE id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, member.getName());
						pstmt.setString(2, member.getEmail());
						pstmt.setString(3, member.getMobile());
						pstmt.setString(4, member.getPost_code());
						pstmt.setString(5, member.getAddress1());
						pstmt.setString(6, member.getAddress2());
						pstmt.setString(7, member.getPhone());
						pstmt.setString(8, member.getId());
				}
				updateCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("DB연결 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		return updateCount;
	}// updateMember()
	
	public List<MemberDTO> selectMemberList() {
		List<MemberDTO> memberList = null;
		
		MemberDTO member = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberDTO>();
			
			while(rs.next()) { 
				// 1개 레코드를 저장할 MemeberDTO 객체 생성
				member = new MemberDTO();
				
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPost_code(rs.getString("post_code"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setPhone(rs.getString("phone"));
				member.setDate(rs.getDate("date"));
				
				// => 제네릭타입에 의해 MemberDTO 타입 객체만 추가 가능함
				memberList.add(member);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return memberList;
	}// selectAll()
	
	public int deleteMember(String id) {
		int deleteCount = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = JdbcUtil.getConnection();
		
		try {
			// 아이디가 일치하는 레코드 검색
			String sql = "DELETE FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
			JdbcUtil.close(pstmt);
		}
		return deleteCount;
		
	}// deleteMember
	
	
}
