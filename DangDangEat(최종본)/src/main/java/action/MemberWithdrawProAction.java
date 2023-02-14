package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberCheckProService;
import svc.MemberWithdrawProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberWithdrawProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberWithdrawProAction");
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		try {
			// MemberBean 인스턴스 생성
			MemberBean member = new MemberBean();
			
			// 기존 비밀번호 확인 (MemberCheckProService - CheckRightPass())
			member.setMember_id(session.getAttribute("sId").toString());
//			member.setMember_pass(request.getParameter("oldPass"));
			// 패스워드 암호화(해싱)
			MyMessageDigest md = new MyMessageDigest("SHA-256");
			member.setMember_pass(md.hashing(request.getParameter("pass")));
			
			// 비밀번호 확인
			MemberCheckProService service = new MemberCheckProService();
			boolean isRightPass = service.CheckRightPass(member, true); // 비밀번호 있음
			
			if(isRightPass) { // 비밀번호 맞음
//				System.out.println("비밀번호 맞댕!");
				// MemberBean 파라미터 데이터 저장
				member.setMember_status("N");
				
				System.out.println(member);
				
				// MemberWithdrawProService 객체 생성 및 modifiMember 호출
				MemberWithdrawProService service2 = new MemberWithdrawProService();
				boolean isWithdrawSuccess = service2.withdrawMember(member);
				
				if(isWithdrawSuccess) { // 회원 탈퇴 성공
					
					// 세션 초기화 후 탈퇴 결과 페이지로 리다이렉트
					session.invalidate(); // 모든 세션 초기화
//					session.removeAttribute("sId"); // 세션 아이디만 초기화
					
					// MemberWithdrawResult.me 로 리다이렉트
					forward = new ActionForward();
					forward.setPath("MemberWithdrawResult.me");
					forward.setRedirect(true);
					
				} else { // 회원정보 수정 실패
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					
					out.println("<script>");
					out.println("alert('회원 탈퇴 못한댕!')");
					out.println("history.back()");
					out.println("</script>");
				}
				
			} else { // 비밀번호 틀림
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸댕!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
