package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import db.JdbcUtil;
import vo.ProductBean;

//실제 비즈니스 로직을 수행하는 ProductDAO 클래스 정의
public class ProductDAO {
	// ------------ 싱글톤 디자인 패턴을 활용한 ProductDAO 인스턴스 생성 작업 -------------
	// 1. 외부에서 인스턴스 생성이 불가능하도록 생성자를 private 접근제한자로 선언
	// 2. 자신의 클래스 내에서 직접 인스턴스를 생성하여 멤버변수에 저장
	//    => 인스턴스 생성없이 클래스가 메모리에 로딩될 때 함께 로딩되도록 static 변수로 선언
	//    => 외부에서 접근하여 함부로 값을 변경할 수 없도록 private 접근제한자로 선언
	// 3. 생성된 인스턴스를 외부로 리턴하는 Getter 메서드 정의
	//    => 인스턴스 생성없이 클래스가 메모리에 로딩될 때 함께 로딩되도록 static 메서드로 선언
	//    => 누구나 접근 가능하도록 public 접근제한자로 선언
	private ProductDAO() {}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}
	//------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// ----------------------------------------------------------------------------------
	// 상품 등록 작업 수행
	// 파라미터 : ProductBean 객체   리턴타입 : int()
	public int insertProduct(ProductBean product) {
		//System.out.println("ProductBean - insertProduct()");

		//INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;

		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt = null;

		try {

			String sql = "INSERT INTO product "
					+ "(`pro_name`, `cate_code`, `pro_brand`, `pro_option`, `pro_qty`, `pro_price`, `pro_yn`,`pro_thumb`, `pro_real_thumb`,`pro_img`,`pro_real_img`,`pro_info`,`pro_date`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getPro_name());
			pstmt.setInt(2, product.getCate_code());
			pstmt.setString(3, product.getPro_brand());		
			pstmt.setString(4, product.getPro_option());		
			pstmt.setInt(5, product.getPro_qty());		
			pstmt.setInt(6, product.getPro_price());				
			pstmt.setString(7,product.getPro_yn());		
			pstmt.setString(8, product.getPro_thumb());	//원본명	
			pstmt.setString(9, product.getPro_real_thumb()); //저장명		
			pstmt.setString(10, product.getPro_img()); //원본명		
			pstmt.setString(11, product.getPro_real_img());	//저장명			
			pstmt.setString(12, product.getPro_info());	
			System.out.println(product);

			System.out.println(pstmt);
			insertCount = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertProduct()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}

		return insertCount;
	}
	
	// 글 상세정보 조회	
	public ProductBean selectProduct(int pro_code) {
		ProductBean product = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			
			// Product 테이블에서 상품코드(pro_code)가 일치하는 1개 레코드 조회		
			
			String sql = "SELECT * FROM product WHERE pro_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();		
			
			//조회 결과가 있을 경우
			if(rs.next()) {
				//ProductBean 객체(product) 생성 후 조회 데이터 저장
				product = new ProductBean();
				product.setPro_code(rs.getInt("pro_code"));
//				System.out.println("1::::::::::"+rs.getInt("pro_code"));
//				System.out.println("2::::::::::"+rs.getString("pro_code"));
				product.setPro_name(rs.getString("pro_name"));
				product.setCate_code(rs.getInt("cate_code"));
				
				if(rs.getString("pro_brand").equals("1")) {
					product.setPro_brand("-");
				} else if (rs.getString("pro_brand").equals("2")) {
					product.setPro_brand("LILY'S KITCHEN");
				} else if (rs.getString("pro_brand").equals("3")) {
					product.setPro_brand("PETSGREEN");
				} else if (rs.getString("pro_brand").equals("4")) {
					product.setPro_brand("RICHZ BOX");
				} else {
					product.setPro_brand("LORENZ");					
				}
				
				product.setPro_option(rs.getString("pro_option"));
				product.setPro_qty(rs.getInt("pro_qty"));
				product.setPro_price(rs.getInt("pro_price"));
				product.setPro_yn(rs.getString("pro_yn"));
				product.setPro_thumb(rs.getString("pro_thumb"));
				product.setPro_real_thumb(rs.getString("pro_real_thumb"));
				product.setPro_img(rs.getString("pro_img"));
				product.setPro_real_img(rs.getString("pro_real_img"));
				product.setPro_info(rs.getString("pro_info"));
//			product.setPro_date(rs.getString("pro_date"));
				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("productDAO - selectProduct()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return product;
	}
	
	//상품 수정 
	public int updateProduct(ProductBean product) {
	
		int updateCount = 0;
		PreparedStatement pstmt = null;	
		
		try {
			// product 테이블에서 상품명, 카테고리, 브랜드, 옵션, 
			// 수량, 가격, 판매여부, 상세정보 변경 (상품코드는 고정)
			String sql = "UPDATE product SET pro_name=?, cate_code=?, "
					+ "pro_brand=?, pro_option=?, pro_qty=?, pro_price=?, pro_yn=?, pro_info=? ";

			// 1) 단, 상품썸네일(pro_thumb)이 null이 아닐 경우 파일명도 수정
			// => 즉, 파일명을 수정하는 SET 절을 문장에 추가 결합
			if(product.getPro_thumb() != null) {
				sql += " , pro_thumb = ? , pro_real_thumb = ?";
			} 
			
			// 2) 단, 상품이미지(pro_img) null이 아닐 경우 파일명도 수정
			if(product.getPro_img() != null) {
				sql += " , pro_img = ? , pro_real_img = ?";
			} 			
			
			sql += " WHERE pro_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getPro_name());
			pstmt.setInt(2, product.getCate_code());
			pstmt.setString(3, product.getPro_brand());		
			pstmt.setString(4, product.getPro_option());		
			pstmt.setInt(5, product.getPro_qty());		
			pstmt.setInt(6, product.getPro_price());				
			pstmt.setString(7,product.getPro_yn());		
			pstmt.setString(8, product.getPro_info());	
			System.out.println(product);
			
			//단, 파일명(pro_thumb)이 null이 아닐 경우에만
			//파일명 파라미터를 교체하는 setXXX() 메서드 호출
			//=> 또한 null이 아닐 때는 글번호 파라미터가 5번, 아니면 3번
			if(product.getPro_thumb() != null) {
				pstmt.setString(9, product.getPro_thumb());	//원본명	
				pstmt.setString(10, product.getPro_real_thumb()); //저장명	
			} 
			
			if(product.getPro_img() != null) {
				
				if(product.getPro_thumb() != null) {
					pstmt.setString(11, product.getPro_img()); //원본명		
					pstmt.setString(12, product.getPro_real_img());	//저장명	
				} else {
					pstmt.setString(9, product.getPro_img()); //원본명		
					pstmt.setString(10, product.getPro_real_img());	//저장명						
				}
			}
			
			//pro_code Where문 (썸네일, 이미지 둘 다 null이 아닐경우 procode 13)
			if(product.getPro_thumb() !=null && product.getPro_img() != null ) {
				pstmt.setInt(13, product.getPro_code()); 
			} else if (product.getPro_thumb() == null && product.getPro_img() == null ) {
				//둘다 null일 경우 
				pstmt.setInt(9, product.getPro_code());
			} else { 
				//썸네일 또는 이미지파일이 하나라도 있을 경우 
				pstmt.setInt(11, product.getPro_code());
			}
			
			System.out.println(pstmt);
			
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - updateProduct()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	
	//상품 삭제 작업 수행
	public int deleteProduct(ProductBean product) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
		
			//상품코드에 해당하는 레코드 삭제
			String sql = "DELETE FROM product WHERE pro_Code = ? ";
			//String sql = "UPDATE product SET PRO_USE_YN ='N' WHERE pro_Code = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.getPro_code());
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("ProductDAO - deleteProduct()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}
	
	// 상품 리스트 조회
	public List<ProductBean> selectProductList(String keyword, String category, int startRow, int listLimit) {
		List<ProductBean> productList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			// product 테이블의 모든 레코드 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE pro_name LIKE '%검색어%')
			//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			// => 정렬 : 상품코드(board_re_ref) 기준 등록순으로 정렬
			// => 조회 시작 레코드 행번호(startRow) 부터 listLimit 갯수(10) 만큼만 조회		
			String sql = "SELECT * FROM product "
					+ "WHERE pro_name "
					+ "LIKE ? "
					+ " AND cate_code like ?"
					/*
					 * if(상품페이지일 경우) { + "AND pro__yn='1'" }
					 */
//					+ "AND pro_use_yn='Y'"
					+ "ORDER BY pro_code DESC ";
			
			//listLimit이 0보다 작을 경우 전체 검색을 하기위한 조건
			//0보다 클 경우 기존처럼 행번호, 갯수만큼 조회
			if(listLimit > 0) {
				sql += " LIMIT ?,?";
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + category + "%");
			
			//listLimit이 0보다 작을 경우 전체 검색을 하기위한 조건
			//0보다 클 경우 기존처럼 행번호, 갯수만큼 조회			
			if(listLimit > 0) {
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, listLimit);
			}
			
			System.out.println("pstmt::"+pstmt);
			rs = pstmt.executeQuery();

			// 전체 목록 저장할 List 객체 생성
			productList = new ArrayList<ProductBean>();
			
			// 조회 결과가 있을 경우
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setPro_code(rs.getInt("pro_code"));
				product.setPro_name(rs.getString("pro_name"));
				product.setCate_code(rs.getInt("cate_code"));
				
				if(rs.getString("pro_brand").equals("1")) {
					product.setPro_brand("-");
				} else if (rs.getString("pro_brand").equals("2")) {
					product.setPro_brand("LILY'S KITCHEN");
				} else if (rs.getString("pro_brand").equals("3")) {
					product.setPro_brand("PETSGREEN");
				} else if (rs.getString("pro_brand").equals("4")) {
					product.setPro_brand("RICHZ BOX");
				} else {
					product.setPro_brand("LORENZ");					
				}
				
				product.setPro_option(rs.getString("pro_option"));
				product.setPro_qty(rs.getInt("pro_qty"));
				product.setPro_price(rs.getInt("pro_price"));
				product.setPro_yn(rs.getString("pro_yn"));
				product.setPro_thumb(rs.getString("pro_thumb"));
				product.setPro_real_thumb(rs.getString("pro_real_thumb"));
				product.setPro_img(rs.getString("pro_img"));
				product.setPro_real_img(rs.getString("pro_real_img"));
				product.setPro_info(rs.getString("pro_info"));
				product.setPro_date(rs.getString("pro_date")); //pro_date를 String타입으로 Bean 저장했으므로 불러와야함
				
				System.out.println(product);
				
				// 전체 목록 저장하는 List 객체에 1개 게시물 정보가 저장된 ProductBean 객체 추가
				productList.add(product);
				
			}
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectProductList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return productList;
		
	}
	
	// 상품 리스트 갯수 조회
	public int selectProductListCount(String keyword,String category) {

		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// product 테이블의 모든 레코드 갯수 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE pro_name LIKE '%검색어%')
			//   (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//   (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			String sql = "SELECT COUNT(*) "
					+ "FROM product "
					+ "WHERE pro_name LIKE ?"
					+ " AND cate_code like ?"
					
					;
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + category + "%");
			
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 listCount 변수에 저장
			if(rs.next()) {
			listCount = rs.getInt(1);
			
			}
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectProductListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	
	public JSONArray selectNewProduct(int numberOfProducts) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray productList = null;
		try {
			// product 테이블의 모든 레코드 갯수 조회
			// => 제목에 검색어를 포함하는 레코드 조회(WHERE pro_name LIKE '%검색어%')
			//   (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
			//   (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
			String sql = "SELECT  pro_code, pro_name, pro_thumb, pro_real_thumb "
					+ " FROM product "
					+ " WHERE  pro_yn = 1 "
					+ " ORDER BY pro_date DESC"
					+ " LIMIT ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,numberOfProducts);
		
			rs = pstmt.executeQuery();
			System.out.println("실행됨");
			  productList = new JSONArray();
			// 조회 결과가 있을 경우 ArrayList에 저장 변수에 저장
			while(rs.next()) {
				JSONObject product = new JSONObject();
				product.put("pro_name", rs.getString("pro_name"));
				product.put("pro_code", rs.getInt("pro_code"));
				product.put("pro_thumb", rs.getString("pro_thumb"));
				product.put("pro_real_thumb", rs.getString("pro_real_thumb"));
				product.put("pro_name", rs.getString("pro_name"));
				productList.put(product);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - selectNewProduct(int numberOfProducts)");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}

	
} //class ProductDAO 끝

















