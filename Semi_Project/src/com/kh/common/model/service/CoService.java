package com.kh.common.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.dao.CoDao;
import com.kh.product.model.vo.Product;

public class CoService {
	public ArrayList<Product> selectNewProduct() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new CoDao().selectNewProduct(conn);
		JDBCTemplate.close(conn);
		return list;
	}

}
