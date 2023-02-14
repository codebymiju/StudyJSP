package jsp09_jdbc_dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Jsp8_2DAO {

	public int insert(Jsp8_2DTO jsp) {
		
		int insertCount = 0;
		
		Connection con = null; //객체(=클래스)니까 null값 
		PreparedStatement pstmt = null;
		
		try {
			// 1단계 & 2단계
			// => 공통으로 처리하는 JdbcUtil 객체의 getConnection() 메서드를 호출하여
			//    DB 연결 후 리턴되는 Connection 타입 객체를 리턴받아 저장
			// => 단. getConnection() 메서드는 static 메서드이므로
			//    JdbcUtil 클래스의 인스턴스 생성이 불필요(클래스명.메서드명());
			con = JdbcUtil.getConnection(); // 한줄로 1 & 2단계 끝!
			
			// 내가 정보를 다 넣는다고 했으니 정보를 선택해서 넣지 않더라도
			// ? 만능문자 개수 = 테이블에 있는 컬럼 갯수를 맞춰서 넣어야 한다
			String sql = "INSERT INTO jsp8_2 VALUES(?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			
			// 여기서 넣는 정보는 갯수는 수정 가능
			pstmt.setString(1, jsp.getName());
			pstmt.setString(2, jsp.getId());
			pstmt.setString(3, jsp.getPasswd());
			pstmt.setString(4, jsp.getJumin());
			pstmt.setString(5, jsp.getEmail());
			pstmt.setString(6, jsp.getJob());
			pstmt.setString(7, jsp.getGender());
			pstmt.setString(8, jsp.getHobby());
			pstmt.setString(9, jsp.getContent());
			
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
	
	// 회원 목록 조회 작업을 수행하는 select() 메서드 정의
	// > 파라미터 : 없음 		리턴 타입 : java.util.ArrayList
	public ArrayList select() {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList clientList = null;
		
		try {
			// 1단계 & 2단계
			con = JdbcUtil.getConnection(); 
			
			String sql = "SELECT * FROM jsp8_2 ORDER BY hire_date DESC";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); // 조회라서 필요함
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			clientList = new ArrayList(); 
			
			while(rs.next()) {
				Jsp8_2DTO j = new Jsp8_2DTO();
				j.setName(rs.getString("name"));
				j.setId(rs.getString("id"));
				j.setPasswd(rs.getString("passwd"));
				j.setJumin(rs.getString("jumin"));
				j.setEmail(rs.getString("email"));
				j.setJob(rs.getString("job"));
				j.setGender(rs.getString("gender"));
				j.setHobby(rs.getString("hobby"));
				j.setContent(rs.getString("content"));
				j.setHire_date(rs.getDate("hire_date"));
				
				clientList.add(j);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}// finally
		
		return clientList;
		
	}// select()
	
	// 회원 상세정보 조회
	
	
	
	// 파라미터 : 아이디(id) 	리턴타입 : jsp8_2DTO(dto)
	public Jsp8_2DTO selectDetail(String id) {
		Jsp8_2DTO dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1단계 & 2단계
			con = JdbcUtil.getConnection(); 
			
			String sql = "SELECT * FROM jsp8_2 WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery(); // 조회라서 필요함
			
				if(rs.next()) {
						// 1개 레코드를 저장할 jsp8_2dto타입 객체 생성
						dto = new Jsp8_2DTO();
						
						dto.setName(rs.getString("name"));
						dto.setId(rs.getString("id"));
						dto.setJumin(rs.getString("jumin"));
						dto.setEmail(rs.getString("email"));
						dto.setJob(rs.getString("job"));
						dto.setGender(rs.getString("gender"));
						dto.setHobby(rs.getString("hobby"));
						dto.setContent(rs.getString("content"));
						dto.setHire_date(rs.getDate("hire_date"));
			
				}//if
				}//try
				catch (SQLException e) {
					System.out.println("SQL 구문 오류");
					e.printStackTrace();
				} finally {
					// select는 3개 close, 그 외는 2개 close
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					JdbcUtil.close(con);
				}// finally
		return dto;
	}// selectDetail()
	
	
	// 회원 삭제 작업 수행할 delete() 메서드
	// => 파라미터 : 아이디   리턴타입 : int(count)
	public int delete(String id) {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1단계 & 2단계
			con = JdbcUtil.getConnection(); 
			
			String sql = "DELETE FROM jsp8_2 WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}// finally
		return count;
	}// delete() 메서드
	
	
	public boolean login(String id, String passwd)	{
		boolean isLoginSuccess = false;

		Jsp8_2DTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1단계 & 2단계
			con = JdbcUtil.getConnection(); // jdbcUtil의 static() 소환
			
			String sql = "SELECT * FROM jsp8_2 WHERE id=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery(); // 조회라서 필요함
			
				if(rs.next()) { // 조회 결과가 있을 경우 = 로그인 성공
						isLoginSuccess = true;
				}//if
				}//try
				catch (SQLException e) {
					System.out.println("SQL 구문 오류");
					e.printStackTrace();
				} finally {
					// select는 3개 close, 그 외는 2개 close
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					JdbcUtil.close(con);
				}// finally
		
		return isLoginSuccess;
		
	}// isLoginSuccess
	
	
}//class
