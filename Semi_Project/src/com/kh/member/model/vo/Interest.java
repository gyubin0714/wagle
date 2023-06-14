package com.kh.member.model.vo;

public class Interest {

	private int productNo; //PRODUCT_NO	NUMBER
	private int memNo; //MEM_NO	NUMBER
	
	
	public Interest() {
		super();
	}


	public Interest(int productNo, int memNo) {
		super();
		this.productNo = productNo;
		this.memNo = memNo;
	}


	public int getProductNo() {
		return productNo;
	}


	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}


	public int getMemNo() {
		return memNo;
	}


	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}


	@Override
	public String toString() {
		return "Interest [productNo=" + productNo + ", memNo=" + memNo + "]";
	}
	
	
	
	
}
