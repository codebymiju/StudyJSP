package vo;

public class cart_wish_proBean {
	/*
	 create view cart_wish_pro_view
   as    select cart_code, member_id, c.pro_code, c.cart_amount, cart_ischecked, cart_wishlist, pro_name, pro_brand, pro_price, pro_real_thumb
      from cart c join product p 
      on (c.pro_code = p.pro_code);
	 */
	
   private int cart_code; // 장바구니 코드
   private String member_id; // 주문회원 아이디
   private int pro_code; // 장바구니에 담긴 상품코드
   private int cart_amount; // 장바구니 수량
   private int cart_ischecked; // 장바구니 여부
   private int cart_wishlist; // 위시리스트 여부
   private String pro_name; // 장바구니 상품명
   private String pro_brand; // 장바구니 상품브랜드
   private int pro_price; // 장바구니 상품가격
   private String pro_real_thumb; // 장바구니 상품썸네일
   private int totalPrice; // 장바구니 총 가격
	   
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public int getCart_ischecked() {
		return cart_ischecked;
	}
	public void setCart_ischecked(int cart_ischecked) {
		this.cart_ischecked = cart_ischecked;
	}
	public int getCart_wishlist() {
		return cart_wishlist;
	}
	public void setCart_wishlist(int cart_wishlist) {
		this.cart_wishlist = cart_wishlist;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_brand() {
		return pro_brand;
	}
	public void setPro_brand(String pro_brand) {
		this.pro_brand = pro_brand;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_real_thumb() {
		return pro_real_thumb;
	}
	public void setPro_real_thumb(String pro_real_thumb) {
		this.pro_real_thumb = pro_real_thumb;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int totalPrice() {
		this.totalPrice = this.cart_amount * this.pro_price;
		return totalPrice;
	}
	
	@Override
	public String toString() {
		return "cart_wish_proBean [cart_code=" + cart_code + ", member_id=" + member_id + ", pro_code=" + pro_code
				+ ", cart_amount=" + cart_amount + ", cart_ischecked=" + cart_ischecked + ", cart_wishlist="
				+ cart_wishlist + ", pro_name=" + pro_name + ", pro_brand=" + pro_brand + ", pro_price=" + pro_price
				+ ", pro_real_thumb=" + pro_real_thumb + "]";
	}
	   
	   
}
