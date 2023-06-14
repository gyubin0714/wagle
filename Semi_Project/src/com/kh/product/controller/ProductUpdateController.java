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
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/update.po")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductUpdateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;

			String savePath = request.getSession().getServletContext().getRealPath("/resources/product_upfiles");

			MultipartRequest multiRequest
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			int productNo = Integer.parseInt(multiRequest.getParameter("pno")); 
			String productName = multiRequest.getParameter("productName");
			String category = multiRequest.getParameter("category2");
			String productStatus = multiRequest.getParameter("productStatus");
			String location = multiRequest.getParameter("location");
			int productPrice = Integer.parseInt(multiRequest.getParameter("productPrice"));
			String productDesc = multiRequest.getParameter("productDesc");
			String productDelv = multiRequest.getParameter("productDelv");
			String method = multiRequest.getParameter("p_method");

			Product p = new Product();

			p.setProductNo(productNo);
			p.setProductName(productName);
			p.setLocation(location);
			p.setProductStatus(productStatus);
			p.setProductDesc(productDesc);
			p.setProductPrice(productPrice);
			p.setProductDelv(productDelv);
			p.setProductCate(category);
			p.setMethod(method);

			ArrayList<Attachment> list = new ArrayList();
			Attachment titleAt = null;
			Attachment at = null;

			for(int i = 0; i < 5; i++) {
				String p_reUpfile= "p_reUpfile" + i;
				String originFileNo= "originFileNo" + i;
				String originFileName = "originFileName" + i;

				// 새로운 첨부파일 추가
				if(multiRequest.getOriginalFileName(p_reUpfile) != null) {
					//titleImg 경우(i==0)
					if(i == 0) {
						// 새로운 파일 존재하면 객체 생성!
						titleAt = new Attachment();
						titleAt.setOriginName(multiRequest.getOriginalFileName(p_reUpfile));
						titleAt.setChangeName(multiRequest.getFilesystemName(p_reUpfile));
						titleAt.setFilePath("resources/product_upfiles");
						titleAt.setRefBno(productNo); // 어떤 게시글의 첨부파일인지 productNo(REF_BNO)

						// 새로운 첨부파일 존재 + 원본파일 있을경우
						if(multiRequest.getParameter(originFileNo) != null) {
							titleAt.setFileNo(Integer.parseInt(multiRequest.getParameter(originFileNo)));
							new File(savePath + multiRequest.getParameter(originFileName)).delete();
						} 

					} else {
						//contentIng (1~4)
						at = new Attachment();
						at.setOriginName(multiRequest.getOriginalFileName(p_reUpfile));
						at.setChangeName(multiRequest.getFilesystemName(p_reUpfile));
						at.setFilePath("resources/product_upfiles");
						at.setRefBno(productNo); // 어떤 게시글의 첨부파일인지 productNo(REF_BNO)

						// 새로운 첨부파일 존재 + 원본파일 있을경우
						if(multiRequest.getParameter(originFileNo) != null) {
							at.setFileNo(Integer.parseInt(multiRequest.getParameter(originFileNo)));
							new File(savePath + multiRequest.getParameter(originFileName)).delete();
						} 

						at.setFileLevel(2);

						list.add(at);
					}
				}
			}
			
			int result = new ProductService().updateProduct(p, titleAt, list);

			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/detail.po?pno=" + productNo);
			} else { // 실패
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
