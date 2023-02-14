package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeDetailAction implements Action {	

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("NoticeDetailAction");
			
			ActionForward forward = null;
			
			// 상세정보 조회에 필요한 글번호 가져오기
			int notice_code = Integer.parseInt(request.getParameter("notice_code"));

			NoticeDetailService service = new NoticeDetailService();
			NoticeBean notice = service.getNotice(notice_code, true);
//			System.out.println(notice);
			
			// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
			request.setAttribute("notice", notice);
			
			forward = new ActionForward();
			forward.setPath("board/notice_detail.jsp");
			forward.setRedirect(false);
			
			return forward;
		}
	}