package vo;

public class paymentsBean {
	// 결제 정보를 저장하는 paymentsBean
	
	private int pay_number; // 최종주문번호
	private int order_code; // 주문번호(orders 테이블용)
	private int pay_amount; // 주문금액
	private String cp_code; // 사용한 쿠폰 코드
	private int cp_discount_amount; // 할인금액
	
	public int getPay_number() {
		return pay_number;
	}
	public void setPay_number(int pay_number) {
		this.pay_number = pay_number;
	}
	public int getOrder_code() {
		return order_code;
	}
	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}
	public int getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	public int getCp_discount_amount() {
		return cp_discount_amount;
	}
	public void setCp_discount_amount(int cp_discount_amount) {
		this.cp_discount_amount = cp_discount_amount;
	}
	
	@Override
	public String toString() {
		return "paymentsBean [pay_number=" + pay_number + ", order_code=" + order_code + ", pay_amount=" + pay_amount
				+ ", cp_code=" + cp_code + ", cp_discount_amount=" + cp_discount_amount + "]";
	}

}
