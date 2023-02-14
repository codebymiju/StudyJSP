package jsp09_jdbc_dao;

public class StudentDTO {
	// 1. 데이터를 저장하는데 사용할 인스턴스 변수 선언
	private int idx;
	private String name;
	
	// 2. 멤버변수에 접근할 Getter/Setter 메서드 정의(alt_shifr_s_r)
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
