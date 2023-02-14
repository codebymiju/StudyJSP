package vo;

import java.sql.Date;

public class CommentBean {

	/*
	 *   	
	 CREATE TABLE comment (
     comment_code INT PRIMARY KEY AUTO_INCREMENT,
     review_code INT,
     member_id VARCHAR(20),
     comment_content VARCHAR(500),
     comment_date DATE,
     FOREIGN KEY(review_code) references review(review_code),
     FOREIGN KEY(member_id) references member(member_id)
    );	 	 
*/
	  private int comment_code;				
	  private int review_code;
	  private String member_id;
	  private String comment_content;
	  private Date comment_date;
	
	public int getComment_code() {
		return comment_code;
	}
	public void setComment_code(int comment_code) {
		this.comment_code = comment_code;
	}
	public int getReview_code() {
		return review_code;
	}
	public void setReview_code(int review_code) {
		this.review_code = review_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	
	@Override
	public String toString() {
		return "CommentBean [comment_code=" + comment_code + ", review_code=" + review_code + ", member_id=" + member_id
				+ ", comment_content=" + comment_content + ", comment_date=" + comment_date + "]";
	}
}
	  
