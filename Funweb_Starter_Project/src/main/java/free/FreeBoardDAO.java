package free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;

public class FreeBoardDAO {
	
	Connection con;
	PreparedStatement pstmt, pstmt2;
	ResultSet rs;
	
	// selectFreeBoardList() 메서드를 호출하여 게시물 목록 조회
	// => 파라미터 : 시작행번호, 페이지 당 게시물 목록 수
//	    (검색 기능 통합으로 인해 항상 키워드도 함께 전달)
	// => 리턴타입 : List<FreeBoardDTO>(freeBoardList) startRow, listLimit, keyword)

	public List<FreeBoardDTO> selectFreeBoardList(int startRow,int listLimit, String keyword) {
		List<FreeBoardDTO> freeBoardList = null;

		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM free_board WHERE subject LIKE ? ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			freeBoardList = new ArrayList<FreeBoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드 저장할 BoardDTO 객체 생성
				FreeBoardDTO freeBoard = new FreeBoardDTO();
				
				freeBoard.setIdx(rs.getInt("idx"));
				freeBoard.setName(rs.getString("name"));
				freeBoard.setPass(rs.getString("pass"));
				freeBoard.setSubject(rs.getString("subject"));
				freeBoard.setContent(rs.getString("content"));
				freeBoard.setDate(rs.getTimestamp("date"));
				freeBoard.setReadcount(rs.getInt("readcount"));
				freeBoardList.add(freeBoard);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectFreeBoardList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return freeBoardList;
	} // selectFreeBoardList()
	
	// selectFreeBoardListCount
	public int selectFreeBoardListCount(String keyword) {
		int freeBoardListCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT COUNT(idx) FROM free_board WHERE subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				freeBoardListCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectFreeBoardListCount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return freeBoardListCount;
	} // selectFreeBoardListCount ()
	
	public int insertFreeBoard(FreeBoardDTO freeBoard) {
		int insertCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try { // file_board 테이블의 idx 레코드(글번호) 중 가장 큰 값 조회
			int idx = 1; // 새 글 번호로 사용할 변수 선언 
			
			String sql = "SELECT MAX(idx) FROM free_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우
				// 주의! getInt("컬럼명") 사용 시 "idx"가 아닌 
				//       "MAX(idx)" 이름 지정 필요
				idx = rs.getInt(1) + 1;
			}
			// file_board에 데이터 추가(Insert)
			sql = "INSERT INTO free_board VALUES(?,?,?,?,?,?,?,now(),0)";
			pstmt2 = con.prepareStatement(sql);
			
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, freeBoard.getName());
			pstmt2.setString(3, freeBoard.getPass());
			pstmt2.setString(4, freeBoard.getSubject());
			pstmt2.setString(5, freeBoard.getContent());
			pstmt2.setString(6, freeBoard.getOriginal_file());
			pstmt2.setString(7, freeBoard.getReal_file());
			
			insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - insertFreeBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(con);
		}
		return insertCount;
	}// insertFreeBoard()
	
	public void updateReadCount(int idx) {
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "UPDATE free_board SET readcount=readcount+1 WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateReadCount()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
	} // updateReadCount()
	
	public FreeBoardDTO selectFreeBoard(int idx) {
		FreeBoardDTO fileBoard = null;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM free_board WHERE idx =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fileBoard = new FreeBoardDTO();
				fileBoard.setIdx(rs.getInt("idx"));
				fileBoard.setName(rs.getString("name"));
				fileBoard.setPass(rs.getString("pass"));
				fileBoard.setSubject(rs.getString("subject"));
				fileBoard.setContent(rs.getString("content"));
				fileBoard.setOriginal_file(rs.getString("original_file"));
				fileBoard.setReal_file(rs.getString("real_file"));
				fileBoard.setDate(rs.getTimestamp("date"));
				fileBoard.setReadcount(rs.getInt("readcount"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectFileBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return fileBoard;
	} // selectFreeBoard()
	
	public List<FreeBoardDTO> selectRecentBoardList() {
		List<FreeBoardDTO> boardList = null;
		
		con = JdbcUtil.getConnection();	
		
		try {
			String sql = "SELECT idx, name, subject, date "
					    +"FROM free_board ORDER BY idx DESC LIMIT 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			boardList = new ArrayList<FreeBoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드 저장할 BoardDTO 객체 생성
				FreeBoardDTO board = new FreeBoardDTO();
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
	
	// FileBoardDAO 객체의 updateFileBoard() 메서드를 호출하여 글수정 작업 수행
	// => 파라미터 : FileBoardDTO 객체    리턴타입 : int(updateCount)
	public int updateFreeBoard(FreeBoardDTO freeBoard) {
		int updateCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "UPDATE free_board SET subject=?, content=?, original_file=?, real_file=? "
					+ "WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, freeBoard.getSubject());
			pstmt.setString(2, freeBoard.getContent());
			pstmt.setString(3, freeBoard.getOriginal_file());
			pstmt.setString(4, freeBoard.getReal_file());
			pstmt.setInt(5, freeBoard.getIdx());
			pstmt.setString(6, freeBoard.getPass());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - updateFreeBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return updateCount;
	} // updateFreeBoard
	
	//업로드 된 파일 삭제를 위해 selectRealFile() 메서드 호출하여 실제 업로드 된 파일명 조회
	//=> 주의! 레코드 삭제 전 미리 조회 필요(파라미터 : 글번호(idx), 리턴타입 : String)
	
	public String selectRealFile(int idx) {
		String realFile = "";
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT real_file FROM free_board WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				realFile = rs.getString(1); // real_file로 변경 가능 = 조회한 것 중 첫번째 칼럼이라는 의미
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectRealFile()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return realFile;
	} // selectRealFile
	
	// FileBoardDAO 객체의 deleteFileBoard() 메서드를 호출하여 글 삭제 작업 수행
	// => 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteFileCount)
	public int deleteFreeBoard(int idx, String pass) {
		int deleteFreeCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "DELETE FROM free_board WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			
			deleteFreeCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - deleteFreeBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return deleteFreeCount;
	} // deleteFreeBoard()
	
}
