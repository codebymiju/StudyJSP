package vo;

public class Order_productBean {
// 주문 상품 테이블 
	private int order_code;// 주문번호(fk)
	private int pro_code; //상품번호(fk)
	private int order_stock; //주문수량
	private int cart_code; // 23/01/03 추가된 항목
	
	public int getOrder_code() {
		return order_code;
	}
	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public int getOrder_stock() {
		return order_stock;
	}
	public void setOrder_stock(int order_stock) {
		this.order_stock = order_stock;
	}
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	
	@Override
	public String toString() {
		return "Order_productBean [order_code=" + order_code + ", pro_code=" + pro_code + ", order_stock=" + order_stock
				+ ", cart_code=" + cart_code + "]";
	}
	
}
