package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberCheckProService;
import svc.MemberModifyProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberModifyProAction");
		
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
			member.setMember_pass(md.hashing(request.getParameter("oldPass")));
			
			// 비밀번호 확인
			MemberCheckProService service = new MemberCheckProService();
			boolean isRightPass = service.CheckRightPass(member, true); // 비밀번호 있음
			
			if(isRightPass) { // 비밀번호 맞음
				System.out.println("비밀번호 맞댕!");
				// MemberBean 파라미터 데이터 저장
				// 신규 비밀번호 존재할 경우 신규 비밀번호 저장
				if(request.getParameter("newPass") != null && request.getParameter("newPass") != "") {
					System.out.println("새 비밀번호 만든댕!");
//					member.setMember_pass(request.getParameter("newPass"));
					// 패스워드 암호화(해싱)
					member.setMember_pass(md.hashing(request.getParameter("newPass")));
				}
				member.setMember_email(request.getParameter("email"));
				member.setMember_mobile(request.getParameter("mobile"));
				member.setMember_addr1(request.getParameter("addr1"));
				member.setMember_addr2(request.getParameter("addr2"));
				member.setMember_postcode(request.getParameter("postcode"));
				if(request.getParameter("birth") != null && !request.getParameter("birth").equals("")) {
				member.setMember_birth(Date.valueOf(request.getParameter("birth")));
				}
				
				System.out.println(member);
				
				// MemberModifyProService 객체 생성 및 modifiMember 호출
				MemberModifyProService service2 = new MemberModifyProService();
				boolean isModifiSuccess = service2.modifyMember(member);
				
				if(isModifiSuccess) { // 회원정보 수정 성공
					// MemberInfo.me 로 리다이렉트
					forward = new ActionForward();
					forward.setPath("MemberInfo.me");
					forward.setRedirect(true);
					
				} else { // 회원정보 수정 실패
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					
					out.println("<script>");
					out.println("alert('회원 정보 수정 못했댕!')");
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
		
		return forward; // MemberFrontController
	}

}
