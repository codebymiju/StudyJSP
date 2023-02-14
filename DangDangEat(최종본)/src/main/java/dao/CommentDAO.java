package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.CommentBean;

// 실제 비즈니스 로직을 수행하는 ReviewDAO 클래스 정의
// => 각 Service 클래스 인스턴스에서 ReviewDAO 인스턴스에 접근 시 고유 데이터가 불필요하므로
//    ReviewDAO 인스턴스는 애플리케이션에서 단 하나만 생성하여 공유해도 된다!
//    따라서, 싱글톤 디자인 패턴을 적용하여 클래스를 정의하면 메모리 낭비를 막을 수 있다!
public class CommentDAO {
	// ------------ 싱글톤 디자인 패턴을 활용한 ReviewDAO 인스턴스 생성 작업 -------------
	// 1. 외부에서 인스턴스 생성이 불가능하도록 생성자를 private 접근제한자로 선언
	// 2. 자신의 클래스 내에서 직접 인스턴스를 생성하여 멤버변수에 저장
	//    => 인스턴스 생성없이 클래스가 메모리에 로딩될 때 함께 로딩되도록 static 변수로 선언
	//    => 외부에서 접근하여 함부로 값을 변경할 수 없도록 private 접근제한자로 선언
	// 3. 생성된 인스턴스를 외부로 리턴하는 Getter 메서드 정의
	//    => 인스턴스 생성없이 클래스가 메모리에 로딩될 때 함께 로딩되도록 static 메서드로 선언
	//    => 누구나 접근 가능하도록 public 접근제한자로 선언
	public CommentDAO() {}
	
	private static CommentDAO instance = new CommentDAO();

	public static CommentDAO getInstance() {
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
	
	
	// 댓글 쓰기
	public int insertCommentReview(CommentBean comment) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		
		try {
			String sql = "INSERT INTO comment VALUES (null,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getReview_code());
			pstmt.setString(2, comment.getMember_id());
			pstmt.setString(3, comment.getComment_content()); 

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertCommentReview()");
			e.printStackTrace();
		} finally {
//			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	
	// 댓글 목록 조회
	public List<CommentBean> selectCommentList(int review_code, int startRow, int listLimit) {
		List<CommentBean> commentList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
		
			String sql = "SELECT * FROM comment "
						+ "WHERE review_code=? "
						+ "ORDER BY comment_code DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			commentList = new ArrayList<CommentBean>();
			
			while(rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setComment_code(rs.getInt("comment_code"));
				comment.setReview_code(rs.getInt("review_code"));
				comment.setMember_id(rs.getString("member_id"));
				comment.setComment_content(rs.getString("comment_content"));
				comment.setComment_date(rs.getDate("comment_date"));
				System.out.println(comment);
				
				commentList.add(comment);
			}
			System.out.println(commentList);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectCommentList()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return commentList;
	}
	
	// 댓글 삭제
	public int deleteComment(int comment_code) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호에 해당하는 레코드 삭제
			String sql = "DELETE FROM comment "
								+ "WHERE comment_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - deleteNotice()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}

}