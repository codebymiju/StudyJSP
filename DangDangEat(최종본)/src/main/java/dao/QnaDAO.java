package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.QnaBean;

public class QnaDAO {

	private QnaDAO() {}
	
	private static QnaDAO instance = new QnaDAO();

	public static QnaDAO getInstance() {
		return instance;
	}
	// ----------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// ----------------------------------------------------------------------------------
	// 글쓰기 작업 수행
	public int insertQna(QnaBean qna) {
		System.out.println("QnaDAO - insertQna()");
		
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		int qna_code = 1; // 새 글 번호
		
		try {			
			
			String sql = "SELECT COUNT(qna_code) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우(= 기존 게시물이 하나라도 존재할 경우)
				// (만약, 게시물이 존재하지 않을 경우 DB 에서 NULL 로 표기, rs.next() 가 false)
				qna_code = rs.getInt(1) + 1; // 기존 게시물 번호 중 가장 큰 번호(= 조회 결과) + 1
			}
			System.out.println("새 글 번호 : " + qna_code);
			// --------------------------------------------------------------------------------
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?,?,?,now(),?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, qna_code); // 글번호
			pstmt2.setString(2, qna.getMember_id()); // 작성자
			pstmt2.setString(3, qna.getQna_pass()); 
			pstmt2.setString(4, qna.getQna_subject());
			pstmt2.setString(5, qna.getQna_content());
			pstmt2.setString(6, qna.getQna_file());
			pstmt2.setString(7, qna.getQna_real_file()); // 실제파일명
			pstmt2.setInt(8, qna_code); // 참조글번호(글쓰기는 글번호와 동일하게 사용)
			pstmt2.setInt(9, 0); // 들여쓰기레벨
			pstmt2.setInt(10, 0); // 순서번호		
			pstmt2.setString(11, qna.getQna_secret());
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insert()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(rs);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 DAO 에서 반환 금지!
		}
		
		return insertCount; // Service 로 리턴
	}
	
	// 글목록 조회
	public List<QnaBean> selectQnaList(String keyword, int startRow, int listLimit) {
		List<QnaBean> qnaList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM qna "
					+ "WHERE qna_subject "
					+ "LIKE ? "
					+ "ORDER BY qna_re_ref DESC, qna_re_seq ASC "
					+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 목록 저장할 List 객체 생성
			qnaList = new ArrayList<QnaBean>();
			
			// 조회 결과가 있을 경우
			while(rs.next()) {
				// QnaBean 객체(qna) 생성 후 조회 데이터 저장
				QnaBean qna = new QnaBean();
				qna.setQna_code(rs.getInt("qna_code"));
				qna.setMember_id(rs.getString("member_id"));
//				qna.setQna_pass(rs.getString("qna_pass"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_file(rs.getString("qna_file"));
				qna.setQna_real_file(rs.getString("qna_real_file"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_secret(rs.getString("qna_secret"));
				System.out.println(qna);
				
				qnaList.add(qna);
			}
			
		} catch (SQLException e) {
			System.out.println("qnaDAO - selectQnaList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return qnaList;
	}
	
	// 글목록 갯수 조회
	public int selectQnaListCount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) "
								+ "FROM qna "
								+ "WHERE qna_subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 listCount 변수에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("QnaDAO - selectQnaListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	  }

	// 글 상세정보 조회
	public QnaBean selectQna(int qna_code) {
		QnaBean qna = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// qna 테이블에서 글번호(qna)가 일치하는 1개 레코드 조회
			String sql = "SELECT * FROM qna "
								+ "WHERE qna_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_code);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// QnaBean 객체(qna) 생성 후 조회 데이터 저장
				qna = new QnaBean();
				qna.setQna_code(rs.getInt("qna_code"));
				qna.setMember_id(rs.getString("member_id"));
//				qna.setQna_pass(rs.getString("qna_pass"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_file(rs.getString("qna_file"));
				qna.setQna_real_file(rs.getString("qna_real_file"));
				qna.setQna_re_ref(rs.getInt("qna_re_ref"));
				qna.setQna_re_lev(rs.getInt("qna_re_lev"));
				qna.setQna_re_seq(rs.getInt("qna_re_seq"));
				qna.setQna_date(rs.getDate("qna_date"));
				qna.setQna_secret(rs.getString("qna_secret"));
				System.out.println(qna);
			}			
		} catch (SQLException e) {
			System.out.println("QnaDAO - selectQna()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}		
		return qna;
	}
	
	
	
	// 패스워드 일치 여부 확인
	public boolean isQnaWriter(int qna_code) {
		boolean isQnaWriter = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// qna 테이블에서 글번호(qna)가 일치하는 1개 레코드 조회
			String sql = "SELECT * FROM qna "
								+ "WHERE qna_code=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_code);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// isQnaWriter 값을 true 로 변경
				isQnaWriter = true;
			}
			
		} catch (SQLException e) {
			System.out.println("QnaDAO - isQnaWriter()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isQnaWriter;
	}
	
	// 글 삭제
	public int deleteQna(int qna_code) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호에 해당하는 레코드 삭제
			String sql = "DELETE FROM qna "
								+ "WHERE qna_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("QnaDAO - deleteQna()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}
	
	// 글 수정
	public int updateQna(QnaBean qna) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// qna 테이블의 제목, 내용, 파일명을 변경(이름은 고정)
			String sql = "UPDATE qna"
								+ " SET"
								+ " 	qna_subject = ?"
								+ " 	, qna_content = ?";			
							// 단, 파일명(qna_file)이 null 이 아닐 경우에만 파일명도 수정
							// => 즉, 파일명을 수정하는 SET 절을 문장에 추가 결합
							if(qna.getQna_file() != null) {
								sql += ", qna_file = ?"
									+ " , qna_real_file = ?";
							}
			
					sql			+= " WHERE"
								+ " 	qna_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_subject());
			pstmt.setString(2, qna.getQna_content());
			// 단, 파일명(qna_file)이 null 이 아닐 경우에만 
			// 파일명 파라미터를 교체하는 setXXX() 메서드 호출
			// => 또한, null 이 아닐 때는 글번호의 파라미터번호가 5번, 아니면 3번 
			if(qna.getQna_file() != null) {
				pstmt.setString(3, qna.getQna_file());
				pstmt.setString(4, qna.getQna_real_file());
				pstmt.setInt(5, qna.getQna_code());
			} else {
				pstmt.setInt(3, qna.getQna_code());
			}
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("QnaDAO - updateQna()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	// 답글 쓰기
	public int insertReplyQna(QnaBean qna) {
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		int qna_code = 1; // 새 글 번호
		
		try {
			// 새 글 번호 계산을 위해 기존 qna 테이블의 모든 번호(qna_code) 중 가장 큰 번호 조회
			// => 조회 결과 + 1 값을 새 글 번호로 지정하고, 조회 결과가 없으면 기본값 1 로 설정
			// => MySQL 구문의 MAX() 함수 사용(SELECT MAX(컬럼명) FROM 테이블명)
						
			String sql = "SELECT MAX(qna_code) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qna_code = rs.getInt(1) + 1;
			}
			
//			System.out.println("새 글 번호 : " + qna_code);
			// ----------------------------------------------------------------------------
			int ref = qna.getQna_re_ref(); // 원본글의 참조글번호
			int lev = qna.getQna_re_lev(); // 원본글의 들여쓰기레벨
			int seq = qna.getQna_re_seq(); // 원본글의 순서번호
			
			// 기존 답글들에 대한 순서번호 증가 = UPDATE 구문
			// => 원본글의 참조글번호(qna_re_ref)와 같고
			//    원본글의 순서번호(qna_re_seq) 보다 큰 레코드들의
			//    순서번호를 + 1 씩 증가시키기
			sql = "UPDATE qna"
					+ "		SET"
					+ "			qna_re_seq = qna_re_seq + 1"
					+ "		WHERE"
					+ "			qna_re_ref = ?"
					+ "			AND qna_re_seq > ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, ref);
			pstmt2.setInt(2, seq);
			pstmt2.executeUpdate();
			
			JdbcUtil.close(pstmt2);
			
			// 새 답글에 사용될 원본글의 lev, seq 값 + 1 처리
			lev++;
			seq++;
			
			// ------------------------------------------------------------
			// 답글 INSERT
			// => 글쓰기와 달리 ref, lev, seq 값은 설정된 값으로 변경
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?,?,?,now(),?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, qna_code); // 글번호
			pstmt2.setString(2, qna.getMember_id()); // 작성자
			pstmt2.setString(3, qna.getQna_pass()); 
			pstmt2.setString(4, qna.getQna_subject()); // 제목
			pstmt2.setString(5, qna.getQna_content()); // 내용
			pstmt2.setString(6, qna.getQna_file()); // 원본파일명
			pstmt2.setString(7, qna.getQna_real_file()); // 실제파일명
			pstmt2.setInt(8, ref); // 참조글번호
			pstmt2.setInt(9, lev); // 들여쓰기레벨
			pstmt2.setInt(10, seq); // 순서번호
			pstmt2.setString(11, qna.getQna_secret());
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertReplyQna()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		
		return insertCount;
	}
	
}
