package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.Interest;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductCategory;

public class ProductService {



	public int insertProduct(Product p, ArrayList<Attachment> list) {
		Connection conn = getConnection();

		// result1 = product
		int result1 = new ProductDao().insertProduct(conn, p);

		// result2 = attachment
		int result2 = new ProductDao().insertAttachment(conn, list);

		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return (result1 * result2);

	}

	public ArrayList<ProductCategory> selectCategoryList() {

		Connection conn = getConnection();

		ArrayList<ProductCategory> list = new ProductDao().selectCategoryList(conn);

		close(conn);

		return list;
	}

	public ArrayList<Product> selectProductList(int boardLimit) {

		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDao().selectProductList(conn, boardLimit);

		close(conn);

		return list;
	}

	public ArrayList<Product> moreProductList(int startNum) {

		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDao().moreProductList(conn, startNum);

		close(conn);

		return list;
	}

	public ArrayList<Product> moreSearchProductList(int startNum, String searchSelect, String searchText) {

		Connection conn = getConnection();
		ArrayList<Product> searchList = new ArrayList();

		if(searchSelect.equals("title")) {
			searchList = new ProductDao().moreSearchTitle(conn, searchText, startNum);
		} else if(searchSelect.equals("memId")) {
			searchList = new ProductDao().moreSearchMemId(conn, searchText, startNum);
		} else {
			searchList = new ProductDao().moreSearchAll(conn, searchText, startNum);
		}

		close(conn);

		return searchList;

	}

	public int increaseCount(int productNo) {

		Connection conn = getConnection();

		int result = new ProductDao().increaseCount(conn, productNo);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;

	}

	public Product selectProduct(int productNo) {

		Connection conn = getConnection();

		Product p = new ProductDao().selectProduct(conn, productNo);

		close(conn);

		return p;

	}

	public ArrayList<Attachment> selectAttachment(int productNo) {

		Connection conn = getConnection();

		ArrayList<Attachment> list = new ProductDao().selectAttachment(conn, productNo);

		close(conn);

		return list;
	}

	public int updateProduct(Product p, Attachment titleAt, ArrayList<Attachment> list) {
		Connection conn = getConnection();

		int result1 = new ProductDao().updateProduct(conn, p);

		//attachment 작업
		int result2 = 1;
		int result3 = 1;

		// 1. titleImg : 새롭게 첨부파일이 있을 경우
		if(titleAt != null) {
			result2 = new ProductDao().updateTitleAttachment(conn, titleAt);
		}
		
		// 2. contentImg : 새롭게 첨부파일이 있을 경우
		if(list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				// 이미지 수정 => UPDATE
				if(list.get(i).getFileNo() != 0) {
					result3 = new ProductDao().updateAttachment(conn, list);
				} else {
					result3 = new ProductDao().insertNewAttachment(conn, list);
				}
			}
			
		}
		
		if((result1 * result2 * result3) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return ((result1 * result2 * result3));
	}

	public int deleteProduct(int productNo) {
		Connection conn = getConnection();

		int result = new ProductDao().deleteProduct(conn, productNo);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public Interest selectInterest(Interest itst) {

		Connection conn = getConnection();

		Interest interest = new ProductDao().selectInterest(conn, itst);

		return interest;

	}

	public int interestUpdate(Interest itst) {
		Connection conn = getConnection();

		int result = 0;
		// 1. 게시글 번호, 멤버번호 있는지 확인  
		Interest interest = new ProductDao().selectInterest(conn, itst);

		// 2.있으면 delete / 없으면 insert
		if(interest != null) {
			result = new ProductDao().deleteInterest(conn, itst);
		} else {
			result = new ProductDao().insertInterest(conn, itst);
		}

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int interestCount(int productNo) {
		Connection conn = getConnection();

		int interestCount = new ProductDao().interestCount(conn, productNo);

		if(interestCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return interestCount;
	}

	public ArrayList<ProductCategory> selectCategorySecond(int cateParent){
		Connection conn = getConnection();

		ArrayList<ProductCategory> list = new ProductDao().selectCategorySecond(conn, cateParent);

		close(conn);

		return list;

	}

	public ArrayList<Product> selectSaleProduct(int memNo, int productNo) {
		Connection conn = getConnection();

		ArrayList<Product> pList = new ProductDao().selectSaleProduct(conn, memNo, productNo);

		close(conn);

		return pList;
	}

	public Attachment selectUserProfile(int memNo) {
		Connection conn = getConnection();

		Attachment profile = new ProductDao().selectUserProfile(conn, memNo);

		close(conn);

		return profile;
	}

	public Member selectMember(int memNo) {

		Connection conn = getConnection();

		Member m = new ProductDao().selectMember(conn, memNo);

		close(conn);

		return m;

	}

	public ArrayList<Attachment> selectSaleAttachment(ArrayList<Product> sellPnoList){

		Connection conn = getConnection();

		ArrayList<Attachment> pListAt = new ProductDao().selectSaleAttachment(conn, sellPnoList);

		close(conn);

		return pListAt;
	}

	public ArrayList<Product> searchProduct(String searchSelect, String searchText, int boardLimit) {

		Connection conn = getConnection();
		ArrayList<Product> searchList = new ArrayList();

		if(searchSelect.equals("title")) {
			searchList = new ProductDao().searchTitle(conn, searchText, boardLimit);
		} else if(searchSelect.equals("memId")) {
			searchList = new ProductDao().searchMemId(conn, searchText, boardLimit);
		} else {
			searchList = new ProductDao().searchAll(conn, searchText, boardLimit);
		}

		close(conn);

		return searchList;
	}

	public int selectListCount(String searchSelect, String searchText) {
		Connection conn = getConnection();
		int listCount = 0;

		if(searchSelect.equals("title")) {
			listCount = new ProductDao().searchTitleCount(conn, searchText);
		} else if(searchSelect.equals("memId")) {
			listCount = new ProductDao().searchMemIdCount(conn, searchText);
		} else {
			listCount = new ProductDao().searchAllCount(conn, searchText);
		}


		if(listCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return listCount;

	}




}
