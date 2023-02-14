package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;

public class FileBoardDAO {
	private Connection con;
	private PreparedStatement pstmt, pstmt2;
	private ResultSet rs;
	
	// 글쓰기 작업 수행 - insertFileBoard()
	// => 파라미터 : FileBoardDTO 객체    리턴타입 : int(insertCount)
	public int insertFileBoard(FileBoardDTO fileBoard) {
		int insertCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			// file_board 테이블의 idx 레코드(글번호) 중 가장 큰 값 조회
			int idx = 1; // 새 글 번호로 사용할 변수 선언(초기값 1)
			
			String sql = "SELECT MAX(idx) FROM file_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우
				// 조회된 글번호 + 1 값을 새 글 번호로 저장
				// => 주의! getInt("컬럼명") 사용 시 "idx" 가 아닌 "MAX(idx)" 이름 지정 필요
				idx = rs.getInt(1) + 1; // 컬럼인덱스 활용
			}
			
			// file_board 테이블에 데이터 추가(INSERT)
			sql = "INSERT INTO file_board VALUES (?,?,?,?,?,?,?,now(),0)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx);
			pstmt2.setString(2, fileBoard.getName());
			pstmt2.setString(3, fileBoard.getPass());
			pstmt2.setString(4, fileBoard.getSubject());
			pstmt2.setString(5, fileBoard.getContent());
			pstmt2.setString(6, fileBoard.getOriginal_file());
			pstmt2.setString(7, fileBoard.getReal_file());
			
			insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertFileBoard()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(con);
		}
		
		return insertCount;
	}

	// 전체 게시물 수 조회 selectFileBoardListCount() => 검색어 기능 추가
	// => 파라미터 : 검색어(keyword), 리턴타입 : int(listCount)
	public int selectFileBoardListCount(String keyword) {
		int listCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			// 특정 컬럼 또는 전체 컬럼(*)에 해당하는 레코드 수 조회하기 위해
			// MySQL 의 COUNT() 함수 활용(SELECT COUNT(컬럼명 또는 *) FROM 테이블명)
			// => 제목 검색 기능 추가와 일반 목록 기능을 결합하여
			//    제목이 널스트링("")일 경우 전체 목록 조회 가능
			String sql = "SELECT COUNT(idx) FROM file_board "
							+ "WHERE subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectFileBoardListCount()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return listCount;
	}
	

	// 게시물 목록 조회 selectFileBoardList() - 검색 기능 추가
	// => 파라미터 : 시작행번호, 페이지 당 게시물 목록 수, 검색어(keyword)
	//    리턴타입 : List<FileBoardDTO>(boardList)
	public List<FileBoardDTO> selectFileBoardList(int startRow, int listLimit, String keyword) {
		List<FileBoardDTO> fileBoardList = null;
		
		con = JdbcUtil.getConnection();
		
		try {
			// board 테이블의 모든 레코드 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
			//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			String sql = "SELECT * FROM file_board "
								+ "WHERE subject "
								+ "LIKE ? "
								+ "ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			fileBoardList = new ArrayList<FileBoardDTO>();
			
			while(rs.next()) {
				// 1개 레코드를 저장할 BoardDTO 객체 생성
				FileBoardDTO fileBoard = new FileBoardDTO();
				fileBoard.setIdx(rs.getInt("idx"));
				fileBoard.setName(rs.getString("name"));
				fileBoard.setPass(rs.getString("pass"));
				fileBoard.setSubject(rs.getString("subject"));
				fileBoard.setContent(rs.getString("content"));
				fileBoard.setOriginal_file(rs.getString("original_file"));
				fileBoard.setReal_file(rs.getString("real_file"));
				fileBoard.setDate(rs.getTimestamp("date"));
				fileBoard.setReadcount(rs.getInt("readcount"));
//				System.out.println(board);
				
				// 전체 레코드 저장하는 List 객체에 1개 레코드 저장된 FileBoardDTO 객체 추가
				fileBoardList.add(fileBoard);
			}
			System.out.println(fileBoardList);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectFileBoardList()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return fileBoardList;
	}
	
	
	// 조회수 증가 작업 수행 - updateReadcount()
	// => 파라미터 : 글번호(idx)   리턴타입 : void
	public void updateReadcount(int idx) {
		con = JdbcUtil.getConnection(); // Connection 객체 가져오기
		
		try {
			// file_board 테이블에서 글번호(idx)가 일치하는 게시물(레코드)의
			// 조회수(readcount) 값을 1만큼 증가 - UPDATE
			String sql = "UPDATE file_board "
							+ "SET readcount=readcount+1 "
							+ "WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateReadcount()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		// 리턴 데이터 없음 - driver_content.jsp
	}
	
	//  게시물 1개 조회 작업 수행 - selectFileBoard()
	// => 파라미터 : 글번호(idx)   리턴타입 : FileBoardDTO(fileBoard)
	public FileBoardDTO selectFileBoard(int idx) {
		FileBoardDTO fileBoard = null;
		
		con = JdbcUtil.getConnection(); // Connection 객체 가져오기
		
		try {
			// file_board 테이블에서 글번호(idx) 가 일치(조건) 하는 레코드(전체 컬럼 데이터) 조회
			// => 조회 결과가 존재할 경우 FileBoardDTO 객체 생성 후 데이터 저장
			String sql = "SELECT * FROM file_board WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery(); // SQL 구문 실행
			
			// 조회결과 존재 여부 판별
			if(rs.next()) { // 조회 결과 존재할 경우
				fileBoard = new FileBoardDTO(); // FileBoardDTO 객체 생성
				// 데이터 저장
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
			System.out.println("SQL 구문 오류! - selectFileBoard()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return fileBoard; // driver_content.jsp 로 리턴
	}
	
	// 실제 업로드 된 파일명 조회 - selectRealFile()
	// => 파라미터 : 글번호(idx), 리턴타입 : String
	public String selectRealFile(int idx) {
		String realFile = "";
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "SELECT real_file FROM file_board WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				realFile = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectRealFile()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return realFile;
	}
	
	// 글 삭제 작업 수행 - deleteFileBoard()
	// => 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteCount)
	public int deleteFileBoard(int idx, String pass) {
		int deleteCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			// file_board 테이블에서 글번호와 패스워드가 일치하는 레코드 삭제(DELETE)
			String sql = "DELETE FROM file_board "
						+ "WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - deleteFileBoard()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return deleteCount;
	}
	
	// 글수정 작업 수행 - updateFileBoard()
	// => 파라미터 : FileBoardDTO 객체    리턴타입 : int(updateCount)
	public int updateFileBoard(FileBoardDTO fileBoard) {
		int updateCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			// file_board 테이블에서 글번호와 패스워드가 일치하는 레코드 수정(UPDATE)
			String sql = "UPDATE file_board "
						+ "SET subject=?, content=?, original_file=?, real_file=? "
						+ "WHERE idx=? AND pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileBoard.getSubject());
			pstmt.setString(2, fileBoard.getContent());
			pstmt.setString(3, fileBoard.getOriginal_file());
			pstmt.setString(4, fileBoard.getReal_file());
			pstmt.setInt(5, fileBoard.getIdx());
			pstmt.setString(6, fileBoard.getPass());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateFileBoard()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return updateCount;
	} // updateFileBoard
	
}












