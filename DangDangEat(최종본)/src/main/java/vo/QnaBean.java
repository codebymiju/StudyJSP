package vo;

import java.sql.Date;

public class QnaBean {
	/*
	 * team2 데이터베이스 생성 및 notice 테이블 정의
	    CREATE DATABASE class5_220823_team2;
	    use class5_220823_team2
	    
	   
      CREATE TABLE qna (
         qna_code INT PRIMARY KEY,
         member_id VARCHAR(20),
         qna_pass VARCHAR(16),
	     qna_subject VARCHAR(100),
	     qna_content VARCHAR(2000),
	     qna_file VARCHAR(200), 
	     qna_real_file VARCHAR(200), 
	     qna_re_ref INT,
	     qna_re_lev INT,
	     qna_re_seq INT,
         qna_date DATE, 
         qna_secret VARCHAR(1),
         FOREIGN KEY(member_id) references member(member_id)
        );
       
	 * 
	 * team2 데이터베이스의 qna 테이블(게시판) 1개 레코드(= 1개 게시물) 정보를 저장하는
	 * Bean 클래스(DTO or VO) 정의
	 * 
	 */
	
	private int qna_code;
	private String member_id;
	private String qna_pass;
	private String qna_subject;
	private String qna_content;
	private String qna_file;
	private String qna_real_file;
	private int qna_re_ref;
	private int qna_re_lev;
	private int qna_re_seq;
	private Date qna_date;
	private String qna_secret;
	
	
	public int getQna_code() {
		return qna_code;
	}
	public void setQna_code(int qna_code) {
		this.qna_code = qna_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getQna_pass() {
		return qna_pass;
	}
	public void setQna_pass(String qna_pass) {
		this.qna_pass = qna_pass;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public String getQna_real_file() {
		return qna_real_file;
	}
	public void setQna_real_file(String qna_real_file) {
		this.qna_real_file = qna_real_file;
	}
	public int getQna_re_ref() {
		return qna_re_ref;
	}
	public void setQna_re_ref(int qna_re_ref) {
		this.qna_re_ref = qna_re_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}

	public String getQna_secret() {
		return qna_secret;
	}
	public void setQna_secret(String qna_secret) {
		this.qna_secret = qna_secret;
	}
	
	@Override
	public String toString() {
		return "QnaBean [qna_code=" + qna_code + ", member_id=" + member_id + ", qna_pass=" + qna_pass
				+ ", qna_subject=" + qna_subject + ", qna_content=" + qna_content + ", qna_file=" + qna_file
				+ ", qna_real_file=" + qna_real_file + ", qna_re_ref=" + qna_re_ref + ", qna_re_lev=" + qna_re_lev
				+ ", qna_re_seq=" + qna_re_seq + ", qna_date=" + qna_date + ", qna_secret=" + qna_secret + "]";
	}
	
}