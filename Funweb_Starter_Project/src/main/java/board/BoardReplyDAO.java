package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;

public class BoardReplyDAO {

	Connection con = null;
	PreparedStatement pstmt = null, pstmt2 = null;
	ResultSet rs = null;
	
	// INSERT INTO board_reply VALUES(null, '아이디', '내용', now(), 글번호, '게시판타입');
	// INSERT INTO board_reply VALUES(null, 'hong', '댓글입니다', now(), 36, 'driver');
	// => BoardReplyDAO - insertReplyBoard() 메서드로 INSERT 작업 수행
	
	public int insertReplyBoard(BoardReplyDTO board) {
		int insertCount = 0;
		
		con = JdbcUtil.getConnection();
		
		try {
			String sql = "INSERT INTO board_reply VALUES(null,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getRef_idx());
			pstmt.setString(4, board.getBoard_type());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - insertReplyBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return insertCount;
	} // insertReplyBoard
	
	// BoardReplyDAO - getReplyList() 메서드 호출하여 댓글 목록 가져오기
	// -> 파라미터 : 게시물글번호, 게시판타입   
	//    리턴타입 : List<BoardReplyDTO> (replyList)
	
	// 댓글 목록 조회 - selectReplyList()
		// => 파라미터 : 게시물글번호, 게시판타입, startRow, listLimit 
		//  리턴타입 : List<BoardReplyDTO>(replyList)
		public List<BoardReplyDTO> selectReplyList(
				int ref_idx, String board_type, int startRow, int listLimit) {
			List<BoardReplyDTO> replyList = null;
			
			con = JdbcUtil.getConnection();
			
			try {
				// board_reply 테이블의 모든 레코드 조회
				// => 조건 : 게시판 타입(board_type 컬럼)이 일치하고, 원본글번호가 일치하는 목록 조회
				// => idx 컬럼 기준 내림차순 정렬(ORDER BY 컬럼명 정렬방식)
				// => 시작행번호부터 게시물 목록 수 만큼으로 갯수 제한(LIMIT 시작행번호,목록수)
				//    (단, 시작행번호 첫번째는 0부터 시작)
				//    (또한, LIMIT 에 파라미터 하나만 사용 시 목록 갯수로 사용됨)
				String sql = "SELECT * FROM board_reply "
							+ "WHERE board_type=? AND ref_idx=? "
							+ "ORDER BY idx DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, board_type);
				pstmt.setInt(2, ref_idx);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, listLimit);
				rs = pstmt.executeQuery();
				
				// 전체 레코드를 저장할 ArrayList 객체 생성
				replyList = new ArrayList<BoardReplyDTO>();
				
				while(rs.next()) {
					// 1개 레코드를 저장할 BoardReplyDTO 객체 생성
					BoardReplyDTO board = new BoardReplyDTO();
					board.setIdx(rs.getInt("idx"));
					board.setId(rs.getString("id"));
					board.setContent(rs.getString("content"));
					board.setDate(rs.getTimestamp("date"));
					board.setRef_idx(rs.getInt("ref_idx"));
					board.setBoard_type(rs.getString("board_type"));
					System.out.println(board);
					
					// 전체 레코드 저장하는 List 객체에 1개 레코드 저장된 BoardReplyDTO 객체 추가
					replyList.add(board);
				}
//				System.out.println(replyList);
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! - selectReplyList()");
				e.printStackTrace();
			} finally {
				// 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
			return replyList;
			
		}// selectReplyList()
	
		// 댓글의 삭제버튼 작동시키는 메서드
				public int deleteReply(int idx) {
					int deleteCount = 0;
					
					con = JdbcUtil.getConnection();
					
					try {
						String sql = "DELETE FROM board_reply WHERE idx=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, idx);
						
						deleteCount = pstmt.executeUpdate();
					} catch (SQLException e) {
						System.out.println("SQL구문 오류 - deleteReply()");
						e.printStackTrace();
					} finally {
						JdbcUtil.close(pstmt);
						JdbcUtil.close(con);
					}
					
					return deleteCount;
				} // deleteReply()
				
				

	// 전체 게시물 수 조회 selectReplyBoardListCount() 
			public int selectReplyBoardListCount(String board_type, int idx) {
				int listCount = 0;
				
				con = JdbcUtil.getConnection();
				
				try {
					// 특정 컬럼 또는 전체 컬럼(*)에 해당하는 레코드 수 조회하기 위해
					// MySQL 의 COUNT() 함수 활용(SELECT COUNT(컬럼명 또는 *) FROM 테이블명)
					// => 제목 검색 기능 추가와 일반 목록 기능을 결합하여
					//    제목이 널스트링("")일 경우 전체 목록 조회 가능
					String sql = "SELECT COUNT(idx) FROM board_reply WHERE board_type=? AND ref_idx=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, board_type);
					pstmt.setInt(2, idx);
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
			} // selectReplyBoardListCount()
				
				
}// BoardReplyDAO 
