package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ProductInsertProService;
import svc.ProductModifyProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String realPath = "";
		
		// 수정 작업 결과에 따라 삭제할 파일이 달라지므로 파일명을 저장할 변수 선언
		String deleteFileName = "";
		
		try {
			String uploadPath = "upload"; // 업로드 가상디렉토리(이클립스)
			//업로드 실제 디렉토리(톰캣) 얻어오기 
			realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("실제 업로드 경로 : " + realPath);
			
			//만약, 해당 디렉토리가 존재하지 않을 경우 디렉토리 생성
			//=> java.io.File 클래스 인스턴스 생성(파라미터로 해당 디렉토리 전달)
			File f = new File(realPath);
			// => 단, File 객체가 생성되더라도 해당 디렉토리 또는 파일을 직접 생성 X
			 //실제 경로에 대상 존재 여부 판별 
			if(!f.exists()) { //해당 경로가 존재하지 않을 경우
				//File 객체의 mkdir() 메서드를 호출하여 경로 생성
				f.mkdir();
			}
			
			
			
			int fileSize = 1024 * 1024 * 10;
			// --------------------------------------------------------------------
			//파일 업로드 처리 (enctype="mutlipart/form-data") 를 위해
			// MultipartRequest 객체 생성 => cos.jar 라이브러리 필요
			MultipartRequest multi = new MultipartRequest(
					request, // 1)실제 요청 정보가 포함된 request 객체 
					realPath, // 2) 실제 업로드 경로
					fileSize, // 3) 업로드 파일 최대 사이즈
					"UTF-8",  // 4) 한글 파일명 처리 위한 인코딩 방식
					new DefaultFileRenamePolicy() // 5) 중복 파일명을 처리할 객체
					);


			// 전달받은 파라미터 데이터를 ProductBean 클래스 인스턴스 생성 후 저장
			ProductBean product = new ProductBean(); 
			
			if(multi.getParameter("pro_code") != null) {
				product.setPro_code(Integer.parseInt(multi.getParameter("pro_code")));
			}
			
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
			product.setPro_thumb(multi.getOriginalFileName("pro_thumb"));
			product.setPro_real_thumb(multi.getFilesystemName("pro_thumb"));

			product.setPro_img(multi.getOriginalFileName("pro_img"));
			product.setPro_real_img(multi.getFilesystemName("pro_img"));

			product.setPro_info(multi.getParameter("pro_info"));
			product.setPro_date(multi.getParameter("pro_date"));
			System.out.println(product);
			// => 만약, 수정할 파일을 선택하지 않았을 경우 파일명은 null값이 저장됨
			//------------------------------------------------------------
			//ProductModifyProService 클래스 인스턴스 생성 후 modifyProduct()
			//상품 수정 작업 요청
			//파라미터 : ProductBean객체  리턴타입 : boolean(isInsertSuccess)
			ProductModifyProService service = new ProductModifyProService();
			boolean isModifySuccess = service.modifyProduct(product);
				
				if(!isModifySuccess) { //수정 실패 시
				// 자바스크립트 사용하여 "상품 수정 실패!" 출력 후 이전페이지로 돌아가기	
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('상품 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");

			} else { //수정 성공 시
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

		return forward; // ProductFrontController 로 리턴
	}
			

}
