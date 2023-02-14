package vo;

import java.sql.Date;

/*
 CREATE TABLE coupon(
	cp_code VARCHAR(30)  primary key, -- 할인코드(PK)
	cp_name VARCHAR(30), -- 할인코드명
	cp_target VARCHAR(15), -- 타켓(birth, new_member, event )
	cp_discount_value INT, -- 할인율 / (단위:%)
	cp_startdate VARCHAR(10), -- 쿠폰시작일(YYYY-MM-DD): birth, new_member는 null
	cp_period INT, -- 기간/ (단위:일)
	cp_min_price INT, -- 최소 구매액
	cp_max_discount INT, -- 최대 할인액
	cp_status INT -- 쿠폰상태(0 삭제 , 1 존재)
 );
 */
/**
 * @author user
 *
 */
public class CouponBean {
	
	private String cp_code;//쿠폰코드(PK)
	private String cp_name;//할인코드명
	private Cp_target cp_target;//-- 타켓(birth, new_member, event )
	private int cp_discount_value; //할인율(%)
	private String cp_startdate;//쿠폰시작일:  쿠폰시작일(YYYY-MM-DD): birth, new_member는 null
	private int cp_period;//유효기간
	private int cp_min_price;//최소구매액
	private int cp_max_discount;//최대할인금액
	private int cp_status; //관리자가 (0 삭제 , 1 존재)
	
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	
	public Cp_target getCp_target() {
		return cp_target;
	}
	public void setCp_target(Cp_target cp_target) {
		this.cp_target = cp_target;
	}
	public int getCp_discount_value() {
		return cp_discount_value;
	}
	public void setCp_discount_value(int cp_discount_value) {
		this.cp_discount_value = cp_discount_value;
	}
	public String getCp_startdate() {
		return cp_startdate;
	}
	public void setCp_startdate(String cp_startdate) {
		this.cp_startdate = cp_startdate;
	}
	public int getCp_period() {
		return cp_period;
	}
	public void setCp_period(int cp_period) {
		this.cp_period = cp_period;
	}
	public int getCp_min_price() {
		return cp_min_price;
	}
	public void setCp_min_price(int cp_min_price) {
		this.cp_min_price = cp_min_price;
	}
	public int getCp_max_discount() {
		return cp_max_discount;
	}
	public void setCp_max_discount(int cp_max_discount) {
		this.cp_max_discount = cp_max_discount;
	}
	public int getCp_status() {
		return cp_status;
	}
	public void setCp_status(int cp_status) {
		this.cp_status = cp_status;
	}
	
	
	
	@Override
	public String toString() {
		return "CouponBean [cp_code=" + cp_code + ", cp_name=" + cp_name + ", cp_target=" + cp_target
				+ ", cp_discount_value=" + cp_discount_value + ", cp_startdate=" + cp_startdate + ", cp_period="
				+ cp_period + ", cp_min_price=" + cp_min_price + ", cp_max_discount=" + cp_max_discount + ", cp_status="
				+ cp_status + "]";
	}

	

	

}
