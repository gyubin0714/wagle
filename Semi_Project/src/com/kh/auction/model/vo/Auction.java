package com.kh.auction.model.vo;

import java.util.Date;

public class Auction {
	
	private int productNo;		//PRODUCT_NO	NUMBER  			상품번호
	private String memNo;	  	//MEM_NO	NUMBER					판매자번호
	private String nickName; 	//									판매자닉네임
	private String productName;//PRODUCT_NAME	VARCHAR2(50 BYTE)	상품이름
	private String productStatus;//PRODUCT_STATUS	VARCHAR2(20 BYTE) 중고/새상품
	private String location;	//LOACATION	VARCHAR2(300 BYTE)		거래지역
	private String productDesc;//PRODUCT_DESC	VARCHAR2(4000 BYTE)	상품설명
	private int productPrice;//PRODUCT_PRICE	NUMBER				상품가격
	private String productDelv;//PRODUCT_DELV	VARCHAR2(2000 BYTE)	배송방법
	private Date createDt;		//CREATE_DT	DATE					등록일
	private String deleteYN;	//DELETE_YN	CHAR(1 BYTE)			삭제여부
	private int viewCnt;		//VIEW_CNT	NUMBER					조회수
	private String productTradeStatus;//PRODUCT_TRADE_STATUS	VARCHAR2(100 BYTE)판매상태
	private int cateNo;			//CATE_NO	NUMBER					카테고리번호
	private String cateName;	//CATE_NAME 						카테고리이름
	private String method;		//METHOD	CHAR(1 BYTE)			거래방식
	private int auctionNo;		//AUCTION_NO	NUMBER				경매번호
	private int priceMin;		//PRICE_MIN	NUMBER					시작입찰가
	private int priceNow;		//PRICE_NOW	NUMBER					현재입찰가
	private String startTime;	//START_TIME	DATE				경매시작시간
	private String endTime; 	//END_TIME	DATE					경매종료시간
	private String titleimg;
	private int count;			//COUNT								입찰자수(중복제외)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Auction() {
		super();
	}

	public Auction(int productNo, String memNo, String nickName, String productName, String productStatus,
			String location, String productDesc, int productPrice, String productDelv, Date createDt, String deleteYN,
			int viewCnt, String productTradeStatus, int cateNo, String cateName, String method, int auctionNo,
			int priceMin, int priceNow, String startTime, String endTime, String titleimg) {
		super();
		this.productNo = productNo;
		this.memNo = memNo;
		this.nickName = nickName;
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
		this.cateName = cateName;
		this.method = method;
		this.auctionNo = auctionNo;
		this.priceMin = priceMin;
		this.priceNow = priceNow;
		this.startTime = startTime;
		this.endTime = endTime;
		this.titleimg = titleimg;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getAuctionNo() {
		return auctionNo;
	}

	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}

	public int getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}

	public int getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(int priceNow) {
		this.priceNow = priceNow;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	@Override
	public String toString() {
		return "Auction [productNo=" + productNo + ", memNo=" + memNo + ", nickName=" + nickName + ", productName="
				+ productName + ", productStatus=" + productStatus + ", location=" + location + ", productDesc="
				+ productDesc + ", productPrice=" + productPrice + ", productDelv=" + productDelv + ", createDt="
				+ createDt + ", deleteYN=" + deleteYN + ", viewCnt=" + viewCnt + ", productTradeStatus="
				+ productTradeStatus + ", cateNo=" + cateNo + ", cateName=" + cateName + ", method=" + method
				+ ", auctionNo=" + auctionNo + ", priceMin=" + priceMin + ", priceNow=" + priceNow + ", startTime="
				+ startTime + ", endTime=" + endTime + ", titleimg=" + titleimg + "]";
	}

}
