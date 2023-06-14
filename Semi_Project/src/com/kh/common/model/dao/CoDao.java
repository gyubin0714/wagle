package com.kh.common.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.member.model.dao.MemDao;
import com.kh.product.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;


public class CoDao {
    private Properties prop = new Properties();
	
    public CoDao() {
		
		String file = MemDao.class.getResource("/sql/member/mem_mapper.xml").getPath();
		//System.out.println(file);
		
		//System.out.println(file);
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Product> selectNewProduct(Connection conn) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNewProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setFileNo(rset.getString("FILE_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRRODUCT_PRICE"));
				p.setMethod(rset.getString("METHOD"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				
				
				list.add(p);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
		
		
		
	}

}
