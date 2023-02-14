package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ProductInsertProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductInsertProAction");

		ActionForward forward = null;

		try {

			String uploadPath = "upload"; // 업로드 가상디렉토리(이클립스)
			//업로드 실제 디렉토리(톰캣) 얻어오기 
			
			String realPath = request.getServletContext().getRealPath(uploadPath);
			//실제 업로드 경로(집) :
			// C:\Users\eyeds\eclipse_jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DangDangEat\ upload
			//실제 업로드 경로(학원) : D:\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DangDangEat\ upload
//			//String uploadPath = "ftp://db.itwillbs.dev:8030/upload"; // FTP 주소!!!체크필수!!!  12/30
			System.out.println("실제 업로드 경로 : " + realPath);
			int fileSize = 1024 * 1024 * 10;

			//파일 업로드 처리 
			MultipartRequest multi = new MultipartRequest(
					request, // 1)실제 요청 정보가 포함된 request 객체 
					realPath, // 2) 실제 업로드 경로
					fileSize, // 3) 업로드 파일 최대 사이즈
					"UTF-8",  // 4) 한글 파일명 처리 위한 인코딩 방식
					new DefaultFileRenamePolicy() // 5) 중복 파일명을 처리할 객체
					);


			// 전달받은 파라미터 데이터를 ProductBean 클래스 인스턴스 생성 후 저장
			ProductBean product = new ProductBean(); 
			//*pro_code는 DB에서 재생할꺼다!
			product.setPro_name(multi.getParameter("pro_name"));

			if(multi.getParameter("cate_code") != null) {
				product.setCate_code(Integer.parseInt(multi.getParameter("cate_code")));
			} 

			product.setPro_brand(multi.getParameter("pro_brand"));
			product.setPro_option(multi.getParameter("pro_option"));

			if(multi.getParameter("pro_qty") != null) {
				product.setPro_qty(Integer.parseInt(multi.getParameter("pro_qty")));
			}

			if(multi.getParameter("pro_price") != null) {
				product.setPro_price(Integer.parseInt(multi.getParameter("pro_price")));
			}

			product.setPro_yn(multi.getParameter("pro_yn"));
			

			// 파일명은 getParameter() 로 단순 처리 불가능
			// => 원본 파일명 : getOriginalFileName()
			//중복 처리된(실제 업로드 되는) 파일명 : getFilesystemName() 
			product.setPro_thumb(multi.getOriginalFileName("pro_thumb"));
			product.setPro_real_thumb(multi.getFilesystemName("pro_thumb"));

			product.setPro_img(multi.getOriginalFileName("pro_img"));
			product.setPro_real_img(multi.getFilesystemName("pro_img"));

			//textarea 내용 줄바꿈 함수 추가(replace)
			product.setPro_info(multi.getParameter("pro_info").replace("\r\n", "<br>")); 
			product.setPro_date(multi.getParameter("pro_date"));
			System.out.println(product);
			
			//------------------------------------------------------------
			//ProductInsertProService 클래스 인스턴스 생성 후 registProduct()
			//상품 등록 작업 요청
			//파라미터 : ProductBean객체  리턴타입 : boolean(isInsertSuccess)
			ProductInsertProService service = new ProductInsertProService();
			boolean isInsertSuccess = service.registProduct(product);

			//상품 등록 요청 처리 결과 판별
			if(!isInsertSuccess) { // 실패 시
				// 업로드 된 실제 파일 삭제
				File f = new File(realPath, product.getPro_thumb());
				f = new File(realPath, product.getPro_img());

				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}

				// 자바스크립트 사용하여 "글쓰기 실패!" 출력 후 이전페이지로 돌아가기	
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('상품 등록 실패!')");
				out.println("history.back()");
				out.println("</script>");
				// ActionForward 객체 생성하지 않음!! => null 값 전달

			} else { //성공 시
				// 포워딩 정보 저장을 위한 ActionForward 객체 생성
				// 포워딩 경로 : ProductList.pd, 포워딩 방식 : Redirect
				forward = new ActionForward();
				forward.setPath("AdminProductList.ad"); 
				forward.setRedirect(true);
			}

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward; // BoardFrontController 로 리턴
	}

}
