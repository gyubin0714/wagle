package com.kh.product.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;
import com.kh.product.model.vo.Interest;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductCategory;

public class ProductDao {

	private Properties prop = new Properties();

	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/product/product_mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int insertProduct(Connection conn, Product p) {

		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(p.getMemNo()));
			pstmt.setString(2, p.getProductName());
			pstmt.setString(3, p.getLocation());
			pstmt.setString(4, p.getProductStatus());
			pstmt.setString(5, p.getProductDesc());
			pstmt.setInt(6, p.getProductPrice());
			pstmt.setString(7, p.getProductDelv());
			pstmt.setInt(8, Integer.parseInt(p.getProductCate()));
			pstmt.setString(9, p.getMethod());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> list) {

		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			for(Attachment at : list) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());

				result *= pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;


	}


	public ArrayList<ProductCategory> selectCategoryList(Connection conn) {

		ArrayList<ProductCategory> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryList");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				ProductCategory pc = new ProductCategory();
				pc.setCateNo(rset.getInt("CATE_NO"));
				pc.setCateName(rset.getString("CATE_NAME"));
				pc.setCateParent(rset.getInt("CATE_PARENT"));

				list.add(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> selectProductList(Connection conn, int boardLimit) {

		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

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

	public ArrayList<Product> moreProductList(Connection conn, int startNum) {

		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("moreProductList");

		try {
			int boardLimit = startNum + 12;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startNum);
			pstmt.setInt(2, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

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

	public int increaseCount(Connection conn, int productNo) {

		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increaseCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public Product selectProduct(Connection conn, int ProductNo) {

		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ProductNo);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				p = new Product();

				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				p.setViewCnt(rset.getInt("VIEW_CNT"));
				p.setProductStatus(rset.getString("PRODUCT_STATUS"));
				p.setProductDelv(rset.getString("PRODUCT_DELV"));
				p.setLocation(rset.getString("LOACATION"));
				p.setProductDesc(rset.getString("PRODUCT_DESC"));
				p.setNickName(rset.getString("NICKNAME"));
				p.setProductCate(rset.getString("CATE_NAME"));
				p.setCateNo(rset.getInt("CATE_NO"));
				p.setCateParent(rset.getInt("CATE_PARENT"));
				p.setMethod(rset.getString("METHOD"));
				p.setMemNo(rset.getString("MEM_NO"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}

	public ArrayList<Attachment> selectAttachment(Connection conn, int productNo) {

		ArrayList<Attachment> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));

				list.add(at);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int updateProduct(Connection conn, Product p) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProduct");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getProductName());
			pstmt.setString(2, p.getLocation());
			pstmt.setString(3, p.getProductStatus());
			pstmt.setString(4, p.getProductDesc());
			pstmt.setInt(5, p.getProductPrice());
			pstmt.setString(6, p.getProductDelv());
			pstmt.setInt(7, Integer.parseInt(p.getProductCate()));
			pstmt.setString(8, p.getMethod());
			pstmt.setInt(9, p.getProductNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateTitleAttachment(Connection conn, Attachment titleAt) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, titleAt.getOriginName());
			pstmt.setString(2, titleAt.getChangeName());
			pstmt.setString(3,  titleAt.getFilePath());
			pstmt.setInt(4, titleAt.getFileNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}



	public int updateAttachment(Connection conn, ArrayList<Attachment> list) {

		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			for(Attachment at : list) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileNo());

				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int insertNewAttachment(Connection conn, ArrayList<Attachment> list) {

		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			for(Attachment at : list) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, at.getRefBno());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getChangeName());
				pstmt.setString(4, at.getFilePath());
				pstmt.setInt(5, at.getFileLevel());

				result *= pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public int deleteProduct(Connection conn, int productNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteProduct");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Interest selectInterest(Connection conn, Interest itst) {

		Interest interest = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInterest");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, itst.getProductNo());
			pstmt.setInt(2, itst.getMemNo());

			rset = pstmt.executeQuery();

			if(rset.next()) {
				interest = new Interest(rset.getInt("PRODUCT_NO"),
						rset.getInt("MEM_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return interest;
	}

	public int deleteInterest(Connection conn, Interest itst) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInterest");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, itst.getProductNo());
			pstmt.setInt(2, itst.getMemNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertInterest(Connection conn, Interest itst) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInterest");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, itst.getProductNo());
			pstmt.setInt(2, itst.getMemNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int interestCount(Connection conn, int productNo) {

		int interestCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("interestCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				interestCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return interestCount;
	}

	public ArrayList<ProductCategory> selectCategorySecond(Connection conn, int cateParent){

		ArrayList<ProductCategory> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategorySecond");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cateParent );
			rset = pstmt.executeQuery();

			while(rset.next()) {
				ProductCategory p = new ProductCategory();
				p.setCateNo(rset.getInt("CATE_NO"));
				p.setCateName(rset.getString("CATE_NAME"));
				p.setCateParent(rset.getInt("CATE_PARENT"));

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

	public ArrayList<Product> selectSaleProduct(Connection conn, int memNo, int productNo) {

		ArrayList<Product> pList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSaleProduct");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memNo);

			pstmt.setInt(2, productNo);

			rset = pstmt.executeQuery();

			while(rset.next()){

				Product saleP = new Product();
				saleP.setProductNo(rset.getInt("PRODUCT_NO"));
				saleP.setProductName(rset.getString("PRODUCT_NAME"));
				saleP.setProductStatus(rset.getString("PRODUCT_STATUS"));
				saleP.setCreateDt(rset.getDate("CREATE_DT"));

				pList.add(saleP);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return pList;
	}

	public Attachment selectUserProfile(Connection conn, int memNo) {

		Attachment profile = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUserProfile");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();

			if(rset.next()) {

				profile.setFileNo(rset.getInt("FILE_NO"));
				profile.setOriginName(rset.getString("ORIGIN_NAME"));
				profile.setChangeName(rset.getString("CHANGE_NAME"));
				profile.setFilePath(rset.getString("FILE_PATH"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return profile;
	}

	public Member selectMember(Connection conn, int memNo) {
		Member m  = new Member();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectMember");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				m.setMemNo(memNo);
				m.setMemId(rset.getString("MEM_ID"));
				m.setNickname(rset.getString("NICKNAME"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return m;
	}

	public ArrayList<Attachment> selectSaleAttachment(Connection conn, ArrayList<Product> sellPnoList){
		ArrayList<Attachment> pListAt = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSaleAttachment");

		try {

			for(Product p : sellPnoList) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, Integer.parseInt(p.getMemNo()));
				pstmt.setInt(2, p.getProductNo());

				rset = pstmt.executeQuery();

				while(rset.next()) {
					Attachment at = new Attachment();
					at.setFileNo(rset.getInt("FILE_NO"));
					at.setOriginName(rset.getString("ORIGIN_NAME"));
					at.setChangeName(rset.getString("CHANGE_NAME"));
					at.setFilePath(rset.getString("FILE_PATH"));

					pListAt.add(at);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return pListAt;

	}

	public ArrayList<Product> searchTitle(Connection conn, String searchText, int boardLimit) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchTitle");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}


	public ArrayList<Product> searchMemId(Connection conn, String searchText, int boardLimit) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchMemId");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}

	public ArrayList<Product> searchAll(Connection conn, String searchText, int boardLimit) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchAll");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}

	public int searchTitleCount(Connection conn, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchTitleCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;

	}

	public int searchMemIdCount(Connection conn, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchMemIdCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;

	}

	public int searchAllCount(Connection conn, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchAllCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;

	}

	public ArrayList<Product> moreSearchTitle(Connection conn, String searchText, int startNum) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("moreSearchTitle");

		try {
			int boardLimit = startNum + 12;

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}


	public ArrayList<Product> moreSearchMemId(Connection conn, String searchText, int startNum) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("moreSearchMemId");

		try {
			int boardLimit = startNum + 12;

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}

	public ArrayList<Product> moreSearchAll(Connection conn, String searchText, int startNum) {

		ArrayList<Product> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("moreSearchAll");

		try {

			int boardLimit = startNum + 12;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);

			pstmt.setInt(3, startNum);
			pstmt.setInt(4, boardLimit);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setTitleimg(rset.getString("TITLEIMG"));

				searchList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}


}



