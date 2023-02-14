package vo;

import java.sql.Date;


/*
+-------------------+--------------+------+-----+---------+-------+
| Field             | Type         | Null | Key | Default | Extra |
+-------------------+--------------+------+-----+---------+-------+
| member_id         | varchar(20)  | NO   | PRI | NULL    |       |
| member_pass       | varchar(200) | NO   |     | NULL    |       |
| member_email      | varchar(50)  | NO   | UNI | NULL    |       |
| member_name       | varchar(20)  | NO   |     | NULL    |       |
| member_mobile     | varchar(15)  | NO   |     | NULL    |       |
| member_addr1      | varchar(100) | NO   |     | NULL    |       |
| member_addr2      | varchar(100) | NO   |     | NULL    |       |
| member_postcode   | varchar(6)   | NO   |     | NULL    |       |
| member_date       | date         | NO   |     | NULL    |       |
| member_point      | int          | YES  |     | NULL    |       |
| member_birth      | date         | YES  |     | NULL    |       |
| member_admin      | varchar(1)   | YES  |     | N       |       |
| member_status     | varchar(1)   | YES  |     | NULL    |       |
| member_authStatus | varchar(1)   | YES  |     | N       |       |
+-------------------+--------------+------+-----+---------+-------+
 */

public class MemberBean {
	
	private String member_id;
	private String member_pass;
	private String member_email;
	private String member_name;
	private String member_mobile;
	private String member_addr1;
	private String member_addr2;
	private String member_postcode;
	private Date member_date;
	private int member_point;
	private Date member_birth;
	private String member_admin;
	private String member_status;
	private String member_authStatus;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_mobile() {
		return member_mobile;
	}
	public void setMember_mobile(String member_mobile) {
		this.member_mobile = member_mobile;
	}
	public String getMember_addr1() {
		return member_addr1;
	}
	public void setMember_addr1(String member_addr1) {
		this.member_addr1 = member_addr1;
	}
	public String getMember_addr2() {
		return member_addr2;
	}
	public void setMember_addr2(String member_addr2) {
		this.member_addr2 = member_addr2;
	}
	public String getMember_postcode() {
		return member_postcode;
	}
	public void setMember_postcode(String member_postcode) {
		this.member_postcode = member_postcode;
	}
	public Date getMember_date() {
		return member_date;
	}
	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	public Date getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_admin() {
		return member_admin;
	}
	public void setMember_admin(String member_admin) {
		this.member_admin = member_admin;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public String getMember_authStatus() {
		return member_authStatus;
	}
	public void setMember_authStatus(String member_authStatus) {
		this.member_authStatus = member_authStatus;
	}
	
	@Override
	public String toString() {
		return "MemberBean [member_id=" + member_id + ", member_pass=" + member_pass + ", member_email=" + member_email
				+ ", member_name=" + member_name + ", member_mobile=" + member_mobile + ", member_addr1=" + member_addr1
				+ ", member_addr2=" + member_addr2 + ", member_postcode=" + member_postcode + ", member_date="
				+ member_date + ", member_point=" + member_point + ", member_birth=" + member_birth + ", member_admin="
				+ member_admin + ", member_status=" + member_status + ", member_authStatus=" + member_authStatus + "]";
	}
	
}
