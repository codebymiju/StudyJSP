package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.BoardBean;

// 실제 비즈니스 로직을 수행하는 BoardDAO 클래스 정의 
// -> 각 Service 클래스 인스턴스에서 BoardDAO 인스턴스에 접근시 고유데이터 불필요!
//	  BoardDAO 인스턴스는 애플리케이션에서 단 하나만 생성 공유해도 된다!
//    (싱글톤 디자인 패턴을 적용하여 클래스 정의 = 메모리 절약)
public class BoardDAO {
	// ------------ 싱글톤 디자인 패턴을 활용한 BoardDAO 인스턴스 생성 작업 -------------
	// 1. 외부에서 인스턴스 생성이 불가능하도록 생성자를 private 접근제한자로 선언
	// 2. 자신의 클래스 내에서 직접 인스턴스를 생성하여 멤버변수에 저장
	// 3. 생성된 인스턴스를 외부로 리턴하는 Getter 메서드 정의

	private BoardDAO() {} // 1. 생성자 (private)
	
	private static BoardDAO instance = new BoardDAO(); // 2. 인스턴스(private+static)

	public static BoardDAO getInstance() { // 3. getter 메서드 정의 (자동)
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
	// 12/02
	// 글쓰기 작업 수행
	public int insertBoard(BoardBean board) {
		System.out.println("BoardDAO - insertBoard()");

		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			int board_num = 1;
			
			String sql = "SELECT MAX(board_num) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				board_num = rs.getInt(1) + 1;
			} // idx = 1 로 설정해둬서 else 필요 없음
			System.out.println("새 글 번호 : " + board_num);
			//=====================================================
			// 전달받은 데이터(BoardBean객체)를 사용하여 INSERT 작업 수행
			sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt2 = con.prepareStatement(sql);
			
			pstmt2.setInt(1, board_num);
			pstmt2.setString(2, board.getBoard_name());
			pstmt2.setString(3, board.getBoard_pass());
			pstmt2.setString(4, board.getBoard_subject());
			pstmt2.setString(5, board.getBoard_content());
			pstmt2.setString(6, board.getBoard_file());
			pstmt2.setString(7, board.getBoard_real_file());
			pstmt2.setInt(8, board_num); // 참조글번호(글쓰기는 글번호와 동일하게 사용)
			pstmt2.setInt(9, 0); // 들여쓰기 레벨
			pstmt2.setInt(10, 0); // 순서번호
			pstmt2.setInt(11, 0); // 조회수
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return insertCount; // service로 리턴
	} // insertBoard()

	// 글목록 조회 
	public List<BoardBean> selectBoardList(String keyword, int startRow, int listLimit) {
		
		
		List<BoardBean> boardList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블의 모든 레코드 조회 (참조글번호 내림차순, 순서번호 오름차순)
			// 조회시작 startRow부터 ListLimit 갯수만큼 조회
			String sql = "SELECT * FROM board WHERE board_subject LIKE ? "
					   + "ORDER BY board_re_ref DESC, board_re_seq ASC "
					   + "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 목록 저장할 List객체 생성
			boardList = new ArrayList<BoardBean>();
			while(rs.next()) {
				// BoardBean 객체(board) 생성 후 조회 데이터 저장
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
//				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_real_file(rs.getString("board_real_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				System.out.println(board);
				// 전체 목록 저장하는 List 객체에 1개 게시물 정보가 저장된 BoardBean 객체 추가
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return boardList;
	} // selectBoardList

	// 글목록 갯수 조회
	public int selectBoardListCount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블의 모든 레코드 갯수 조회
			String sql = "SELECT COUNT(*) FROM board WHERE board_subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 listCount 변수에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		
		
		return listCount;
	}

	// 12/05 01-(1)
	public BoardBean selectBoard(int board_num) {
		BoardBean board = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// board 테이블에서 글번호(board)가 일치하는 1개 레코드 조회
			String sql = "SELECT * FROM board WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// BoardBean 객체(board) 생성 후 조회 데이터 (1개)저장
				board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
//				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_real_file(rs.getString("board_real_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
//				System.out.println(board);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return board;
	} //selectBoard()

	// 조회수 증가 12/05 1-(1).2
	public int updateReadCount(int board_num) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE board SET board_readcount=board_readcount+1 WHERE board_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - updateReadCount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return updateCount;
	} // updateReadCount()
	
	// 12/07 - 6 패스워드 일치 여부 확인 작업 수행 (조회)
	public boolean isBoardWriter(int board_num, String board_pass) {
		boolean isBoardWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM board WHERE board_num=? AND board_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, board_pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우
				isBoardWriter = true;
			}
		} catch (SQLException e) {
			System.out.println("BoardDAO - isBoardWriter()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return isBoardWriter;
	}//isBoardWriter()

	// 글 삭제 (글번호 해당 레코드 삭제) 
	public int deleteBoard(int board_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		try { 
			String sql = "DELETE FROM board WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - deleteBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		return deleteCount;
	} //deleteBoard()

	// 12/07 1-(2).2  & 3-(1)
	// 수정된 내용 업데이트 하는 메서드
	public int updateBoard(BoardBean board) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try { // 3-(1) 제목, 내용은 고정이고 파일명은 변동이니 분리하기 
			String sql = "UPDATE board SET board_subject=?, board_content=?";
			
							// 단, 파일명(board_file)이 null이 아닐 경우에만 파일명도 수정
							// -> 즉, 파일명을 수정하는 SET절을 문장에 추가 결합
							if(board.getBoard_file() != null) {
								sql += ", board_file = ?, board_real_file = ?";
							}
					sql += " WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_subject());
			pstmt.setString(2, board.getBoard_content());
			
			// 파일명 != null 일때만 파일명 파라미터 교체하는 set()
			if(board.getBoard_file() != null) { // != null 보드넘은 5번째
				pstmt.setString(3, board.getBoard_file());
				pstmt.setString(4, board.getBoard_real_file());
				pstmt.setInt(5, board.getBoard_num());
			} else { // 파일명 = null은 마지막 보드넘이 3번째
				pstmt.setInt(3, board.getBoard_num());
			}
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - updateBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			// 주의! Connection 객체는 Service 클래스가 관리하므로 close(con)금지!!!
			// JdbcUtil.close(con) 하면 service로 넘어가서 nullPointerException 발생
		}
		
		return updateCount;
	} // updateBoard()

	// 12/09 1.답글 입력하는 메서드 
	public int insertReplyBoard(BoardBean board) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			int board_num = 1;
			
			String sql = "SELECT MAX(board_num) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				board_num = rs.getInt(1) + 1;
			} // idx = 1 로 설정해둬서 else 필요 없음
			
			//----------------------------------------------------------------
			// 기존 답글들에 대한 순서번호 증가 = UPDATE 구문
			// -> board_re_ref가 일치하고 board_re_seq보다 큰 레코드들의 순서번호 +1 시키기
			int ref = board.getBoard_re_ref(); // 원본글의 참조글번호
			int lev = board.getBoard_re_lev(); // 원본글의 들여쓰기레벨
			int seq = board.getBoard_re_seq(); // 원본글의 순서번호
			
			sql = "UPDATE board SET board_re_seq = board_re_seq + 1"
					+ " WHERE board_re_ref = ? AND board_re_seq > ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, ref);
			pstmt2.setInt(2, seq);
			pstmt2.executeUpdate();
			
			JdbcUtil.close(pstmt2);
			
			// 새 답글에 사용될 lev, seq 값 + 1
			lev++;
			seq++;
			//----------------------------------------------------------------
			// 답글에 대한 INSERT 작업 수행
			// 글쓰기와 달리 ref, lev, seq는 새로 설정된 값으로 변경
			sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt2 = con.prepareStatement(sql);
			
			pstmt2.setInt(1, board_num);
			pstmt2.setString(2, board.getBoard_name());
			pstmt2.setString(3, board.getBoard_pass());
			pstmt2.setString(4, board.getBoard_subject());
			pstmt2.setString(5, board.getBoard_content());
			pstmt2.setString(6, board.getBoard_file());
			pstmt2.setString(7, board.getBoard_real_file());
			pstmt2.setInt(8, ref); // 참조글번호(글쓰기는 글번호와 동일하게 사용) - 변경
			pstmt2.setInt(9, lev); // 들여쓰기 레벨 - 변경
			pstmt2.setInt(10, seq); // 순서번호 - 변경
			pstmt2.setInt(11, 0); // 조회수
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - insertReplyBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		return insertCount;
	} // insertReplyBoard


}
