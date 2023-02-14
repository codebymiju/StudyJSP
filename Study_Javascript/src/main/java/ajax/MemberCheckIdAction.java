package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberCheckIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			// 전달받은 id 파라미터 가져오기
			String id = request.getParameter("id");
			
			// MemberCheckIdService 클래스 인스턴스 생성 후
			// isExistId() 메서드를 호출하여 아이디 존재 여부 판별
			// => 파라미터 : 아이디    리턴타입 : boolean(isExist)
			MemberCheckIdService service = new MemberCheckIdService(); 
			boolean isExist = service.isExistId(id);
			
			if(!isExist) { // 아이디 존재하지 않을 경우
				// "false" 출력
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("false");
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}









