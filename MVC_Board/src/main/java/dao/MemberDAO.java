package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.MemberBean;

public class MemberDAO {
	
	private MemberDAO() {} // 1. 생성자 (private)
	
	private static MemberDAO instance = new MemberDAO(); // 2. 인스턴스(private+static)

	public static MemberDAO getInstance() { // 3. getter 메서드 정의 (자동)
		return instance;
	}
	//-----------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	//-----------------------------------------------------------------------------
	// 싱글톤 디자인 위한 기본 세팅

	// 12/09 회원가입시 DB에 정보 입력하는 메서드
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO member VALUES (?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getGender());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberDAO - insertMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	} // insertMember()

	// 12/09 로그인 메서드 : 로그인 정보와 기존 회원DB가 일치하는지 확인하는 메서드
	public int selectMember(MemberBean member) {
		int loginResult = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE id=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginResult = 1; 
			}
		} catch (SQLException e) {
			System.out.println("MemberDAO - selectMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return loginResult;
	} // selectMember()

	// 12/09 회원 목록 조회 (12/12 조건절 수정)
	public List<MemberBean> selectMemberList() {
		List<MemberBean> memberList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { // 가입일 순, 알파벳 순등 정렬조건 설정 가능
			String sql = "SELECT * FROM member ORDER BY id ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				MemberBean member = new MemberBean();
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setEmail(rs.getString("email"));
				member.setGender(rs.getString("gender"));
				member.setDate(rs.getDate("date"));
				
				// List 객체에 MemberBean 객체 추가
				memberList.add(member);
			}
		} catch (SQLException e) {
			System.out.println("MemberDAO - selectMemberList");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return memberList;
	} // selectMemberList()
	
}
