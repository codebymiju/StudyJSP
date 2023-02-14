package board;
/*
댓글 테이블(board_reply) 정의
(단, 여러 게시판의 댓글을 하나의 테이블로 통합 관리)
-> 게시판 타입으로 분리하여 관리 
--------------------------------
번호(idx) 정수 PK AI(자동증가 = AUTO_INCREMENT)
아이디(id) 문자 16자 NN
내용(content) 문자 100자 NN
작성일(date) 날짜&시각(DATETIME) NN
원본글번호(ref_idx) 정수 NN
게시판타입(board_type) VARCHAR(30) NN  => 공지사항('notice'), 파일게시판('file')
--------------------------------
CREATE TABLE board_reply (
	idx INT PRIMARY KEY AUTO_INCREMENT,
	id VARCHAR(16) NOT NULL,
	content VARCHAR(100) NOT NULL,
	date DATETIME NOT NULL,
	ref_idx INT NOT NULL,
	board_type VARCHAR(30) NOT NULL
);
 */

import java.sql.Timestamp;

public class BoardReplyDTO {
	
	private int idx;
	private String id;
	private String content;
	private Timestamp date;
	private int ref_idx;
	private String board_type;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getRef_idx() {
		return ref_idx;
	}
	public void setRef_idx(int ref_idx) {
		this.ref_idx = ref_idx;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	
	@Override
	public String toString() {
		return "BoardReplyDTO [idx=" + idx + ", id=" + id + ", content=" + content + ", date=" + date + ", ref_idx="
				+ ref_idx + ", board_type=" + board_type + ", getIdx()=" + getIdx() + ", getId()=" + getId()
				+ ", getContent()=" + getContent() + ", getDate()=" + getDate() + ", getRef_idx()=" + getRef_idx()
				+ ", getBoard_type()=" + getBoard_type() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
