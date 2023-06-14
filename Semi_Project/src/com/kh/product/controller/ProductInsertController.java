package com.kh.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/insert.po")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductInsertController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 1024 * 1024 * 10;

			String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",  new MyFileRenamePolicy());


			String productName = multiRequest.getParameter("productName");
			String category = multiRequest.getParameter("category2");
			String location = multiRequest.getParameter("location");
			String productStatus = multiRequest.getParameter("productStatus");
			int productPrice = Integer.parseInt(multiRequest.getParameter("productPrice"));
			String productDesc = multiRequest.getParameter("productDesc");
			String productDelv = multiRequest.getParameter("productDelv");
			String memNo = multiRequest.getParameter("memNo");
			String method = multiRequest.getParameter("p_method");

			Product p = new Product();

			p.setProductName(productName);
			p.setProductCate(category);
			p.setLocation(location);
			p.setProductStatus(productStatus);
			p.setProductPrice(productPrice);
			p.setProductDesc(productDesc);
			p.setProductDelv(productDelv);
			p.setMemNo(memNo);
			p.setMethod(method);

			ArrayList<Attachment> list = new ArrayList();
			Attachment at = null;
			// 5개
			for(int i = 1; i < 6; i++) {
				String p_file= "p_file" + i;

				if(multiRequest.getOriginalFileName(p_file) != null) {
					at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(p_file));
					at.setChangeName(multiRequest.getFilesystemName(p_file));
					at.setFilePath("resources/product_upfiles");
					if(i == 1) {
						at.setFileLevel(1);
					} else {
						at.setFileLevel(2);
					}

					list.add(at);
				}
			}

			int result = new ProductService().insertProduct(p, list);
			
			
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "상품이 등록되었습니다.");
				response.sendRedirect(request.getContextPath() + "/list.po?cpage=1");
			}else { // 실패
				// 만약 첨부파일이 있었는데 실패했다면 이미 업로드된 파일을 굳이 서버에서 보관할 필요없음
				if(at != null) {
					// delete() 호출
					new File(savePath + at.getChangeName()).delete();
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
