package svc;

import java.sql.Connection;
import java.util.List;

import org.json.JSONArray;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductListNewService {
	public JSONArray getNewProduct(int numberOfProduct) {
		ProductDAO dao = ProductDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		dao.setConnection(con);
		JSONArray productList = dao.selectNewProduct(5);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		return productList;
	}
}
