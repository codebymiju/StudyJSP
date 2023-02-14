package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ProductDeleteProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		System.out.println(request.getParameter("pro_name"));
		//상품코드 가져오기 
		ProductBean product = new ProductBean();
		product.setPro_code(Integer.parseInt(request.getParameter("pro_code")));
		
		
		try {
			//ProductDeleteProService 클래스의 removeBoard() 메서드를 호출하여 글 삭제 작업 수행
			//파라미터 : product  리턴타입 : boolean(isDeleteSuccess) 
			ProductDeleteProService service = new ProductDeleteProService();
			boolean isDeleteSuccess = service.removeProduct(product);
			
			//삭제 가능여부 판별 
			if(!isDeleteSuccess) { //삭제 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
				
			} else { //삭제 성공 시 
				
				String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
				String realPath = request.getServletContext().getRealPath(uploadPath);
				
				// 업로드 된 실제 파일 삭제
				File f = new File(realPath, product.getPro_real_thumb()); //썸네일
				File f2 = new File(realPath, product.getPro_real_img()); //상세이미지
				
				// 해당 디렉토리 및 파일 존재 여부 판별(썸네일)
				if(f.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				
				// 해당 디렉토리 및 파일 존재 여부 판별(상품 상세이미지)
				if(f2.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f2.delete();
				}
				
				forward = new ActionForward();
				forward.setPath("AdminProductList.ad");
				forward.setRedirect(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
