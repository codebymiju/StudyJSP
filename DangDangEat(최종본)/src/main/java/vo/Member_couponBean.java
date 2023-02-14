package vo;


public class Member_couponBean {


	private String member_id; //회원아이디(FK)
	private String coupon_code; //쿠폰 코드(FK)
	private String mc_used;  // 사용유무 (Y / N )
	
	private String target_sd;//mc_view
	private String target_ed;//mc_view 
	private String mc_stat;//mc_view
	
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}
	public String getMc_used() {
		return mc_used;
	}
	public void setMc_used(String mc_used) {
		this.mc_used = mc_used;
	}
	public String getTarget_sd() {
		return target_sd;
	}
	public void setTarget_sd(String target_sd) {
		this.target_sd = target_sd;
	}
	public String getTarget_ed() {
		return target_ed;
	}
	public void setTarget_ed(String target_ed) {
		this.target_ed = target_ed;
	}
	public String getMc_stat() {
		return mc_stat;
	}
	public void setMc_stat(String mc_stat) {
		this.mc_stat = mc_stat;
	}
	
	
	@Override
	public String toString() {
		return "Member_couponBean [member_id=" + member_id + ", coupon_code=" + coupon_code + ", mc_used=" + mc_used
				+ ", target_sd=" + target_sd + ", target_ed=" + target_ed + ", mc_stat=" + mc_stat + "]";
	}
	
	

	
	

	


}
