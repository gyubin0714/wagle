package com.kh.product.model.vo;

import java.sql.Date;

public class Product {

	private int productNo;//PRODUCT_NO	NUMBER
	private String memNo;//MEM_NO	NUMBER
	private String productName;//PRODUCT_NAME	VARCHAR2(50 BYTE)
	private String productStatus;//PRODUCT_STATUS	CHAR(1 BYTE)
	private String location;//LOACATION	VARCHAR2(300 BYTE)
	private String productDesc;//PRODUCT_DESC	VARCHAR2(4000 BYTE)
	private int productPrice;//PRODUCT_PRICE	NUMBER
	private String productDelv;//PRODUCT_DELV	VARCHAR2(2000 BYTE)
	private Date createDt;//CREATE_DT	DATE
	private String deleteYN;//DELETE_YN	CHAR(1 BYTE)
	private int viewCnt;//VIEW_CNT	NUMBER
	private String productTradeStatus;//PRODUCT_TRADE_STATUS	VARCHAR2(100 BYTE)
	private int cateNo;//CATE_NO	NUMBER
	private String method;//METHOD	CHAR(1 BYTE)
	private String titleimg;
	private String productCate;
	private String nickName;
	private int cateParent;
	private int interestCount;
	private String memName;
	private String memId;
	private int auctionNo;
	private String fileNo;
	
	public Product(int productNo, String productName, Date createDt, String fileNo, String method) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.createDt = createDt;
		this.fileNo = fileNo;
		this.method = method;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public Product(int productNo, String productName, int productPrice, Date createDt, String method) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.createDt = createDt;
		this.method = method;
	}

	public int getInterestCount() {
		return interestCount;
	}
	public void setInterestCount(int interestCount) {
		this.interestCount = interestCount;
	}
	public int getCateParent() {
		return cateParent;
	}
	public void setCateParent(int cateParent) {
		this.cateParent = cateParent;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProductCate() {
		return productCate;
	}
	public void setProductCate(String productCate) {
		this.productCate = productCate;
	}
	public String getTitleimg() {
		return titleimg;
	}
	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}
	public Product() {
		super();
	}
	public Product(int productNo, String memNo, String productName, String productStatus, String location,
			String productDesc, int productPrice, String productDelv, Date createDt, String deleteYN, int viewCnt,
			String productTradeStatus, int cateNo, String method) {
		super();
		this.productNo = productNo;
		this.memNo = memNo;
		this.productName = productName;
		this.productStatus = productStatus;
		this.location = location;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productDelv = productDelv;
		this.createDt = createDt;
		this.deleteYN = deleteYN;
		this.viewCnt = viewCnt;
		this.productTradeStatus = productTradeStatus;
		this.cateNo = cateNo;
		this.method = method;
	}
	public Product(int productNo, String memNo, String productName, String productStatus, String location,
			String productDesc, int productPrice, String productDelv, Date createDt, String deleteYN, int viewCnt,
			String productTradeStatus, int cateNo, String method, String memName, String memId, int auctionNo) {
		super();
		this.productNo = productNo;
		this.memNo = memNo;
		this.productName = productName;
		this.productStatus = productStatus;
		this.location = location;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productDelv = productDelv;
		this.createDt = createDt;
		this.deleteYN = deleteYN;
		this.viewCnt = viewCnt;
		this.productTradeStatus = productTradeStatus;
		this.cateNo = cateNo;
		this.method = method;
		this.memName = memName;
		this.memId = memId;
		this.auctionNo = auctionNo;
	}
	public int getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDelv() {
		return productDelv;
	}
	public void setProductDelv(String productDelv) {
		this.productDelv = productDelv;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getProductTradeStatus() {
		return productTradeStatus;
	}
	public void setProductTradeStatus(String productTradeStatus) {
		this.productTradeStatus = productTradeStatus;
	}
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", memNo=" + memNo + ", productName=" + productName
				+ ", productStatus=" + productStatus + ", location=" + location + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + ", productDelv=" + productDelv + ", createDt=" + createDt
				+ ", deleteYN=" + deleteYN + ", viewCnt=" + viewCnt + ", productTradeStatus=" + productTradeStatus
				+ ", cateNo=" + cateNo + ", method=" + method + "]";
	}
	
}
