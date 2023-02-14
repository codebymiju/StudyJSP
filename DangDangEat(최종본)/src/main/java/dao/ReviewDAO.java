package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewDAO {
	
	private ReviewDAO() {}
	
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
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
	public int insertReview(ReviewBean review) {
		System.out.println("ReviewDAO - insertReview()");
		
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			// 새 글 번호 계산을 위해 기존 review 테이블의 모든 번호(review_code) 중 가장 큰 번호 조회
			// => 조회 결과 + 1 값을 새 글 번호로 지정하고, 조회 결과가 없으면 기본값 1 로 설정
			// => MySQL 구문의 MAX() 함수 사용(SELECT MAX(컬럼명) FROM 테이블명)
			int review_code = 1; // 새 글 번호
			
			String sql = "SELECT MAX(review_code) FROM review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우(= 기존 게시물이 하나라도 존재할 경우)
				// (만약, 게시물이 존재하지 않을 경우 DB 에서 NULL 로 표기, rs.next() 가 false)
				review_code = rs.getInt(1) + 1; // 기존 게시물 번호 중 가장 큰 번호(= 조회 결과) + 1
			}
			System.out.println("새 글 번호 : " + review_code);
			// --------------------------------------------------------------------------------
			// 전달받은 데이터(ReviewBean 객체)를 사용하여 INSERT 작업 수행
			// => 참조글번호(review_re_ref)는 새 글 번호와 동일한 번호로 지정
			// => 들여쓰기레벨(review_re_lev)과 순서번호(review_re_seq)는 0으로 지정
			// => INSERT 구문 실행 후 리턴값을 insertCount 변수에 저장
			sql = "INSERT INTO review VALUES (?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, review_code); // 글번호
			pstmt2.setString(2, review.getMember_id()); // 작성자
			pstmt2.setString(3, review.getReview_pass()); 
			pstmt2.setString(4, review.getReview_subject());
			pstmt2.setString(5, review.getReview_content());
			pstmt2.setString(6, review.getReview_file()); // 원본파일명
			pstmt2.setString(7, review.getReview_real_file()); // 실제파일명
			pstmt2.setInt(8, review_code); // 참조글번호(글쓰기는 글번호와 동일하게 사용)
			pstmt2.setInt(9, 0); // 들여쓰기레벨
			pstmt2.setInt(10, 0); // 순서번호
			pstmt2.setInt(11, 0); // 조회수
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertReview()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 DAO 에서 반환 금지!
		}
		
		return insertCount; // Service 로 리턴
	}
	
	// 글목록 조회
	public List<ReviewBean> selectReviewList(String keyword, int startRow, int listLimit) {
		List<ReviewBean> reviewList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// review 테이블의 모든 레코드 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
			//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			// => 정렬 : 참조글번호(review_re_ref) 기준 내림차순, 
			//           순서번호(review_re_seq) 기준 오름차순
			// => 조회 시작 레코드 행번호(startRow) 부터 listLimit 갯수(10) 만큼만 조회
			String sql = "SELECT * FROM review "
								+ "WHERE review_subject "
								+ "LIKE ? "
								+ "ORDER BY review_re_ref DESC, review_re_seq ASC "
								+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 목록 저장할 List 객체 생성
			reviewList = new ArrayList<ReviewBean>();
			
			// 조회 결과가 있을 경우
			while(rs.next()) {
				// ReviewBean 객체(review) 생성 후 조회 데이터 저장
				ReviewBean review = new ReviewBean();
				review.setReview_code(rs.getInt("review_code"));
				review.setMember_id(rs.getString("member_id"));
				review.setReview_pass(rs.getString("review_pass"));
				review.setReview_subject(rs.getString("review_subject"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_file(rs.getString("review_file"));
				review.setReview_real_file(rs.getString("review_real_file"));
				review.setReview_re_ref(rs.getInt("review_re_ref"));
				review.setReview_re_lev(rs.getInt("review_re_lev"));
				review.setReview_re_seq(rs.getInt("review_re_seq"));
				review.setReview_readcount(rs.getInt("review_readcount"));
				review.setReview_date(rs.getDate("review_date"));
//				System.out.println(review);
				
				reviewList.add(review);
			}
			
		} catch (SQLException e) {
			System.out.println("reviewDAO - selectReviewList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return reviewList;
	}
	
	// 글목록 갯수 조회
	public int selectReviewListCount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// review 테이블의 모든 레코드 갯수 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
			//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			String sql = "SELECT COUNT(*) "
								+ "FROM review "
								+ "WHERE review_subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 listCount 변수에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("ReviewDAO - selectReviewListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	
	// 글 상세정보 조회
	public ReviewBean selectReview(int review_code) {
		ReviewBean review = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블에서 글번호(board)가 일치하는 1개 레코드 조회
			String sql = "SELECT * FROM review "
								+ "WHERE review_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// ReviewBean 객체(review) 생성 후 조회 데이터 저장
				review = new ReviewBean();
				review.setReview_code(rs.getInt("review_code"));
				review.setMember_id(rs.getString("member_id"));
//				review.setReview_pass(rs.getString("review_pass"));
				review.setReview_subject(rs.getString("review_subject"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_file(rs.getString("review_file"));
				review.setReview_real_file(rs.getString("review_real_file"));
				review.setReview_re_ref(rs.getInt("review_re_ref"));
				review.setReview_re_lev(rs.getInt("review_re_lev"));
				review.setReview_re_seq(rs.getInt("review_re_seq"));
				review.setReview_readcount(rs.getInt("review_readcount"));
				review.setReview_date(rs.getDate("review_date"));
//				System.out.println(review);
			}
			
		} catch (SQLException e) {
			System.out.println("ReviewDAO - selectReview()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return review;
	}
	
	// 조회수 증가
	public int updateReadcount(int review_code) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호가 일치하는 레코드의 조회수(readcount) 1만큼 증가
			String sql = "UPDATE review "
								+ "SET review_readcount=review_readcount+1 "
								+ "WHERE review_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ReviewDAO - updateReadcount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	// 패스워드 일치 여부 확인
	public boolean isReviewWriter(int review_code) {
		boolean isReviewWriter = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// review 테이블에서 글번호(review)가 일치하는 1개 레코드 조회
			String sql = "SELECT * FROM review "
								+ "WHERE review_code=? "
								;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// isReviewWriter 값을 true 로 변경
				isReviewWriter = true;
			}
			
		} catch (SQLException e) {
			System.out.println("ReviewDAO - isReviewWriter()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isReviewWriter;
	}
	
	// 글 삭제
	public int deleteReview(int review_code) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호에 해당하는 레코드 삭제
			String sql = "DELETE FROM review "
								+ "WHERE review_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ReviewDAO - deleteReview()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}
	
	// 글 수정
	public int updateReview(ReviewBean review) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// Review 테이블의 제목, 내용, 파일명을 변경(이름은 고정)
			String sql = "UPDATE review"
								+ " SET"
								+ " 	review_subject = ?"
								+ " 	, review_content = ?";
			
							// 단, 파일명(review_file)이 null 이 아닐 경우에만 파일명도 수정
							// => 즉, 파일명을 수정하는 SET 절을 문장에 추가 결합
							if(review.getReview_file() != null) {
								sql += ", review_file = ?"
									+ " , review_real_file = ?";
							}
			
					sql			+= " WHERE"
								+ " 	review_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review.getReview_subject());
			pstmt.setString(2, review.getReview_content());
			// 단, 파일명(review_file)이 null 이 아닐 경우에만 
			// 파일명 파라미터를 교체하는 setXXX() 메서드 호출
			// => 또한, null 이 아닐 때는 글번호의 파라미터번호가 5번, 아니면 3번 
			if(review.getReview_file() != null) {
				pstmt.setString(3, review.getReview_file());
				pstmt.setString(4, review.getReview_real_file());
				pstmt.setInt(5, review.getReview_code());
			} else {
				pstmt.setInt(3, review.getReview_code());
			}
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ReviewdDAO - updateReview()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	// 답글 쓰기
	public int insertReplyReview(ReviewBean review) {
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			// 새 글 번호 계산을 위해 기존 review 테이블의 모든 번호(review_code) 중 가장 큰 번호 조회
			// => 조회 결과 + 1 값을 새 글 번호로 지정하고, 조회 결과가 없으면 기본값 1 로 설정
			// => MySQL 구문의 MAX() 함수 사용(SELECT MAX(컬럼명) FROM 테이블명)
			int review_code = 1; // 새 글 번호
			
			String sql = "SELECT MAX(review_code) FROM review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review_code = rs.getInt(1) + 1;
			}
			
//			System.out.println("새 글 번호 : " + review_code);
			// ----------------------------------------------------------------------------
			int ref = review.getReview_re_ref(); // 원본글의 참조글번호
			int lev = review.getReview_re_lev(); // 원본글의 들여쓰기레벨
			int seq = review.getReview_re_seq(); // 원본글의 순서번호
			
			// 기존 답글들에 대한 순서번호 증가 = UPDATE 구문
			// => 원본글의 참조글번호(review_re_ref)와 같고
			//    원본글의 순서번호(review_re_seq) 보다 큰 레코드들의
			//    순서번호를 + 1 씩 증가시키기
			sql = "UPDATE review"
					+ "		SET"
					+ "			review_re_seq = review_re_seq + 1"
					+ "		WHERE"
					+ "			review_re_ref = ?"
					+ "			AND review_re_seq > ?";
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
			sql = "INSERT INTO review VALUES (?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, review_code); // 글번호
			pstmt2.setString(2, review.getMember_id()); // 작성자
			pstmt2.setString(3, review.getReview_pass()); 
			pstmt2.setString(4, review.getReview_subject()); // 제목
			pstmt2.setString(5, review.getReview_content()); // 내용
			pstmt2.setString(6, review.getReview_file()); // 원본파일명
			pstmt2.setString(7, review.getReview_real_file()); // 실제파일명
			pstmt2.setInt(8, ref); // 참조글번호
			pstmt2.setInt(9, lev); // 들여쓰기레벨
			pstmt2.setInt(10, seq); // 순서번호
			pstmt2.setInt(11, 0); // 조회수
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertReplyReview()");
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