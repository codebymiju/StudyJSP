package jsp09_jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		// JDBC 0단계
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/study_jsp5";
		String user = "root";
		String password = "1234";
		
		try {
			// 1단계
			Class.forName(driver);
			
			// 2단계
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로드 실패(Class.forName(driver))");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
		// 주의! Connection 객체는 외부로 리턴해야하므로 close() 메서드 호출하지 않는다!
		
		// 데이터베이스 연결 객체가 저장된 Connection 타입 변수값 리턴
		return con;
	}//getConnection()
	
	public static void close(Connection con) {
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// connection()
	
	public static void close(PreparedStatement pstmt) {
		
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// pstmt()

	public static void close(ResultSet rs) {
	
	try {
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}// resultset()
	
	
}//jdbcUtil class
