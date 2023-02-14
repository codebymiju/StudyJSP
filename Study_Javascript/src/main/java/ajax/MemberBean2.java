package ajax;

public class MemberBean2 {
	private String name;
	private int age;
	private AddressBean address; // 주소1, 주소2를 객체로 관리
	
	public MemberBean2() {}
	
	public MemberBean2(String name, int age, AddressBean address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public AddressBean getAddress() {
		return address;
	}
	public void setAddress(AddressBean address) {
		this.address = address;
	}
	
}
