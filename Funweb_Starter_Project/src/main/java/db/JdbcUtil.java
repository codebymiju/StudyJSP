package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


// 데이터베이스 작업 준비 및 해제(자원반환) 작업을 공통으로 수행할 JdbcUtil 클래스 정의
public class JdbcUtil {
	
	// 1. 데이터베이스 접근을 통해 Connection 객체를 생성하여 외부로 리턴하는
		//    getConnection() 메서드 정의
		// => 파라미터 : 없음      리턴타입 : java.sql.Connection(con)
		// => 단, JdbcUtil 클래스의 인스턴스 생성 없이도 메서드 호출이 가능하도록
		//    static 메서드로 정의
	public static Connection getConnection() {
		// 데이터베이스 연결 객체를 저장할 Connection 타입 변수 선언
		Connection con = null;
		
		try {
			// 1. 톰캣 객체에서 관리하는 Context 객체 가져오기
			Context initCtx = new InitialContext();
			
			// 2. 생성된 Context 객체(initCtx)로부터 context.xml 에 정의된 Resource 태그 부분 가져오기
			// 3. Resource 태그 내의 name 속성(= 리소스 이름 jdbc/MySQL) 가져오기 위해
			//    생성된 Context 객체(envCtx)의 lookup() 메서드를 호출하여 리소스 이름 전달
			//------------------ 2 & 3 결합 문장--------------------
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/MySQL");
			
			// 4. DataSource 객체(= 커넥션풀)로부터 미리 생성된 Connection 객체 가져오기
			con = ds.getConnection();
			
			// 5. (옵션) 자동 커밋(=Auto Commit) 기능 해제
//			con.setAutoCommit(false); // 자동 커밋 기능 해제
			
			// 6. (옵션) 현재 커넥션 정보 확인
//			BasicDataSource bds = (BasicDataSource)ds;
//			System.out.println("MaxTotal : " + bds.getMaxTotal()); // 최대 커넥션 수
//			System.out.println("Active : " + bds.getNumActive()); // 현재 사용 중인 커넥션 수
//			System.out.println("Idle : " + bds.getNumIdle()); // 유휴 상태 커넥션 수
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}//getConnection()
	
	public static void close(Connection con) {
		
		try {
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// connection()
	
	public static void close(PreparedStatement pstmt) {
		
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// pstmt()

	public static void close(ResultSet rs) {
	
	try {
		if(rs != null) {
			rs.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}// resultset()
	
	
}//jdbcUtil class
