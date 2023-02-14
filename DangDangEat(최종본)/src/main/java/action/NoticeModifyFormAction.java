package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeModifyFormAction implements Action {

	
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			ActionForward forward = null;
			
			int notice_code = Integer.parseInt(request.getParameter("notice_code"));
//			System.out.println(notice_code);
			
			NoticeDetailService service = new NoticeDetailService();
			NoticeBean notice = service.getNotice(notice_code, false);
//			System.out.println(notice);
			
			// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
			request.setAttribute("notice", notice);
			
			// ActionForward 객체를 통해 notice_modify.jsp 페이지 포워딩 설정
			// => URL 유지 및 request 객체 유지를 위해 Dispatch 방식 포워딩
			forward = new ActionForward();
			forward.setPath("board/notice_modify.jsp");
			forward.setRedirect(false);
			
			return forward;
		}

	}