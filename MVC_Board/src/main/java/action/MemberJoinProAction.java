package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.MyMessageDigest;
import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			MemberBean member = new MemberBean();
				member.setName(request.getParameter("name"));
				member.setGender(request.getParameter("gender"));
				member.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));
				member.setId(request.getParameter("id"));
//				member.setPasswd(request.getParameter("passwd")); 
			
			// ---------------12/12--------------------------12/21------------------	
			// 패스워드 암호화(해싱) 기능 추가
			
			 MyMessageDigest md = new MyMessageDigest("SHA-256");
 			 member.setPasswd(md.hasing(request.getParameter("passwd"))); // 암호화 처리한 패스워드 저장 
				
			// ---------------12/12------------------------------------------------------	
				
			MemberJoinProService service = new MemberJoinProService();
			Boolean isJoinMember = service.joinMember(member);
			
			// 회원 가입 성공 여부 판별
			if(!isJoinMember) { // 실패시 (=아이디 등 중복일 경우)
				
				response.setContentType("text/html; charset=UTF-8"); // html 형식 넘겨준다는 표시
				PrintWriter out = response.getWriter(); // 자바 코드를 위해 html태그 출력
				out.println("<script>");
				out.println("alert('회원 가입 실패!')");
				out.println("history.back()");
				out.println("</script>");
			
			} else {
				forward = new ActionForward();
				forward.setPath("MemberJoinResult.me");
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			System.out.println("MemberJoinProAction - 오류");
			e.printStackTrace();
		}
		
		return forward;
	}

}
