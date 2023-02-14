package vo;

public class WishProductBean {
	
	
	/*
	 create view wish_product_view
   as    select c.pro_code, p.pro_name, p.cate_code, p.pro_brand, p.pro_price, c.cart_wishlist
      from cart c join product p 
      on (c.pro_code = p.pro_code);
	 */
	
	
   private int pro_code; // 상품코드
   private String pro_name; // 상품명
   private int cate_code; // 카테고리
   private String pro_brand; // 상품브랜드
   private int pro_price; // 상품가격
   private int cart_wishlist; // 위시리스트 여부
   private int wish_count; // 찜 갯수
   
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
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	
	public int getCart_wishlist() {
		return cart_wishlist;
	}
	public void setCart_wishlist(int cart_wishlist) {
		this.cart_wishlist = cart_wishlist;
	}
	public int getWish_count() {
		return wish_count;
	}
	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}
	@Override
	public String toString() {
		return "WishProductBean [pro_code=" + pro_code + ", pro_name=" + pro_name + ", cate_code=" + cate_code
				+ ", pro_brand=" + pro_brand + ", pro_price=" + pro_price + ", cart_wishlist=" + cart_wishlist
				+ ", wish_count=" + wish_count + "]";
	}
	
	
   
}
