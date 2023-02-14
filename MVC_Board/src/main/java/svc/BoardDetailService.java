package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

// 12/05 01-(1)
public class BoardDetailService {
	// 12/07 2. boolean 파라미터 추가 (게시글 수정시 조회 수 올라가지 않도록)
	// 글 상세조회 > 글 번호 + 조회수 증가 여부 파라미터로 전달
	public BoardBean getBoard(int board_num, boolean isUpdateReadcount) {
		BoardBean board = null;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// 1-(1).1
		// BoardDAO 의 selectBoard() 메서드 호출하여 게시물 상세 정보 조회 작업 수행
		// => 파라미터 : 글번호    리턴타입 : BoardBean(board)
		board = dao.selectBoard(board_num);
		
		// 1-(1).2
		// 리턴받은 BoardBean 객체가 null이 아닐 경우 + 12/07 (isUpdateReadcount가 true일 경우)
		// updateReadcount() 메서드 호출 > 조회수 증가 작업 
		// 작업이 성공했을 경우 commit 작업 수행 + BoardBean 객체의 조회수 값 1 증가 시키기
		if(board != null && isUpdateReadcount) { // boolean이니까 값 안줘도 된다
			int updateCount = dao.updateReadCount(board_num);
			if(updateCount > 0) {
				board.setBoard_readcount(board.getBoard_readcount() + 1);
				JdbcUtil.commit(con);
			}
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return board;
	}
}
