package vo;

import java.sql.Date;

/*
 * DangDangEat 데이터베이스 생성 및 product 테이블 정의
pro_code 상품코드  //auto increment 주기
pro_name 상품명
cate_code 카테고리
pro_brand 브랜드
pro_option 옵션
pro_qty 수량
pro_price 가격
pro_yn 판매여부
pro_thumb 썸네일명 (원본파일명)
pro_real_thumb 썸네일명 (저장파일명)
pro_img 상세이미지 (원본파일명)
pro_real_img 상세이미지  (저장파일명)
pro_info 상세정보
pro_date 등록날짜
 * 
CREATE TABLE product (
	   pro_code INT PRIMARY KEY AUTO_INCREMENT,
	   pro_name VARCHAR(30),
	   cate_code INT,
	   pro_brand VARCHAR(30),
	   pro_option VARCHAR(30),
	   pro_qty INT,
	   pro_price INT,
	   pro_yn VARCHAR(10),
	   pro_thumb VARCHAR(500),
	   pro_real_thumb VARCHAR(500),
	   pro_img VARCHAR(500), 
	   pro_real_img VARCHAR(500), 
	   pro_info VARCHAR(500),
	   pro_date DATETIME
	);
 * 
  * 
 */
public class ProductBean {
	
	private int pro_code;
	private String pro_name;
	private int cate_code;
	private String pro_brand;
	private String pro_option;
	private int pro_qty;
	private int pro_price;   // ** DB 수정필!! String -> INT 
	private String pro_yn;
	private String pro_thumb;
	private String pro_real_thumb;
	private String pro_img;
	private String pro_real_img;
	private String pro_info;
	private String pro_date;
	
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getCate_code() {
		return cate_code;
	}
	public void setCate_code(int cate_code) {
		this.cate_code = cate_code;
	}
	public String getPro_brand() {
		return pro_brand;
	}
	public void setPro_brand(String pro_brand) {
		this.pro_brand = pro_brand;
	}
	public String getPro_option() {
		return pro_option;
	}
	public void setPro_option(String pro_option) {
		this.pro_option = pro_option;
	}
	public int getPro_qty() {
		return pro_qty;
	}
	public void setPro_qty(int pro_qty) {
		this.pro_qty = pro_qty;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_yn() {
		return pro_yn;
	}
	public void setPro_yn(String pro_yn) {
		this.pro_yn = pro_yn;
	}
	public String getPro_thumb() {
		return pro_thumb;
	}
	public void setPro_thumb(String pro_thumb) {
		this.pro_thumb = pro_thumb;
	}
	public String getPro_real_thumb() {
		return pro_real_thumb;
	}
	public void setPro_real_thumb(String pro_real_thumb) {
		this.pro_real_thumb = pro_real_thumb;
	}
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	public String getPro_real_img() {
		return pro_real_img;
	}
	public void setPro_real_img(String pro_real_img) {
		this.pro_real_img = pro_real_img;
	}
	public String getPro_info() {
		return pro_info;
	}
	public void setPro_info(String pro_info) {
		this.pro_info = pro_info;
	}
	public String getPro_date() {
		return pro_date;
	}
	public void setPro_date(String pro_date) {
		this.pro_date = pro_date;
	}
	
	@Override
	public String toString() {
		return "ProductBean [pro_code=" + pro_code + ", pro_name=" + pro_name + ", cate_code=" + cate_code
				+ ", pro_brand=" + pro_brand + ", pro_option=" + pro_option + ", pro_qty=" + pro_qty + ", pro_price="
				+ pro_price + ", pro_yn=" + pro_yn + ", pro_thumb=" + pro_thumb + ", pro_real_thumb=" + pro_real_thumb
				+ ", pro_img=" + pro_img + ", pro_real_img=" + pro_real_img + ", pro_info=" + pro_info + ", pro_date="
				+ pro_date + "]";
	}

	
//	@Override
//	public String toString() {
//		return "ProductBean [pro_code=" + pro_code + ", pro_name=" + pro_name + ", cate_code=" + cate_code
//				+ ", pro_brand=" + pro_brand + ", pro_option=" + pro_option + ", pro_qty=" + pro_qty + ", pro_price="
//				+ pro_price + ", pro_yn=" + pro_yn + ", pro_thumb=" + pro_thumb + ", pro_real_thumb=" + pro_real_thumb
//				+ ", pro_img=" + pro_img + ", pro_real_img=" + pro_real_img + ", pro_info=" + pro_info + ", pro_date="
//				+ pro_date + "]";
//	}

	

	
	
}














