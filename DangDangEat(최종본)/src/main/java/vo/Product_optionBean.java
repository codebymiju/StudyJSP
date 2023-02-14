package vo;
/*
 * product_option 테이블 정의
CREATE TABLE product_option ( 
	pro_code INT, //상품코드
	option_code INT,  //옵션코드 
	option_name VARCHAR(10), //옵션 네임 
	FOREIGN KEY(pro_code) REFERENCES product(pro_code) );
 */

public class Product_optionBean {
	
	private int pro_code;
//	private int option_code;    ** 1/1 옵션코드 삭제해야함!
	private String option_name;

	public int getPro_code() {
		return pro_code;
	}


	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}


	public String getOption_name() {
		return option_name;
	}


	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}


	public Product_optionBean(int pro_code, int option_code, String option_name) {
		super();
		this.pro_code = pro_code;
		this.option_name = option_name;
	}

	
	public Product_optionBean() {
		super();
	}


	@Override
	public String toString() {
		return "Product_optionBean [pro_code=" + pro_code + ", option_name=" + option_name + "]";
	}


	
	
	
}
