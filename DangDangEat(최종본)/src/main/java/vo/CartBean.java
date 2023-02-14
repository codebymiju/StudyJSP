package vo;

public class CartBean {
	private int cart_code;
	private String member_id;
	private int pro_code;
	private int cart_amount;
	private int cart_ischecked;
	private int cart_wishlist;
	
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
	
	@Override
	public String toString() {
		return "CartBean [cart_code=" + cart_code + ", member_id=" + member_id + ", pro_code=" + pro_code
				+ ", cart_amount=" + cart_amount + ", cart_ischecked=" + cart_ischecked + ", cart_wishlist="
				+ cart_wishlist + "]";
	}
	
	
}
