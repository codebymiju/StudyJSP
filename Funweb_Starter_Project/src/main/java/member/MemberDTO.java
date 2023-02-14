package member;

import java.sql.Date;

/*
 funweb5 데이터베이스 생성
 회원(member) 테이블 생성
 
 CREATE TABLE member(
 id VARCHAR(16) PRIMARY KEY,
 pass VARCHAR(16) NOT NULL,
 name VARCHAR(20) NOT NULL,
 email VARCHAR(50) UNIQUE NOT NULL,
 mobile VARCHAR(20) UNIQUE NOT NULL,
 post_code VARCHAR(10) NOT NULL,
 address1 VARCHAR(100) NOT NULL,
 address2 VARCHAR(100) NOT NULL,
 phone VARCHAR(20) NOT NULL,
 date DATE NOT NULL
 );
 */

// member 테이블 1개 레코드 정보를 관리하는 MemberDTO 클래스 정의
public class MemberDTO {

	private String id;
	private String pass;
	private String name;
	private String email;
	private String mobile;
	private String post_code;
	private String address1;
	private String address2;
	private String phone;
	private Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() { // alt+shift+S+S
		return "MemberDTO [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + ", post_code="
				+ post_code + ", address1=" + address1 + ", address2=" + address2 + ", phone=" + phone + ", mobile="
				+ mobile + ", date=" + date + "]";
	}
}
