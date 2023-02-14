package vo;

import java.sql.Date;

public class NoticeBean {	
	/*
	 * team2 데이터베이스 생성 및 notice 테이블 정의
	    CREATE DATABASE class5_220823_team2;
	    use class5_220823_team2
	    
	   CREATE TABLE notice (  
	   notice_code INT PRIMARY KEY,
	   member_id VARCHAR(20),	   
	   notice_subject VARCHAR(100),
	   notice_content VARCHAR(2000),
	   notice_readcount INT,
	   notice_date DATE,
	   FOREIGN KEY(member_id) references member(member_id)
	   );

	 * 
	 * team2 데이터베이스의 notice 테이블(게시판) 1개 레코드(= 1개 게시물) 정보를 저장하는
	 * Bean 클래스(DTO or VO) 정의
	 * 
	 */
		
	
	
	private int notice_code;
	private String member_id;
	private String notice_subject;
	private String notice_content;
	private int notice_readcount;
	private Date notice_date; 		
	
	public int getNotice_code() {
       return notice_code;
	}
	public void setNotice_code(int notice_code) {
		this.notice_code = notice_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getNotice_subject() {
		return notice_subject;
	}
	public void setNotice_subject(String notice_subject) {
		this.notice_subject = notice_subject;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	@Override
	public String toString() {
		return "NoticeBean [notice_code=" + notice_code + ", member_id=" + member_id + ", notice_subject="
				+ notice_subject + ", notice_content=" + notice_content + ", notice_readcount=" + notice_readcount
				+ ", notice_date=" + notice_date + "]";
	}
}
		