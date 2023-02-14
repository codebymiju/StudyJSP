package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public class MemberJSONListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// 회원 정보를 조회하여 List 객체에 담았다고 가정
		// List 객체 생성
		List<MemberBean2> memberList2 = new ArrayList<MemberBean2>();
		
		// 1명씩의 회원 정보를 MemberBean2 객체에 담아 List 객체에 추가
		memberList2.add(new MemberBean2("홍길동", 20, new AddressBean("부산", "아이티윌")));
		memberList2.add(new MemberBean2("이순신", 44, new AddressBean("서울", "네이버")));
		memberList2.add(new MemberBean2("강감찬", 30, new AddressBean("제주", "한라산")));
		memberList2.add(new MemberBean2("김제발", 35, new AddressBean("베트남", "하노이")));
		
		// 회원 정보가 담긴 List 객체를 JSONArray 객체로 변환
		JSONArray ja2 = new JSONArray(memberList2);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(ja2.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}











