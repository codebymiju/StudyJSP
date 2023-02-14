package jsp09_jdbc_dao;

import java.sql.Connection; // ctrl_shift_o > 자동 import키
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
	
	public int insert(StudentDTO student){ // 밖에서 만들어둔 studentDTO를 가져다 쓴다
		System.out.println("StudentDAO - insert()");
		
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 클래스 타입 변수 선언
		// => try, catch, finally 블록 등 여러 블록에 걸쳐 변수를 사용하기 위함
		Connection con = null; // 메서드 안에 만드는 변수는 초기화 해야함
		PreparedStatement pstmt = null;
		
		try { // 블럭 지정 후 alt_shift_z - try/catch
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/study_jsp5";
			String user = "root";
			String password = "1234";
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, user, password);
			
			// study_jsp5 데이터베이스 student 테이블에 데이터 추가(insert)
			// -> 리턴되는 추가 작업 결과 변수(count)에 저장
			String sql = "INSERT INTO student VALUES(?, ?)";
			pstmt = con.prepareStatement(sql);
			
			// 만능문자를 치환할 데이터는 StudentDTO 객체에 저장되어 있음 > Getter이용
			pstmt.setInt(1, student.getIdx());
			pstmt.setNString(2, student.getName());
			
			// SQL 구문 실행 후 리턴되는 결과값(int 타입)을 변수에 저장 후 리턴
			insertCount = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 또는 SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// 데이터베이스 자원을 반환하기 위한 코드 작성하는 블럭
			// => Connection, PreparedStatement, ResultSet 등의 객체의 close() 메서드 호출
			//    단, 객체를 생성한 순서의 역순으로 반환해야함
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertCount;
	}
	
	// 회원 목록 조회 작업을 수행하는 select() 메서드 정의
	// > 파라미터 : 없음 		리턴 타입 : java.util.ArrayList
	public ArrayList select() {
		
		// 0단계. 문자열 지정
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/study_jsp5";
		String user = "root";
		String password ="1234";
		
		// 데이터베이스 작업에 사용되는 객체 타입 변수 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList studentList = null;
		
		try { // alt + shift + z 로 try & catch 블록지정
			// 1단계. 드라이버 연결
			Class.forName(driver);
			
			// 2단계. DB 연결
			con = DriverManager.getConnection(url, user, password);
			
			// 3단계. SQL 구문 작성 및 연결
			String sql = "SELECT * FROM student";
			pstmt = con.prepareStatement(sql);
			
			// 4단계. SQL 구문 실행 및 결과 처리
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			studentList = new ArrayList();
			
			// while문을 사용하여 다음 레코드가 존재할 동안 반복
			while(rs.next()) {
				// 각 레코드의 컬럼 데이터를 가져와서 변수에 저장 후 출력
				int idx  = rs.getInt("idx");
				String name = rs.getString("name");
//				System.out.println(idx + ". " + name);
				
				StudentDTO student = new StudentDTO();
				student.setIdx(idx);
				student.setName(name);
				
				// 전체 레코드를 저장 가능한 ArrayList 객체에
				// 1개 레코드가 저장된 StudentTDTO 객체 추가(저장)
				studentList.add(student); // StudentDTO > Object로 업캐스팅
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 또는 SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 관계없이 무조건 마지막에 실행되는 블록
			// -> 데이터베이스 작업에 사용된 객체(자원)을 반환하는 작업은 항상 수행되어야 함
			
			try { // 닫을게 없으면 문제 생길 수 도 있으니 try & catch로 다시 묶어줌
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// finally
		
		// 전체 레코드가 저장된 ArrayList 객체 리턴 > select.jsp
		return studentList;
	}//select()
	
}// class



