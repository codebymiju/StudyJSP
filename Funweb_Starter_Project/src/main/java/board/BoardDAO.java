package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;

public class BoardDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null, pstmt2 = null;
	ResultSet rs = null;

	public int insertBoard(BoardDTO board) {
		int insertCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			int idx = 1;
			
			String sql = "SELECT MAX(idx) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				idx = rs.getInt(1) + 1;
			} // idx = 1 로 설정해둬서 else 필요 없음
			System.out.println("새 글 번호 : " + idx);
			//---------------------------------------------------
			// 게시물 등록(INSERT) 작업 수행
			sql = "INSERT INTO board VALUES (?,?,?,?,?,now(),0)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx); // 위에서 생성한 새 글 번호 활용
			pstmt2.setString(2, board.getName());
			pstmt2.setString(3, board.getPass());
			pstmt2.setString(4, board.getSubject());
			pstmt2.setString(5, board.getContent());
			
			insertCount = pstmt2.executeUpdate(); // 글등록
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(con);
		}
		
		return insertCount;
	}// insertBoard()

	public int selectListCount() {
		int listCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT COUNT(idx) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectListCount");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return listCount;
	}// selectListCount()
	
	// 검색어 기능을 추가한 selectListCount()
	public int selectListCount(String keyword) {
		int listCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT COUNT(idx) FROM board WHERE subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectListCount");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return listCount;
	}// selectListCount()
	
//	public List<BoardDTO> selectBoardList() {
//		List<BoardDTO> boardList = null;
//		con = JdbcUtil.getConnection();
//		
//		try {
//			String sql = "SELECT * FROM board ORDER BY idx DESC";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			// 전체 레코드를 저장할 ArrayList 객체 생성
//			boardList = new ArrayList<BoardDTO>();
//			
//			while(rs.next()) {
//				// 1개 레코드 저장할 BoardDTO 객체 생성
//				BoardDTO board = new BoardDTO();
//				
//				board.setIdx(rs.getInt("idx"));
//				board.setName(rs.getString("name"));
//				board.setPass(rs.getString("pass"));
//				board.setSubject(rs.getString("subject"));
//				board.setContent(rs.getString("content"));
//				board.setDate(rs.getTimestamp("date"));
//				board.setReadcount(rs.getInt("readcount"));
//				boardList.add(board);
//			}
////			System.out.println(boardList);
//		} catch (SQLException e) {
//			System.out.println("SQL 구문 오류 - selectBoardList()");
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//			JdbcUtil.close(con);
//		}
//		return boardList;
//	} // selectBoardList()
	
	public List<BoardDTO> selectBoardList(int startRow, int listLimit) {
		List<BoardDTO> boardList = null;
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM board ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			boardList = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드 저장할 BoardDTO 객체 생성
				BoardDTO board = new BoardDTO();
				
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setDate(rs.getTimestamp("date"));
				board.setReadcount(rs.getInt("readcount"));
				boardList.add(board);
			}
//			System.out.println(boardList);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return boardList;
	} // selectBoardList()
	
	// 검색 기능 추가
	public List<BoardDTO> selectBoardList(int startRow, int listLimit, String keyword) {
		List<BoardDTO> boardList = null;
		con = JdbcUtil.getConnection();
		
		try {
			// + 제목에 검색어를 포함하는 레코드 조회
			String sql = "SELECT * FROM board WHERE subject LIKE ? ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			boardList = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드 저장할 BoardDTO 객체 생성
				BoardDTO board = new BoardDTO();
				
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setDate(rs.getTimestamp("date"));
				board.setReadcount(rs.getInt("readcount"));
				boardList.add(board);
			}
//			System.out.println(boardList);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return boardList;
	} // selectBoardList()
	
	
	public BoardDTO selectBoard(int idx) {
		BoardDTO board = null;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM board WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery(); // sql 구문 실행
			
			if(rs.next()) {
				board = new BoardDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
				board.setSubject(rs.getString("subject"));
				// textarea 에 표시할 데이터는 문자열 치환(replace) 기능을 통해
				// 줄바꿈 기호를 "<br>" 태그로 치환해야한다!
//				board.setContent(rs.getString("content").replaceAll(System.getProperty("line.separator"), "<br>"));
				// => DAO 에서 수행하는 대신 뷰페이지(JSP)에서 수행해도 동일함 
				board.setContent(rs.getString("content"));
				board.setDate(rs.getTimestamp("date"));
				board.setReadcount(rs.getInt("readcount"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return board;
	} // selectBoard
	
	public void updateReadcount(int idx) {
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "UPDATE board SET readcount=readcount+1 WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateReadcount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
	}// updateReadcount()
	
	public int updateBoard(BoardDTO board) {
		int updateCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "UPDATE board SET "
					   +"name=?, subject=?, content=? WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getIdx());
			pstmt.setString(5, board.getPass());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return updateCount;
	}// updateBoard()
	
	//BoardDAO 객체의 deleteBoard() 메서드를 호출하여 글 삭제 작업 수행
	//=> 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteCount)
	public int deleteBoard(int idx, String pass) {
		int deleteCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "DELETE FROM board WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - deleteBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return deleteCount;
	}// deleteBoard
	
	// BoardDAO 객체의 selectRecentBoardList() 메서드를 호출하여
		// 최근 게시물 5개 목록 조회 후 표시
		// => 파라미터 : 없음  리턴타입 : List<BoardDTO>(boardList)
	
	public List<BoardDTO> selectRecentBoardList() {
		List<BoardDTO> boardList = null;
		
		con = JdbcUtil.getConnection();	
		
		try {
			String sql = "SELECT idx, name, subject, date "
					    +"FROM board ORDER BY idx DESC LIMIT 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			boardList = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드 저장할 BoardDTO 객체 생성
				BoardDTO board = new BoardDTO();
				// 데이터 저장
				board.setIdx(rs.getInt("idx"));
				board.setSubject(rs.getString("subject"));
				board.setName(rs.getString("name"));
				board.setDate(rs.getTimestamp("date"));
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - selectRecentBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return boardList;
	} // selectRecentBoardList
	
}
